package shopping.cartapi.controller;

import shopping.general.model.CartDTO;
import shopping.general.model.CartProductDTO;
import shopping.cartapi.service.CartService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/cart")
public class CartController {

    @Autowired
    CartService cartService;

    Logger log = LogManager.getLogger(CartController.class);

    @PostMapping("/crear")
    public ResponseEntity<CartDTO> createCart() {
        log.info("***MS ShoppingCart - creacion de carrito de compras***.");
        return ResponseEntity.ok(cartService.createCart());
    }

    @PostMapping("/agregar/{cartId}")
    public ResponseEntity<CartDTO> addProductCart(@PathVariable Long cartId, @RequestBody CartProductDTO product) {
        log.info("***MS ShoppingCart - agregar productos al carrito de compras, el id del carrito es: {}", cartId);
        return ResponseEntity.ok(cartService.addProduct(cartId, product));
    }

    @GetMapping("/{cartId}")
    public ResponseEntity<CartDTO> getShoppingCart(@PathVariable Long cartId) {
        log.info("***MS ShoppingCart - consultando carrito de compras, el id del carrito es: {}", cartId);
        return ResponseEntity.ok(cartService.getCart(cartId));
    }

    @DeleteMapping("/eliminar/{cartId}/{productId}")
    public ResponseEntity<CartDTO> deleteProductCart(@PathVariable Long cartId, @PathVariable Long productId) {
        log.info("***MS ShoppingCart - eliminar producto del carrito de compras, el id del carrito es: {} " +
                "el producto a eliminar es: {}", cartId, productId);
        return ResponseEntity.ok(cartService.deleteProduct(cartId, productId));
    }

    @PutMapping("/actualizar/{cartId}")
    public ResponseEntity<CartDTO> updateQuantityCart(@PathVariable Long cartId, @RequestBody CartProductDTO product) {
        log.info("***MS ShoppingCart - actualizar la cantidad del producto en el carrito de compras, el id del carrito es: {}", cartId);
        return ResponseEntity.ok(cartService.updateQuantity(cartId, product));
    }

    @PostMapping("/aplicar-cupon/{cartId}")
    public ResponseEntity<CartDTO> applyCoupon(@PathVariable Long cartId, @RequestParam String codeCoupon) {
        log.info("***MS ShoppingCart - aplicar cupon para el id: {} del carrito, el codigo del cupon es: {}", cartId, codeCoupon);
        return ResponseEntity.ok(cartService.applyCoupon(cartId, codeCoupon));
    }

}