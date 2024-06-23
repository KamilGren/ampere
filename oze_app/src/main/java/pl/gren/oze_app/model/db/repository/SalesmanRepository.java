package pl.gren.oze_app.model.db.repository;

import org.springframework.stereotype.Repository;
import pl.gren.oze_app.model.db.entity.Salesman;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

@Repository
public interface SalesmanRepository extends CrudRepository<Salesman, Long> {
    Optional<Salesman> findByName(String name);
    Optional<Salesman> findByEmail(String email);
}
