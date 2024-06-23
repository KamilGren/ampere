package pl.gren.oze_app.model.db.repository.product;

import org.springframework.stereotype.Repository;
import pl.gren.oze_app.model.db.entity.product.Product;
import org.springframework.data.repository.CrudRepository;

@Repository
public interface ProductRepository extends CrudRepository<Product, Long> {

}
