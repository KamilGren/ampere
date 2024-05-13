package pl.gren.oze_app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pl.gren.oze_app.model.CWUBufforTank;

import java.util.List;


@Repository
public interface CWUBufforRepository extends JpaRepository<CWUBufforTank, Long> {

    public static final String FIND_NAMES = "SELECT name FROM cwubuffor_tank";

    @Query(value = FIND_NAMES, nativeQuery = true)
    public List<String> findCWUBufforNames();

    public CWUBufforTank findByName(String name);

}