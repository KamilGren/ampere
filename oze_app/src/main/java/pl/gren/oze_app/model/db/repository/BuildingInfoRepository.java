package pl.gren.oze_app.model.db.repository;

import org.springframework.stereotype.Repository;
import pl.gren.oze_app.model.db.entity.BuildingInfo;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BuildingInfoRepository extends CrudRepository<BuildingInfo, Long> {
    List<BuildingInfo> findAll();
    Optional<BuildingInfo> findByLocation(String location);
}
