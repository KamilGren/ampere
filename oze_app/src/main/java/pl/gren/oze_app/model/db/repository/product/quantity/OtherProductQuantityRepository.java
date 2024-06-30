package pl.gren.oze_app.model.db.repository.product.quantity;

import org.springframework.data.repository.CrudRepository;
import pl.gren.oze_app.model.db.entity.embedded.ProductQuantityId;
import pl.gren.oze_app.model.db.entity.product.quantity.OtherProductQuantity;

public interface OtherProductQuantityRepository extends CrudRepository<OtherProductQuantity, ProductQuantityId> {
}
