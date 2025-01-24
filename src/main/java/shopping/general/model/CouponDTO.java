package shopping.general.model;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class CouponDTO {

    private Long id;
    private String code;
    private BigDecimal discount;
    private boolean active;

    public CouponDTO() {
    }

    public CouponDTO(Long id, String code, BigDecimal discount, Boolean active) {
        this.id = id;
        this.code = code;
        this.discount = discount;
        this.active = active;
    }

}
