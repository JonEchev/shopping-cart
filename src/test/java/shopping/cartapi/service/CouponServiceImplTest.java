package shopping.cartapi.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import shopping.general.database.entity.Cupon;
import shopping.general.database.repository.CuponRepository;
import shopping.general.model.CouponDTO;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CouponServiceImplTest {

    @Mock
    private CuponRepository cuponRepository;

    private CouponService couponService;

    @BeforeEach
    void setUp() {
        couponService = new CouponServiceImpl(cuponRepository);
    }

    @Test
    void validateCouponWithValidActiveCouponShouldReturnCouponDTOTest() {

        String couponCode = "DESC10";

        Cupon cupon = new Cupon();
        cupon.setId(1L);
        cupon.setCodigo(couponCode);
        cupon.setDescuento(BigDecimal.TEN);
        cupon.setActivo(true);

        when(cuponRepository.findByCodigo(couponCode)).thenReturn(Optional.of(cupon));

        CouponDTO result = couponService.validateCoupon(couponCode);

        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals(couponCode, result.getCode());
        assertEquals(BigDecimal.TEN, result.getDiscount());
        assertTrue(result.isActive());

        verify(cuponRepository, times(1)).findByCodigo(couponCode);

    }

    @Test
    void validateCouponWithNonExistentCouponShouldThrowExceptionTest() {

        String couponCode = "INVALID";

        when(cuponRepository.findByCodigo(couponCode)).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class,
                () -> couponService.validateCoupon(couponCode));
        assertEquals("Cupón no encontrado o no válido.", exception.getMessage());

        verify(cuponRepository, times(1)).findByCodigo(couponCode);

    }

    @Test
    void validateCouponWithInactiveCouponShouldThrowExceptionTest() {

        String couponCode = "INACTIVE10";

        Cupon cupon = new Cupon();
        cupon.setCodigo(couponCode);
        cupon.setActivo(false);

        when(cuponRepository.findByCodigo(couponCode)).thenReturn(Optional.of(cupon));

        RuntimeException exception = assertThrows(RuntimeException.class,
                () -> couponService.validateCoupon(couponCode));
        assertEquals("El cupón no está activo.", exception.getMessage());

        verify(cuponRepository, times(1)).findByCodigo(couponCode);

    }

    @Test
    void disableCouponWithValidCouponShouldDisableAndSaveTest() {

        String couponCode = "DISABLE10";
        Cupon cupon = new Cupon();
        cupon.setCodigo(couponCode);
        cupon.setActivo(true);

        when(cuponRepository.findByCodigo(couponCode)).thenReturn(Optional.of(cupon));
        when(cuponRepository.save(any(Cupon.class))).thenReturn(cupon);

        couponService.disableCoupon(couponCode);

        assertFalse(cupon.getActivo());

        verify(cuponRepository, times(1)).findByCodigo(couponCode);
        verify(cuponRepository, times(1)).save(cupon);

    }

    @Test
    void disableCouponWithNonExistentCouponShouldThrowExceptionTest() {

        String couponCode = "NONEXISTENT";

        when(cuponRepository.findByCodigo(couponCode)).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class,
                () -> couponService.disableCoupon(couponCode));
        assertEquals("Cupón no encontrado o no válido.", exception.getMessage());

        verify(cuponRepository, times(1)).findByCodigo(couponCode);
        verify(cuponRepository, never()).save(any());

    }

}