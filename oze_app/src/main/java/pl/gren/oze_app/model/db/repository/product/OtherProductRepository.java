package pl.gren.oze_app.model.db.repository.product;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.gren.oze_app.model.db.entity.product.OtherProduct;
import pl.gren.oze_app.model.db.enums.OtherProductType;

import java.util.List;
import java.util.Optional;

@Repository
public interface OtherProductRepository extends CrudRepository<OtherProduct, Long> {
    List<OtherProduct> findAll();

    @Query("SELECT p FROM OtherProduct p where p.type = :type")
    List<OtherProduct> findAllByType(OtherProductType type);

    @Query("SELECT p FROM OtherProduct p where p.type = :type AND p.id = :id")
    Optional<OtherProduct> findByIdAndType(Long id, OtherProductType type);
}
