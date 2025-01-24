package shopping.general.model;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
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

}
