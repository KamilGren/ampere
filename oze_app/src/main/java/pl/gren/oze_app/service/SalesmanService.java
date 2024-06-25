package pl.gren.oze_app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pl.gren.oze_app.model.db.entity.Salesman;
import pl.gren.oze_app.model.db.repository.SalesmanRepository;
import pl.gren.oze_app.model.dto.salesman.SalesmanCreateDto;
import pl.gren.oze_app.model.dto.salesman.SalesmanUpdateDto;

import java.util.List;
import java.util.Optional;

@Service
public class SalesmanService {

    private final SalesmanRepository salesmanRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    public SalesmanService(SalesmanRepository salesmanRepository) {
        this.salesmanRepository = salesmanRepository;
    }

    public List<Salesman> findAll() {
        return salesmanRepository.findAll();
    }

    public Optional<Salesman> findById(Long id) {
        return salesmanRepository.findById(id);
    }

    public Optional<Salesman> findByUsername(String username) {
        return salesmanRepository.findByUsername(username);
    }

    public Optional<Salesman> findByEmail(String email) {
        return salesmanRepository.findByEmail(email);
    }

    public Optional<Salesman> findByName(String name) {
        return salesmanRepository.findByFirstName(name);
    }

    public void deleteById(Long id) {
        salesmanRepository.deleteById(id);
    }

    public Salesman save(Salesman salesman) {
        return salesmanRepository.save(salesman);
    }

    public Salesman insert(SalesmanCreateDto salesmanDto) {
        // TODO validation somewhere or with @Valid
        Salesman salesman = new Salesman();
        salesman.setFirstName(salesmanDto.getFirstName());
        salesman.setLastName(salesmanDto.getLastName());
        salesman.setUsername(salesmanDto.getUsername());
        salesman.setEmail(salesmanDto.getEmail());
        salesman.setPasswordHash(passwordEncoder.encode(salesmanDto.getPassword()));
        salesman.setRole(roleIdToString(salesmanDto.getRole()));
        return salesmanRepository.save(salesman);
    }

    private String roleIdToString(int id) {
        return switch (id) {
            case 1 -> "HANDLOWIEC";
            case 2 -> "ADMIN";
            default -> throw new IllegalArgumentException();
        };
    }

    public void update(Salesman salesman, SalesmanUpdateDto salesmanDto) {
        salesman.setFirstName(salesmanDto.getFirstName());
        salesman.setLastName(salesmanDto.getLastName());
        salesman.setUsername(salesmanDto.getUsername());
        salesman.setEmail(salesmanDto.getEmail());
        salesman.setRole(roleIdToString(salesmanDto.getRole()));
        salesmanRepository.save(salesman);
    }
}
