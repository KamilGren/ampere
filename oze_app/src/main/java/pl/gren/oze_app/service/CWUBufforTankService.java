package pl.gren.oze_app.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.gren.oze_app.model.CWUBufforTank;
import pl.gren.oze_app.repository.CWUBufforRepository;

import java.util.List;

@Service
public class CWUBufforTankService {

    private final CWUBufforRepository cwuBufforRepository;

    @Autowired
    public CWUBufforTankService(CWUBufforRepository CWUBufforRepository) {
        this.cwuBufforRepository = CWUBufforRepository;
    }

    public List<String> getCWUBufforTankNames() {
        return cwuBufforRepository.findCWUBufforNames();
    }

    public CWUBufforTank getCWUBufforTankByName(String name) {
        return cwuBufforRepository.findByName(name);
    }

    public List<CWUBufforTank> getAllCWUBufforTanks() {
        return cwuBufforRepository.findAll();
    }

    public CWUBufforTank getCWUBufforTankById(Long id) {
        return cwuBufforRepository.findById(id).orElse(null);
    }

    public CWUBufforTank createCWUBufforTank(CWUBufforTank CWUBufforTank) {
        return cwuBufforRepository.save(CWUBufforTank);
    }

    public void deleteCWUBufforTank(Long id) {
        cwuBufforRepository.deleteById(id);
    }
}