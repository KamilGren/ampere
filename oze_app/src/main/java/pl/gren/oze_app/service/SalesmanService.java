package pl.gren.oze_app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pl.gren.oze_app.model.db.entity.Salesman;
import pl.gren.oze_app.model.db.repository.SalesmanRepository;

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

    public List<Salesman> findAll() {
        return salesmanRepository.findAll();
    }

    public Optional<Salesman> findById(Long id) {
        return salesmanRepository.findById(id);
    }

    public Optional<Salesman> findByEmail(String email) {
        return salesmanRepository.findByEmail(email);
    }

    public Optional<Salesman> findByName(String name) {
        return salesmanRepository.findByName(name);
    }

    public void deleteById(Long id) {
        salesmanRepository.deleteById(id);
    }

    public Salesman save(Salesman salesman) {
        return salesmanRepository.save(salesman);
    }

}
