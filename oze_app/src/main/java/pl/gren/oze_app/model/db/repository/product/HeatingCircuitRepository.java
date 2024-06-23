package pl.gren.oze_app.model.db.repository.product;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.gren.oze_app.model.db.entity.product.HeatingCircuit;

@Repository
public interface HeatingCircuitRepository extends CrudRepository<HeatingCircuit, Long> {

}
