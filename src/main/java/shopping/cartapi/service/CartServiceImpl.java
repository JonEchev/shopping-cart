package shopping.cartapi.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import shopping.general.model.CartDTO;
import shopping.general.model.CartProductDTO;
import shopping.general.model.CouponDTO;
import shopping.general.model.ProductDTO;
import shopping.general.database.entity.Carrito;
import shopping.general.database.entity.CarritoProducto;
import shopping.general.database.entity.Producto;
import shopping.general.database.repository.CarritoProductoRepository;
import shopping.general.database.repository.CarritoRepository;
import shopping.general.database.repository.ProductoRepository;
import shopping.general.utilities.Utilities;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CartServiceImpl implements CartService {

    private final CarritoRepository carritoRepository;
    private final ProductoRepository productoRepository;
    private final CarritoProductoRepository carritoProductoRepository;

    @Autowired
    CouponService couponService;

    Logger log = LogManager.getLogger(CartServiceImpl.class);

    String msgCartNotFound = Utilities.getValueMessage("msg_cart_not_found");

    public CartServiceImpl(CarritoRepository carritoRepository, ProductoRepository productoRepository,
                           CarritoProductoRepository carritoProductoRepository) {
        this.carritoRepository = carritoRepository;
        this.productoRepository = productoRepository;
        this.carritoProductoRepository = carritoProductoRepository;
    }

    public CartDTO createCart() {

        Carrito cart = new Carrito();
        cart.setTotal(BigDecimal.ZERO);
        cart.setlCarritoProducto(new ArrayList<>());
        Carrito savedCart = carritoRepository.save(cart);

        return convertToCartDTO(savedCart);

    }

    @Transactional
    public CartDTO addProduct(Long cartId, CartProductDTO cartProductDTO) {

        Carrito carrito = carritoRepository.findById(cartId)
                .orElseThrow(() -> new RuntimeException(msgCartNotFound));

        Producto producto = productoRepository.findById(cartProductDTO.getProduct().getId())
                .orElseThrow(() -> new RuntimeException(Utilities.getValueMessage("msg_id_product_not_found")));

        Optional<CarritoProducto> existing = carrito.getlCarritoProducto()
                .stream()
                .filter(cp -> cp.getProducto().getId().equals(producto.getId()))
                .findFirst();

        log.info("***MS ShoppingCart - existe la compra en el carrito: {}", existing.isPresent());

        if (existing.isPresent()) {
            CarritoProducto carritoProducto = existing.get();
            carritoProducto.setCantidad(carritoProducto.getCantidad() + cartProductDTO.getAmount());
            carritoProducto.setSubtotal(carritoProducto.getProducto().getPrecio()
                    .multiply(BigDecimal.valueOf(carritoProducto.getCantidad())));
            carritoProductoRepository.save(carritoProducto);
        } else {
            CarritoProducto cartProductNew = new CarritoProducto();
            cartProductNew.setCarrito(carrito);
            cartProductNew.setProducto(producto);
            cartProductNew.setCantidad(cartProductDTO.getAmount());
            cartProductNew.setSubtotal(producto.getPrecio().multiply(BigDecimal.valueOf(cartProductDTO.getAmount())));
            carritoProductoRepository.save(cartProductNew);
        }

        updateCartTotal(carrito);

        return convertToCartDTO(carritoRepository.save(carrito));

    }

    @Transactional(readOnly = true)
    public CartDTO getCart(Long cartId) {

        Carrito carrito = carritoRepository.findById(cartId)
                .orElseThrow(() -> new RuntimeException(msgCartNotFound));

        return convertToCartDTO(carrito);

    }

    @Transactional
    public CartDTO deleteProduct(Long cartId, Long productId) {

        Carrito carrito = carritoRepository.findById(cartId)
                .orElseThrow(() -> new RuntimeException(msgCartNotFound));

        carrito.getlCarritoProducto().removeIf(cp -> cp.getProducto().getId().equals(productId));
        carritoProductoRepository.deleteById(productId);

        updateCartTotal(carrito);

        return convertToCartDTO(carritoRepository.save(carrito));

    }

    @Transactional
    public CartDTO updateQuantity(Long cartId, CartProductDTO cartProductDTO) {

        Carrito carrito = carritoRepository.findById(cartId)
                .orElseThrow(() -> new RuntimeException(msgCartNotFound));

        CarritoProducto carritoProducto = carrito.getlCarritoProducto()
                .stream()
                .filter(cp -> cp.getProducto().getId().equals(cartProductDTO.getProduct().getId()))
                .findFirst()
                .orElseThrow(() -> new RuntimeException(Utilities.getValueMessage("msg_product_cart_not_found")));

        log.info("***MS ShoppingCart - la cantidad del producto en el carrito es: {}", cartProductDTO);

        if (cartProductDTO.getAmount() == 0) {
            carrito.getlCarritoProducto().remove(carritoProducto);
            carritoProductoRepository.delete(carritoProducto);
        } else {
            carritoProducto.setCantidad(cartProductDTO.getAmount());
            carritoProducto.setSubtotal(carritoProducto.getProducto().getPrecio()
                    .multiply(BigDecimal.valueOf(cartProductDTO.getAmount())));
            carritoProductoRepository.save(carritoProducto);
        }

        updateCartTotal(carrito);

        return convertToCartDTO(carritoRepository.save(carrito));

    }

    @Transactional
    public CartDTO applyCoupon(Long cartId, String codeCoupon) {

        Carrito carrito = carritoRepository.findById(cartId)
                .orElseThrow(() -> new RuntimeException(msgCartNotFound));

        CouponDTO couponDTO = couponService.validateCoupon(codeCoupon);

        BigDecimal discount = carrito.getTotal().multiply(couponDTO.getDiscount().divide(BigDecimal.valueOf(100)));
        carrito.setTotal(carrito.getTotal().subtract(discount));
        couponService.disableCoupon(codeCoupon);

        return convertToCartDTO(carritoRepository.save(carrito));

    }

    private void updateCartTotal(Carrito carrito) {

        BigDecimal total = carrito.getlCarritoProducto().stream()
                .map(CarritoProducto::getSubtotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        carrito.setTotal(total);

    }

    private CartDTO convertToCartDTO(Carrito carrito) {

        List<CartProductDTO> productosDTO = carrito.getlCarritoProducto()
                .stream()
                .map(cp -> new CartProductDTO(
                        cp.getId(),
                        new ProductDTO(cp.getProducto().getId(), cp.getProducto().getNombre(), cp.getProducto().getPrecio()),
                        cp.getCantidad(),
                        cp.getSubtotal()
                ))
                .collect(Collectors.toList());

        return new CartDTO(carrito.getId(), carrito.getTotal(), productosDTO);

    }

}