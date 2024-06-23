package pl.gren.oze_app.model.db.repository;

import pl.gren.oze_app.model.db.entity.Client;
import org.springframework.data.repository.CrudRepository;

public interface ClientRepository extends CrudRepository<Client, Long> {

}
