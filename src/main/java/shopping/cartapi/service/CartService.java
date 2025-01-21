package shopping.cartapi.service;

import shopping.general.model.CartDTO;
import shopping.general.model.CartProductDTO;

public interface CartService {

    CartDTO createCart();

    CartDTO addProduct(Long cartId, CartProductDTO cartProductDTO);

    CartDTO deleteProduct(Long cartId, Long productId);

    CartDTO updateQuantity(Long cartId, CartProductDTO cartProductDTO);

    CartDTO getCart(Long cartId);

    CartDTO applyCoupon(Long cartId, String codeCoupon);

}