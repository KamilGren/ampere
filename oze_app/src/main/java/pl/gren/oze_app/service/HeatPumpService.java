package pl.gren.oze_app.service;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.gren.oze_app.model.db.entity.product.DomesticHotWaterTank;
import pl.gren.oze_app.model.db.entity.product.HeatPump;
import pl.gren.oze_app.model.db.enums.HeatPumpType;
import pl.gren.oze_app.model.db.repository.product.HeatPumpRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class HeatPumpService {
    @Autowired private final HeatPumpRepository heatPumpRepository;

    public Optional<HeatPump> findById(Long id) {
        return heatPumpRepository.findById(id);
    }

    public List<HeatPump> filterByManufacturerAndType(String manufacturer, Integer typeId) {
        boolean hasBrand = !("".equals(manufacturer));
        boolean hasType = typeId != null;
        if (hasType) {
            var type = HeatPumpType.findById(typeId).orElseThrow();
            if (hasBrand) {
                return heatPumpRepository.findAllByManufacturerAndHeatPumpType(manufacturer, type);
            } else {
                return heatPumpRepository.findAllByHeatPumpType(type);
            }
        } else if (hasBrand) {
            return heatPumpRepository.findAllByManufacturer(manufacturer);
        } else {
            return heatPumpRepository.findAll();
        }
    }
}
