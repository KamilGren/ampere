package pl.gren.oze_app.model.db.repository.product;

import org.springframework.data.repository.CrudRepository;
import pl.gren.oze_app.model.db.entity.product.Circulation;

public interface CirculationRepository extends CrudRepository<Circulation, Long> {

}
