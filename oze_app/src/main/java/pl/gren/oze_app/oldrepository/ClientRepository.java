package pl.gren.oze_app.oldrepository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pl.gren.oze_app.model.Client;
import pl.gren.oze_app.model.ClientProducts;

import java.util.List;
import java.util.Optional;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {

    public static final String FIND_CLIENTPRODUCTS = "SELECT ClientProducts FROM Client";
    public static final String FIND_SALESMAN_ID = "SELECT salesman_id from Client WHERE id = :clientId";
    public static final String FIND_CLIENT_BY_BUILDING_ID = "SELECT * from Client WHERE building_requirements_id = :buildingId";

    List<Client> findClientsBySalesmanId(Long id);
    Optional<Client> findClientById(Long id);

    @Query(value = FIND_CLIENTPRODUCTS, nativeQuery = true)
    Iterable<ClientProducts> findClientProducts();

    //@Query(value = FIND_SALESMAN_ID, nativeQuery = true)
    Long findSalesman_IdById(Long id);
    Long findClient_IdById(Long clientId);

    @Query(value = FIND_CLIENT_BY_BUILDING_ID, nativeQuery = true)
    Client findClientByBuilding_Requirements_Id(@Param("buildingId") Long buildingId);
}