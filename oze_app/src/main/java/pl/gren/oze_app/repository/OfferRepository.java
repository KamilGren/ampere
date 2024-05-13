package pl.gren.oze_app.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.gren.oze_app.model.offer.Credit;
import pl.gren.oze_app.model.offer.Offer;

@Repository
public interface OfferRepository extends JpaRepository<Offer, Long>{
}

