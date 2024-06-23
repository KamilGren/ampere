package pl.gren.oze_app.model.db.repository;

import org.springframework.stereotype.Repository;
import pl.gren.oze_app.model.db.entity.Inverter;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

@Repository
public interface InverterRepository extends CrudRepository<Inverter, Long> {
    List<Inverter> findAll();
}
