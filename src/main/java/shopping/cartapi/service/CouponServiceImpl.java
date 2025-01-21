package shopping.cartapi.service;

import org.springframework.stereotype.Service;
import shopping.general.model.CouponDTO;
import shopping.general.database.entity.Cupon;
import shopping.general.database.repository.CuponRepository;
import shopping.general.utilities.Utilities;

@Service
public class CouponServiceImpl implements CouponService {

    private final CuponRepository cuponRepository;

    public CouponServiceImpl(CuponRepository cuponRepository) {
        this.cuponRepository = cuponRepository;
    }

    public CouponDTO validateCoupon(String codeCoupon) {

        Cupon cupon = cuponRepository.findByCodigo(codeCoupon)
                .orElseThrow(() -> new RuntimeException(Utilities.getValueMessage("msg_coupon_not_found")));

        if (!cupon.getActivo()) {
            throw new RuntimeException(Utilities.getValueMessage("msg_coupon_not_active"));
        }

        return convertToCouponDTO(cupon);

    }

    public void disableCoupon(String codeCoupon) {

        Cupon cupon = cuponRepository.findByCodigo(codeCoupon)
                .orElseThrow(() -> new RuntimeException(Utilities.getValueMessage("msg_coupon_not_found")));

        cupon.setActivo(false);
        cuponRepository.save(cupon);

    }

    private CouponDTO convertToCouponDTO(Cupon cupon) {
        return new CouponDTO(cupon.getId(), cupon.getCodigo(), cupon.getDescuento(), cupon.getActivo());
    }

}