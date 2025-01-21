package shopping.general.database.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import shopping.general.database.entity.Carrito;

@Repository
public interface CarritoRepository extends JpaRepository<Carrito, Long> {
}
