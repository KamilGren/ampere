package pl.gren.oze_app.model.db.repository.product;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.gren.oze_app.model.db.entity.product.DomesticHotWaterTank;

import java.util.List;
import java.util.Optional;

// CWU
@Repository
public interface DomesticHotWaterTankRepository extends CrudRepository<DomesticHotWaterTank, Long> {
    @Query("SELECT e.name FROM DomesticHotWaterTank e")
    List<String> findAllNames();

    Optional<DomesticHotWaterTank> findByName(String name);
}
