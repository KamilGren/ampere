package pl.gren.oze_app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.gren.oze_app.model.Client;
import pl.gren.oze_app.model.ClientProducts;
import pl.gren.oze_app.repository.ClientRepository;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class ClientService {

    private final ClientRepository clientRepository;

    @Autowired
    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public Iterable<ClientProducts> findClientClientProducts() {
        return clientRepository.findClientProducts();
    }
    public void addClient(Client client) {
        clientRepository.save(client);
    }

    public List<Client> showClients() {
        return clientRepository.findAll();
    }

    public List<Client> showClientsBySalesman(Long salesmanId) {
        return clientRepository.findClientsBySalesmanId(salesmanId);
    }

    public Client showClientById(Long id) {
        return clientRepository.findClientById(id).orElseThrow(() -> new NoSuchElementException("Nie znaleziono klienta"));
    }

    public void updateClient(Client client, Long id)
    {
        Client searchedClient = clientRepository.findById(id).orElseThrow(() -> new NoSuchElementException());

        searchedClient.updateClient(client);

        clientRepository.save(searchedClient);
    }

    public void deleteClient(Long id) {
        clientRepository.deleteById(id);
    }

}