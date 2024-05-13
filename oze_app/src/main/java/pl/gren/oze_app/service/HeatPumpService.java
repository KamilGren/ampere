package pl.gren.oze_app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.gren.oze_app.model.HeatPump;
import pl.gren.oze_app.repository.HeatPumpRepository;


import java.util.List;
import java.util.Optional;

@Service
public class HeatPumpService {

    private final HeatPumpRepository heatPumpRepository;

    @Autowired
    public HeatPumpService(HeatPumpRepository heatPumpRepository) {
        this.heatPumpRepository = heatPumpRepository;
    }

    public List<HeatPump> getAllHeatPump() {
        return heatPumpRepository.findAll();
    }

    public List<String> getHeatPumpsProducents() { return heatPumpRepository.findHeatPumpsProducents(); }

    public List<String> getHeatPumpsModels() { return heatPumpRepository.findHeatPumpsModels(); }

    public List<String> getHeatPumpModelsByProducentAndType(String producent, String type) { return heatPumpRepository.findHeatPumpModelsByProducentAndType(producent, type); }

    public HeatPump getHeatPumpByProducentModelType(String producent, String model, String type) { return heatPumpRepository.getHeatPumpByProducentAndModelAndType(producent, model, type); }

    public Optional<HeatPump> getHeatPumpById(Long id) {
        return heatPumpRepository.findById(id);
    }

    public List<HeatPump> getHeatPumpByProducent(String producent) {
        return heatPumpRepository.findHeatPumpByProducent(producent);
    }

    public List<HeatPump> getHeatPumpByModelAndProducent(String producent, String model) {
        return heatPumpRepository.findHeatPumpByProducentAndModel(producent, model);
    }

    public HeatPump saveHeatPump(HeatPump heatPomp) {
        return heatPumpRepository.save(heatPomp);
    }

    public void deleteHeatPump(Long id) {
        heatPumpRepository.deleteById(id);
    }
}