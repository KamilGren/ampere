package pl.gren.oze_app.repository;

import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.stereotype.Repository;
import pl.gren.oze_app.model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
}
