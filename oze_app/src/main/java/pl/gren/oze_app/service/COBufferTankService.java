package pl.gren.oze_app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.gren.oze_app.model.COBufferTank;
import pl.gren.oze_app.repository.COBufferTankRepository;

import java.util.List;

@Service
public class COBufferTankService {

    @Autowired
    private COBufferTankRepository coBufferTankRepository;

    public List<COBufferTank> getAllCOBufferTanks() {
        return coBufferTankRepository.findAll();
    }

    public COBufferTank getCOBufferTankById(Long id) {
        return coBufferTankRepository.findById(id).orElse(null);
    }

    public COBufferTank getCOBufferTankByName(String name) {
        return coBufferTankRepository.findByName(name);
    }

    public COBufferTank saveCOBufferTank(COBufferTank coBufferTank) {
        return coBufferTankRepository.save(coBufferTank);
    }

    public List<String> getCOBufferTankNames() {
        return coBufferTankRepository.findCOBufferNames();
    }

    public void deleteCOBufferTank(Long id) {
        coBufferTankRepository.deleteById(id);
    }
}