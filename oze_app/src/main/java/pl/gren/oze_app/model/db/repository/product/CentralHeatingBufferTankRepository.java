package pl.gren.oze_app.model.db.repository.product;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.gren.oze_app.model.db.entity.product.CentralHeatingBufferTank;

import java.util.List;
import java.util.Optional;

@Repository
public interface CentralHeatingBufferTankRepository extends CrudRepository<CentralHeatingBufferTank, Long> {
    List<CentralHeatingBufferTank> findAll();

    List<CentralHeatingBufferTank> findAllByManufacturer(String manufacturer);

    @Query("SELECT e.name FROM CentralHeatingBufferTank e")
    List<String> findAllNames();

    @Query(value = "SELECT DISTINCT x.manufacturer FROM CentralHeatingBufferTank x ORDER BY x.manufacturer")
    List<String> findAllManufacturers();

    Optional<CentralHeatingBufferTank> findByName(String name);
}
