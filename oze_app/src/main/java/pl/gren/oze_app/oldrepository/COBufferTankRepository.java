package pl.gren.oze_app.oldrepository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pl.gren.oze_app.model.COBufferTank;

import java.util.List;

@Repository
public interface COBufferTankRepository extends JpaRepository<COBufferTank, Long> {

    public static final String FIND_NAMES = "SELECT name FROM cobuffer_tank";

    @Query(value = FIND_NAMES, nativeQuery = true)
    public List<String> findCOBufferNames();

    public COBufferTank findByName(String name);


}