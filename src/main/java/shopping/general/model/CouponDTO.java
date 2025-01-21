package shopping.general.model;

import java.math.BigDecimal;

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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public BigDecimal getDiscount() {
        return discount;
    }

    public void setDiscount(BigDecimal discount) {
        this.discount = discount;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

}
