package pl.gren.oze_app.controller;

import io.github.wimdeblauwe.htmx.spring.boot.mvc.HtmxResponse;
import io.github.wimdeblauwe.htmx.spring.boot.mvc.HxRequest;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.gren.oze_app.model.db.entity.Contract;
import pl.gren.oze_app.model.db.entity.product.HeatPump;
import pl.gren.oze_app.model.db.enums.HeatPumpType;
import pl.gren.oze_app.model.db.repository.ContractRepository;
import pl.gren.oze_app.model.db.repository.product.CentralHeatingBufferTankRepository;
import pl.gren.oze_app.model.db.repository.product.DomesticHotWaterTankRepository;
import pl.gren.oze_app.model.db.repository.product.HeatPumpRepository;
import pl.gren.oze_app.service.HeatPumpService;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("contracts")
@AllArgsConstructor
public class ContractController {

    @Autowired
    private final ContractRepository contractRepository;
    @Autowired
    private final HeatPumpRepository heatPumpRepository;
    @Autowired
    private final HeatPumpService heatPumpService;
    @Autowired
    private final DomesticHotWaterTankRepository cwuRepository;
    @Autowired
    private final CentralHeatingBufferTankRepository coRepository;

    @GetMapping("")
    public String viewAllContracts(Model model) {
        List<Contract> buildings = contractRepository.findAll();
        model.addAttribute("rows", buildings);
        return "contracts/index";
    }

    @GetMapping("/{id}")
    public String viewContract(Model model, @PathVariable Long id) {
        // Add the manufacturers for each main type
        List<String> heatPumpBrands = heatPumpRepository.findAllManufacturers();
        List<String> cwuBrands = cwuRepository.findAllManufacturers();
        List<String> coBrands = coRepository.findAllManufacturers();

        model.addAttribute("heatPumpBrands", heatPumpBrands);
        model.addAttribute("heatPumpTypes", HeatPumpType.getTypes());
        model.addAttribute("cwuBrands", cwuBrands);
        model.addAttribute("coBrands", coBrands);
        model.addAttribute("heatPumpModels", heatPumpRepository.findAll());
        // Need to make API calls to get the heat pumps and products? Maybe just fetch the selected data into the javascript
        // Have a modal for each one
        return "contracts/main";
    }

    @HxRequest
    @GetMapping("/add-heat-pump")
    public HtmxResponse getModalHtmx(Model model, @RequestParam(required = false) String manufacturer, @RequestParam(required = false) Integer typeId) {
        List<HeatPump> heatPumps = heatPumpService.filterByManufacturerAndType(manufacturer, typeId);
        model.addAttribute("heatPumpModels", heatPumps);
        return new HtmxResponse().addTemplate("contracts/main :: #modal-heat-pumps_modelId");
    }

}
