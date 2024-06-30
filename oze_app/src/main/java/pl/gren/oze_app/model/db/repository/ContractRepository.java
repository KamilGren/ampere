package pl.gren.oze_app.model.db.repository;

import org.springframework.stereotype.Repository;
import pl.gren.oze_app.model.db.entity.Contract;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

@Repository
public interface ContractRepository extends CrudRepository<Contract, Long> {
    List<Contract> findAll();
}
