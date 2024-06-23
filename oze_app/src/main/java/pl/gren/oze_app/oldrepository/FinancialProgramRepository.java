package pl.gren.oze_app.oldrepository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.gren.oze_app.model.offer.FinancialProgram;

@Repository
public interface FinancialProgramRepository extends JpaRepository<FinancialProgram, Long> {

    FinancialProgram findByName(String name);

}
