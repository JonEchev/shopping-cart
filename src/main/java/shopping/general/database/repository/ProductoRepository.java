package shopping.general.database.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import shopping.general.database.entity.Producto;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Long> {
}
