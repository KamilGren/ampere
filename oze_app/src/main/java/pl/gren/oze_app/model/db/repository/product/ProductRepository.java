package pl.gren.oze_app.model.db.repository.product;

import pl.gren.oze_app.model.db.entity.product.Product;
import org.springframework.data.repository.CrudRepository;

public interface ProductRepository extends CrudRepository<Product, Long> {

}
