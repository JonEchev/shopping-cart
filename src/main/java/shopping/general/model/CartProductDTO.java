package shopping.general.model;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class CartProductDTO {

    private Long id;
    private ProductDTO product;
    private Integer amount;
    private BigDecimal subtotal;

    public CartProductDTO() {
    }

    public CartProductDTO(Long id, ProductDTO product, Integer amount, BigDecimal subtotal) {
        this.id = id;
        this.product = product;
        this.amount = amount;
        this.subtotal = subtotal;
    }

}
