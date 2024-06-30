package pl.gren.oze_app.controller;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.gren.oze_app.model.db.repository.ContractRepository;
import pl.gren.oze_app.model.db.repository.product.CentralHeatingBufferTankRepository;
import pl.gren.oze_app.model.db.repository.product.DomesticHotWaterTankRepository;
import pl.gren.oze_app.model.db.repository.product.HeatPumpRepository;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/contracts")
public class ContractRestController {
    @Autowired
    private final ContractRepository contractRepository;
    @Autowired
    private final HeatPumpRepository heatPumpRepository;
    @Autowired
    private final DomesticHotWaterTankRepository cwuRepository;
    @Autowired
    private final CentralHeatingBufferTankRepository coRepository;

    public ResponseEntity<Object> getDatasets() {
        // Return the heat pump products,
        // CWU products
        // CO products
        // misc products
        List<String> heatPumpBrands = heatPumpRepository.findAllManufacturers();
        List<String> cwuBrands = cwuRepository.findAllManufacturers();
        List<String> coBrands = coRepository.findAllManufacturers();

        return null;

    }

    // TODO api to get current config
}
