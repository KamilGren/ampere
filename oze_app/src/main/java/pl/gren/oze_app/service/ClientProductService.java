package pl.gren.oze_app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.gren.oze_app.model.ClientProducts;
import pl.gren.oze_app.oldrepository.ClientProductRepository;

@Service
public class ClientProductService {

    ClientProductRepository clientProductRepository;

    @Autowired
    public ClientProductService(ClientProductRepository clientProductRepository) {
        this.clientProductRepository = clientProductRepository;
    }

    public void addClientProduct(ClientProducts clientProducts) {
        clientProductRepository.save(clientProducts);
    }

    public void updateClientProducts(ClientProducts clientProducts, Long id)
    {
        ClientProducts updatedClientProducts = clientProductRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Brak client product z takim ID " + clientProducts.getId()));

        updatedClientProducts.updateClientProducts(clientProducts);

        clientProductRepository.save(updatedClientProducts);
    }
}

