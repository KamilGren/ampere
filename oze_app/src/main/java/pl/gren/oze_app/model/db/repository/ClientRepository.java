package pl.gren.oze_app.model.db.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pl.gren.oze_app.model.db.entity.Client;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

@Repository
public interface ClientRepository extends CrudRepository<Client, Long> {
    List<Client> findAll();

    @Query(value = """
        SELECT * FROM client c
        WHERE `id` = (SELECT client_id FROM `order` WHERE `building_info_id` = :buildingInfoId)
    """, nativeQuery = true)
    Client findByBuildingInfoId(@Param("buildingInfoId") Long buildingInfoId);

    @Query(value="SELECT * FROM client WHERE salesman_id = :salesmanId", nativeQuery = true)
    List<Client> findAllBySalesmanId(Long salesmanId);

    @Query(value="SELECT salesman_id FROM client WHERE id = :clientId", nativeQuery = true)
    Long findSalesmanIdById(Long clientId);

    @Query(value = """
        SELECT building_info_id FROM `order` WHERE client_id = :clientId
    """, nativeQuery = true)
    List<Long> findAllBuildingInfoIds(Long clientId);
}
