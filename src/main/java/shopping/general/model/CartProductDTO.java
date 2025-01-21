package shopping.general.model;

import java.math.BigDecimal;

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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ProductDTO getProduct() {
        return product;
    }

    public void setProduct(ProductDTO product) {
        this.product = product;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public BigDecimal getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(BigDecimal subtotal) {
        this.subtotal = subtotal;
    }

}
