package pl.gren.oze_app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pl.gren.oze_app.model.Salesman;
import pl.gren.oze_app.oldrepository.SalesmanRepository;

import java.util.List;
import java.util.Optional;

@Service
public class SalesmanService {

    private final SalesmanRepository salesmanRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public SalesmanService(SalesmanRepository salesmanRepository, PasswordEncoder passwordEncoder) {
        this.salesmanRepository = salesmanRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public List<Salesman> getAllSalesmen() {
        return salesmanRepository.findAll();
    }

    public Optional<Salesman> findSalesmanByUsername(String username) {
        return salesmanRepository.findByName(username);
    }

//    public Salesman showSalesmanByClientId(Long clientId) {
//        return salesmanRepository.findSalesmanByClientId(clientId);
//    }

    public Optional<Salesman> findSalesmanById(Long id) {
        return salesmanRepository.findById(id);
    }

    public Optional<Salesman> findSalesmanByEmail(String email) {
        return salesmanRepository.findByEmail(email);
    }

    public void updateSalesman(Salesman salesman, Long id)
    {
        Salesman searchedSalesman = salesmanRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Brak handlowca o takim numerze id: " + id));

        searchedSalesman.updateSalesman(salesman);
        //searchedSalesman.setPassword(passwordEncoder.encode(salesman.getPassword()));

        salesmanRepository.save(searchedSalesman);
    }

    public void deleteSalesmanById(Long id) {
        salesmanRepository.deleteById(id);
    }

    public Salesman saveSalesman(Salesman salesman) {
        salesman.setPassword(passwordEncoder.encode(salesman.getPassword()));
        return salesmanRepository.save(salesman);
    }
}
