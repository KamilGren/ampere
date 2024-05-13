package pl.gren.oze_app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pl.gren.oze_app.model.Client;
import pl.gren.oze_app.model.ClientProducts;

import java.util.List;
import java.util.Optional;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {

    public static final String FIND_CLIENTPRODUCTS = "SELECT ClientProducts FROM Client";

    List<Client> findClientsBySalesmanId(Long id);
    Optional<Client> findClientById(Long id);

    @Query(value = FIND_CLIENTPRODUCTS, nativeQuery = true)
    Iterable<ClientProducts> findClientProducts();

}