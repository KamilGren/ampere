package pl.gren.oze_app.model.db.repository.product;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.gren.oze_app.model.db.entity.product.DomesticHotWaterTank;

@Repository
public interface DomesticHotWaterTankRepository extends CrudRepository<DomesticHotWaterTank, Long> {

}
