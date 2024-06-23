package pl.gren.oze_app.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.gren.oze_app.model.db.entity.Inverter;
import pl.gren.oze_app.model.db.repository.InverterRepository;

import java.util.List;
import java.util.Optional;

@Service
public class InverterService {

    private final InverterRepository inverterRepository;

    @Autowired
    public InverterService(InverterRepository inverterRepository) {
        this.inverterRepository = inverterRepository;
    }

    public List<Inverter> findAll() {
        return inverterRepository.findAll();
    }

    public Optional<Inverter> findById(Long id) {
        return inverterRepository.findById(id);
    }

    public Inverter save(Inverter inverter) {
        return inverterRepository.save(inverter);
    }

    public void deleteById(Long id) {
        inverterRepository.deleteById(id);
    }
}
