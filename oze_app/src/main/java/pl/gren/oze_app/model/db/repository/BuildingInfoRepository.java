package pl.gren.oze_app.model.db.repository;

import org.springframework.stereotype.Repository;
import pl.gren.oze_app.model.db.entity.BuildingInfo;
import org.springframework.data.repository.CrudRepository;

@Repository
public interface BuildingInfoRepository extends CrudRepository<BuildingInfo, Long> {

}
