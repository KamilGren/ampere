package pl.gren.oze_app.model.db.repository;

import pl.gren.oze_app.model.db.entity.Order;
import org.springframework.data.repository.CrudRepository;

public interface OrderRepository extends CrudRepository<Order, Long> {

}
