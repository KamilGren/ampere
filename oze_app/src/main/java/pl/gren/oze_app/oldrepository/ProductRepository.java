package pl.gren.oze_app.oldrepository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.gren.oze_app.model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
}
