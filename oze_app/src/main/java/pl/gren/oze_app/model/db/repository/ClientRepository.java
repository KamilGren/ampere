package pl.gren.oze_app.model.db.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pl.gren.oze_app.model.db.entity.Client;
import org.springframework.data.repository.CrudRepository;

@Repository
public interface ClientRepository extends CrudRepository<Client, Long> {
    @Query(value = """
        SELECT * FROM client c
        WHERE `id` = (SELECT client_id FROM `order` WHERE `building_info_id` = :buildingInfoId)
    """, nativeQuery = true)
    Client findByBuildingInfo(@Param("buildingInfoId") Long buildingInfoId);
}
