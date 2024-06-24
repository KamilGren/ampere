package pl.gren.oze_app.model.db.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.gren.oze_app.model.db.entity.TaxCredit;

import java.util.List;

@Repository
public interface TaxCreditRepository extends CrudRepository<TaxCredit, Long> {
    List<TaxCredit> findAll();
}

