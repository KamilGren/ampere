package pl.gren.oze_app.model.db.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pl.gren.oze_app.model.db.entity.Salesman;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SalesmanRepository extends CrudRepository<Salesman, Long> {
    List<Salesman> findAll();
    Optional<Salesman> findByFirstName(String name);
    Optional<Salesman> findByEmail(String email);
    @Query("SELECT s FROM Salesman s WHERE s.username = :username")
    Optional<Salesman> findByUsername(String username);
}
