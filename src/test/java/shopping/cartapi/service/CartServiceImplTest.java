package shopping.cartapi.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import shopping.general.database.entity.Carrito;
import shopping.general.database.entity.CarritoProducto;
import shopping.general.database.entity.Producto;
import shopping.general.database.repository.CarritoProductoRepository;
import shopping.general.database.repository.CarritoRepository;
import shopping.general.database.repository.ProductoRepository;
import shopping.general.model.CartDTO;
import shopping.general.model.CartProductDTO;
import shopping.general.model.CouponDTO;
import shopping.general.model.ProductDTO;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class CartServiceImplTest {

    @Mock
    private CouponService couponService;

    private CartServiceImpl cartService;

    @Mock
    private CarritoRepository carritoRepository;

    @Mock
    private ProductoRepository productoRepository;

    @Mock
    private CarritoProductoRepository carritoProductoRepository;

    private Carrito carrito;
    private Producto producto;
    private CarritoProducto carritoProducto;
    private CartProductDTO cartProductDTO;

    @BeforeEach
    void setUp() {

        MockitoAnnotations.openMocks(this);

        cartService = new CartServiceImpl(carritoRepository, productoRepository, carritoProductoRepository);
        cartService.couponService = couponService;

        carrito = new Carrito();
        carrito.setId(1L);
        carrito.setTotal(BigDecimal.valueOf(100));
        carrito.setlCarritoProducto(new ArrayList<>());

        producto = new Producto();
        producto.setId(1L);
        producto.setNombre("Test Product");
        producto.setPrecio(BigDecimal.valueOf(50));

        carritoProducto = new CarritoProducto();
        carritoProducto.setId(1L);
        carritoProducto.setCarrito(carrito);
        carritoProducto.setProducto(producto);
        carritoProducto.setCantidad(2);
        carritoProducto.setSubtotal(BigDecimal.valueOf(100));

        ProductDTO productDTO = new ProductDTO(1L, "Test Product", BigDecimal.valueOf(50));
        cartProductDTO = new CartProductDTO(1L, productDTO, 2, BigDecimal.valueOf(100));

    }

    @Test
    void createCartShouldCreateNewCartTest() {

        when(carritoRepository.save(any(Carrito.class))).thenReturn(carrito);

        CartDTO result = cartService.createCart();

        assertNotNull(result);
        assertEquals(carrito.getId(), result.getId());
        assertEquals(BigDecimal.valueOf(100), result.getTotal());

        verify(carritoRepository).save(any(Carrito.class));

    }

    @Test
    void addProductNewProductShouldAddToCartTest() {

        when(carritoRepository.findById(1L)).thenReturn(Optional.of(carrito));
        when(productoRepository.findById(1L)).thenReturn(Optional.of(producto));
        when(carritoProductoRepository.save(any(CarritoProducto.class))).thenReturn(carritoProducto);
        when(carritoRepository.save(any(Carrito.class))).thenReturn(carrito);

        CartDTO result = cartService.addProduct(1L, cartProductDTO);

        assertNotNull(result);

        verify(carritoProductoRepository).save(any(CarritoProducto.class));
        verify(carritoRepository).save(carrito);

    }

    @Test
    void addProductExistingProductShouldUpdateQuantityTest() {

        carrito.getlCarritoProducto().add(carritoProducto);

        when(carritoRepository.findById(1L)).thenReturn(Optional.of(carrito));
        when(productoRepository.findById(1L)).thenReturn(Optional.of(producto));
        when(carritoProductoRepository.save(any(CarritoProducto.class))).thenReturn(carritoProducto);
        when(carritoRepository.save(any(Carrito.class))).thenReturn(carrito);

        CartDTO result = cartService.addProduct(1L, cartProductDTO);

        assertNotNull(result);

        verify(carritoProductoRepository).save(any(CarritoProducto.class));
        verify(carritoRepository).save(carrito);

    }

    @Test
    void getCartShouldReturnCartTest() {

        when(carritoRepository.findById(1L)).thenReturn(Optional.of(carrito));

        CartDTO result = cartService.getCart(1L);

        assertNotNull(result);
        assertEquals(carrito.getId(), result.getId());
        assertEquals(carrito.getTotal(), result.getTotal());

    }

    @Test
    void deleteProductShouldRemoveProductFromCartTest() {

        carrito.getlCarritoProducto().add(carritoProducto);

        when(carritoRepository.findById(1L)).thenReturn(Optional.of(carrito));
        when(carritoRepository.save(any(Carrito.class))).thenReturn(carrito);

        CartDTO result = cartService.deleteProduct(1L, 1L);

        assertNotNull(result);

        verify(carritoProductoRepository).deleteById(1L);
        verify(carritoRepository).save(carrito);

    }

    @Test
    void updateQuantityShouldUpdateProductQuantityTest() {

        carrito.getlCarritoProducto().add(carritoProducto);

        when(carritoRepository.findById(1L)).thenReturn(Optional.of(carrito));
        when(carritoProductoRepository.save(any(CarritoProducto.class))).thenReturn(carritoProducto);
        when(carritoRepository.save(any(Carrito.class))).thenReturn(carrito);

        CartDTO result = cartService.updateQuantity(1L, cartProductDTO);

        assertNotNull(result);

        verify(carritoProductoRepository).save(any(CarritoProducto.class));
        verify(carritoRepository).save(carrito);

    }

    @Test
    void updateQuantityZeroQuantityShouldRemoveProductTest() {

        carrito.getlCarritoProducto().add(carritoProducto);
        cartProductDTO.setAmount(0);

        when(carritoRepository.findById(1L)).thenReturn(Optional.of(carrito));
        when(carritoRepository.save(any(Carrito.class))).thenReturn(carrito);

        CartDTO result = cartService.updateQuantity(1L, cartProductDTO);

        assertNotNull(result);

        verify(carritoProductoRepository).delete(any(CarritoProducto.class));
        verify(carritoRepository).save(carrito);

    }

    @Test
    void applyCouponShouldApplyDiscountToCartTest() {

        CouponDTO couponDTO = new CouponDTO();
        couponDTO.setDiscount(BigDecimal.TEN);

        when(carritoRepository.findById(1L)).thenReturn(Optional.of(carrito));
        when(couponService.validateCoupon("DESC10")).thenReturn(couponDTO);
        when(carritoRepository.save(any(Carrito.class))).thenReturn(carrito);

        CartDTO result = cartService.applyCoupon(1L, "DESC10");

        assertNotNull(result);

        verify(couponService).validateCoupon("DESC10");
        verify(couponService).disableCoupon("DESC10");
        verify(carritoRepository).save(carrito);

    }

    @Test
    void getCartCartNotFoundShouldThrowExceptionTest() {

        when(carritoRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> cartService.getCart(1L));

    }

}