package pl.gren.oze_app.model.db.repository;

import org.springframework.stereotype.Repository;
import pl.gren.oze_app.model.db.entity.OrderConfig;
import org.springframework.data.repository.CrudRepository;

@Repository
public interface OrderConfigRepository extends CrudRepository<OrderConfig, Long> {

}
