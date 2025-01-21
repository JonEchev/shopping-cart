package shopping.general.model;

import java.math.BigDecimal;
import java.util.List;

public class CartDTO {

    private Long id;
    private BigDecimal total;
    private List<CartProductDTO> lCartProduct;

    public CartDTO() {
    }

    public CartDTO(Long id, BigDecimal total, List<CartProductDTO> lCartProduct) {
        this.id = id;
        this.total = total;
        this.lCartProduct = lCartProduct;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public List<CartProductDTO> getlCartProduct() {
        return lCartProduct;
    }

    public void setlCartProduct(List<CartProductDTO> lCartProduct) {
        this.lCartProduct = lCartProduct;
    }

}
