package pl.gren.oze_app.oldrepository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pl.gren.oze_app.model.ClientProducts;
import pl.gren.oze_app.model.db.entity.Client;

@Repository
public interface ClientProductRepository extends JpaRepository<ClientProducts, Long> {


}
