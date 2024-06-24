package pl.gren.oze_app.model.db.repository.product;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.gren.oze_app.model.db.entity.product.CentralHeatingBufferTank;

import java.util.List;
import java.util.Optional;

@Repository
public interface CentralHeatingBufferTankRepository extends CrudRepository<CentralHeatingBufferTank, Long> {
    @Query("SELECT e.name FROM CentralHeatingBufferTank e")
    List<String> findAllNames();

    Optional<CentralHeatingBufferTank> findByName(String name);
}
