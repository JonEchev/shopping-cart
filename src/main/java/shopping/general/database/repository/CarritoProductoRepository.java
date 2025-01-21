package shopping.general.database.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import shopping.general.database.entity.CarritoProducto;

@Repository
public interface CarritoProductoRepository extends JpaRepository<CarritoProducto, Long> {
}
