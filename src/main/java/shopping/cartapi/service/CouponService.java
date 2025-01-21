package shopping.cartapi.service;

import shopping.general.model.CouponDTO;

public interface CouponService {

    CouponDTO validateCoupon(String codeCoupon);

    void disableCoupon(String codeCoupon);

}