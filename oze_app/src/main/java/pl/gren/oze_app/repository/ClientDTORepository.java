package pl.gren.oze_app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pl.gren.oze_app.model.Client;
import pl.gren.oze_app.model.ClientDTO;
import pl.gren.oze_app.model.ClientProducts;

import java.util.List;
import java.util.Optional;

@Repository
public interface ClientDTORepository extends JpaRepository<ClientDTO, Long> {

    Optional<ClientDTO> findClientDTOById(Long id);



}