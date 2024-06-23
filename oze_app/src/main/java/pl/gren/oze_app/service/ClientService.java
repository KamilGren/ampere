package pl.gren.oze_app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.gren.oze_app.model.db.entity.Client;
import pl.gren.oze_app.model.db.repository.ClientRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ClientService {

    private final ClientRepository clientRepository;

    @Autowired
    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public Client save(Client client) {
        return clientRepository.save(client);
    }

    public List<Client> findAll() {
        return clientRepository.findAll();
    }

    public Optional<Client> findById(Long id) {
        return clientRepository.findById(id);
    }

    public List<Client> findAllBySalesman(Long salesmanId) {
        return clientRepository.findAllBySalesmanId(salesmanId);
    }

    public Long getSalesmanId(Long clientId) {
        return clientRepository.findSalesmanIdById(clientId);
    }

    public Client showClientByBuildingId(Long buildingId) {
        return clientRepository.findByBuildingInfoId(buildingId);
    }

    public List<Long> getBuildingIds(Long clientId) {
        return clientRepository.findAllBuildingInfoIds(clientId);
    }

    public void delete(Long id) {
        clientRepository.deleteById(id);
    }

    public void delete(Client client) {
        clientRepository.delete(client);
    }

}