package pl.gren.oze_app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pl.gren.oze_app.model.HeatPump;
import pl.gren.oze_app.model.Product;


import java.util.List;
import java.util.Optional;

public interface HeatPumpRepository extends JpaRepository<HeatPump, Long> {

    // public delete

    public static final String FIND_PRODUCENTS = "SELECT DISTINCT Producent FROM heat_pump";
    public static final String FIND_MODELS = "SELECT DISTINCT model FROM heat_pump WHERE producent = :producent AND type = :type";
    public static final String FIND_TYPE = "SELECT DISTINCT model FROM heat_pump WHERE producent = :producent AND type = :type";
    //public static final String FIND_HEATPUMP = "SELECT * FROM heat_pump WHERE producent = :producent AND model = :model AND type = :type";

    public Optional<HeatPump> findById(Long id);

    public Optional<HeatPump> findByModel(String model);

   // @Query(value = FIND_HEATPUMP, nativeQuery = true)
    public HeatPump getHeatPumpByProducentAndModelAndType(String producent, String type, String model);

    @Query(value = FIND_PRODUCENTS, nativeQuery = true)
    public List<String> findHeatPumpsProducents();

    @Query(value = FIND_MODELS, nativeQuery = true)
    public List<String> findHeatPumpsModels();

    @Query(value = FIND_MODELS, nativeQuery = true)
    public List<String> findHeatPumpModelsByProducentAndType(@Param("producent") String producent, @Param("type") String model);

    public List<HeatPump> findHeatPumpByProducent(String producent);

    public List<HeatPump> findHeatPumpByProducentAndModel(String producent, String model);


    @Query("SELECT DISTINCT hp.model FROM HeatPump hp WHERE hp.producent = :producent")
    List<String> findHeatPumpModelsByProducent(@Param("producent") String producent);












}