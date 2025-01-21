package shopping.cartapi.controller;

import shopping.cartapi.service.CartService;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.junit.Assert.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;
import shopping.general.model.CartDTO;
import shopping.general.model.CartProductDTO;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CartControllerTest {

    @Mock
    private CartService cartService;

    @InjectMocks
    private CartController cartController;

    private CartDTO cartDTO;
    private CartProductDTO cartProductDTO;

    @BeforeEach
    void setUp() {

        MockitoAnnotations.openMocks(this);

        cartDTO = new CartDTO();
        cartProductDTO = new CartProductDTO();

    }

    @Test
    void createCartShouldReturnNewCartTest() {

        when(cartService.createCart()).thenReturn(cartDTO);

        ResponseEntity<CartDTO> response = cartController.createCart();

        assertNotNull(response);
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(cartDTO, response.getBody());

        verify(cartService, times(1)).createCart();

    }

    @Test
    void addProductCartShouldAddProductToCartTest() {

        Long cartId = 1L;
        when(cartService.addProduct(cartId, cartProductDTO)).thenReturn(cartDTO);

        ResponseEntity<CartDTO> response = cartController.addProductCart(cartId, cartProductDTO);

        assertNotNull(response);
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(cartDTO, response.getBody());

        verify(cartService, times(1)).addProduct(cartId, cartProductDTO);

    }

    @Test
    void getShoppingCartShouldReturnCartTest() {

        Long cartId = 1L;
        when(cartService.getCart(cartId)).thenReturn(cartDTO);

        ResponseEntity<CartDTO> response = cartController.getShoppingCart(cartId);

        assertNotNull(response);
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(cartDTO, response.getBody());

        verify(cartService, times(1)).getCart(cartId);

    }

    @Test
    void deleteProductCartShouldRemoveProductFromCartTest() {

        Long cartId = 1L;
        Long productId = 1L;
        when(cartService.deleteProduct(cartId, productId)).thenReturn(cartDTO);

        ResponseEntity<CartDTO> response = cartController.deleteProductCart(cartId, productId);

        assertNotNull(response);
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(cartDTO, response.getBody());

        verify(cartService, times(1)).deleteProduct(cartId, productId);

    }

    @Test
    void updateQuantityCartShouldUpdateProductQuantityTest() {

        Long cartId = 1L;
        when(cartService.updateQuantity(cartId, cartProductDTO)).thenReturn(cartDTO);

        ResponseEntity<CartDTO> response = cartController.updateQuantityCart(cartId, cartProductDTO);

        assertNotNull(response);
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(cartDTO, response.getBody());

        verify(cartService, times(1)).updateQuantity(cartId, cartProductDTO);

    }

    @Test
    void applyCouponShouldApplyCouponToCartTest() {

        Long cartId = 1L;
        String codeCoupon = "DISCOUNT20";
        when(cartService.applyCoupon(cartId, codeCoupon)).thenReturn(cartDTO);

        ResponseEntity<CartDTO> response = cartController.applyCoupon(cartId, codeCoupon);

        assertNotNull(response);
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(cartDTO, response.getBody());

        verify(cartService, times(1)).applyCoupon(cartId, codeCoupon);

    }

}