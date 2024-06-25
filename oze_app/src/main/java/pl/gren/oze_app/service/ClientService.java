package pl.gren.oze_app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.gren.oze_app.model.db.entity.Client;
import pl.gren.oze_app.model.db.entity.Salesman;
import pl.gren.oze_app.model.db.repository.ClientRepository;
import pl.gren.oze_app.model.dto.client.ClientCreateDto;
import pl.gren.oze_app.model.dto.client.ClientUpdateDto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ClientService {

    private final ClientRepository clientRepository;
    private final SalesmanService salesmanService;

    @Autowired
    public ClientService(ClientRepository clientRepository, SalesmanService salesmanService) {
        this.clientRepository = clientRepository;
        this.salesmanService = salesmanService;
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

    public void delete(Client client) {
        clientRepository.delete(client);
    }

    public Client insert(ClientCreateDto dto) {
        Client client = new Client();
        client.setName(dto.getName());
        client.setAddress(dto.getAddress());
        client.setNote(dto.getNote());
        client.setPhone(dto.getPhone());
        client.setCreatedAt(LocalDateTime.now());
        Salesman salesman = salesmanService.findById(dto.getSalesmanId()).orElseThrow(IllegalArgumentException::new);
        client.setSalesman(salesman);
        return clientRepository.save(client);
    }

    public void update(Client client, ClientUpdateDto dto) {
        client.setName(dto.getName());
        client.setAddress(dto.getAddress());
        client.setNote(dto.getNote());
        client.setPhone(dto.getPhone());
        Salesman salesman = salesmanService.findById(dto.getSalesmanId()).orElseThrow(IllegalArgumentException::new);
        client.setSalesman(salesman);
        clientRepository.save(client);
    }

    public void deleteById(Long id) {
        clientRepository.deleteById(id);
    }
}