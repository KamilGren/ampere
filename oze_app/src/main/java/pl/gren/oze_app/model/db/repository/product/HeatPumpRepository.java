package pl.gren.oze_app.model.db.repository.product;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pl.gren.oze_app.model.db.entity.product.HeatPump;
import org.springframework.data.repository.CrudRepository;
import pl.gren.oze_app.model.db.enums.HeatPumpType;

import java.util.List;
import java.util.Optional;

@Repository
public interface HeatPumpRepository extends CrudRepository<HeatPump, Long> {
    Optional<HeatPump> findByModel(String model);
    Optional<HeatPump> findByManufacturerAndModelAndHeatPumpType(String manufacturer, String model, HeatPumpType heatPumpType);
    List<HeatPump> findAllByManufacturer(String manufacturer);

    @Query(value = "SELECT manufacturer FROM HeatPump")
    List<String> findAllManufacturers();

    @Query(value = "SELECT model FROM HeatPump")
    List<String> findAllModels();

    @Query(value = "SELECT model FROM HeatPump WHERE manufacturer = :m AND heatPumpType = :t")
    List<String> findAllModelsByManufacturerAndType(@Param(":m") String manufacturer, @Param(":t") HeatPumpType heatPumpType);


}
