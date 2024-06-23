package pl.gren.oze_app.model.db.repository.product;

import org.springframework.stereotype.Repository;
import pl.gren.oze_app.model.db.entity.product.HeatPump;
import org.springframework.data.repository.CrudRepository;

@Repository
public interface HeatPumpRepository extends CrudRepository<HeatPump, Long> {

}
