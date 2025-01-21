package shopping.general.database.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Carrito {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private BigDecimal total = BigDecimal.ZERO;

    @OneToMany(mappedBy = "carrito", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CarritoProducto> lCarritoProducto = new ArrayList<>();

    public Carrito() {
    }

    public Carrito(BigDecimal total) {
        this.total = total;
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

    public List<CarritoProducto> getlCarritoProducto() {
        return lCarritoProducto;
    }

    public void setlCarritoProducto(List<CarritoProducto> lCarritoProducto) {
        this.lCarritoProducto = lCarritoProducto;
    }

}