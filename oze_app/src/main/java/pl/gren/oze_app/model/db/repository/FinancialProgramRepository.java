package pl.gren.oze_app.model.db.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.gren.oze_app.model.db.entity.FinancialProgram;

import java.util.List;

@Repository
public interface FinancialProgramRepository extends CrudRepository<FinancialProgram, Long> {
    List<FinancialProgram> findAll();
}
