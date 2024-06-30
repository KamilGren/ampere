package pl.gren.oze_app.controller;

import io.github.wimdeblauwe.htmx.spring.boot.mvc.HtmxResponse;
import io.github.wimdeblauwe.htmx.spring.boot.mvc.HxRequest;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.gren.oze_app.model.db.entity.Contract;
import pl.gren.oze_app.model.db.entity.product.CentralHeatingBufferTank;
import pl.gren.oze_app.model.db.entity.product.DomesticHotWaterTank;
import pl.gren.oze_app.model.db.entity.product.HeatPump;
import pl.gren.oze_app.model.db.enums.HeatPumpType;
import pl.gren.oze_app.model.db.repository.ContractRepository;
import pl.gren.oze_app.model.db.repository.product.HeatPumpRepository;
import pl.gren.oze_app.model.dto.contracts.ModelDTO;
import pl.gren.oze_app.service.COService;
import pl.gren.oze_app.service.CWUService;
import pl.gren.oze_app.service.HeatPumpService;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

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
    private final CWUService cwuService;
    @Autowired
    private final COService coService;

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
        Set<String> cwuBrands = cwuService.findDistinctManufacturers();
        Set<String> coBrands = coService.findDistinctManufacturers();

        model.addAttribute("heatPumpBrands", heatPumpBrands);
        model.addAttribute("heatPumpTypes", HeatPumpType.getTypes());
        model.addAttribute("cwuBrands", cwuBrands);
        model.addAttribute("coBrands", coBrands);
        model.addAttribute("heatPumpModels", heatPumpRepository.findAll());
        model.addAttribute("cwuModels", cwuService.findAll());
        model.addAttribute("coModels", coService.findAll());
        model.addAttribute("hiddenId", id);
        // Need to make API calls to get the heat pumps and products? Maybe just fetch the selected data into the javascript
        // Have a modal for each one
        return "contracts/main";
    }

    @HxRequest
    @GetMapping("/add-heat-pump")
    public HtmxResponse handleAddHeatPumpFilter(Model model, @RequestParam(required = false) String manufacturer, @RequestParam(required = false) Integer typeId) {
        List<HeatPump> heatPumps = heatPumpService.filterByManufacturerAndType(manufacturer, typeId);
        model.addAttribute("heatPumpModels", heatPumps);
        return new HtmxResponse().addTemplate("contracts/main :: #modal-heat-pumps_modelId");
    }

    @HxRequest
    @GetMapping("/add-cwu")
    public HtmxResponse handleAddCwuFilter(Model model, @RequestParam(required = false) String manufacturer) {
        List<DomesticHotWaterTank> cwuModels = cwuService.filterByManufacturer(manufacturer);
        model.addAttribute("cwuModels", cwuModels);
        return new HtmxResponse().addTemplate("contracts/main :: #modal-cwu_modelId");
    }

    @HxRequest
    @GetMapping("/add-co")
    public HtmxResponse handleAddCoFilter(Model model, @RequestParam(required = false) String manufacturer) {
        List<CentralHeatingBufferTank> coModels = coService.filterByManufacturer(manufacturer);
        model.addAttribute("coModels", coModels);
        return new HtmxResponse().addTemplate("contracts/main :: #modal-co_modelId");
    }

    // TODO save the added values to whichever active contract

    @PostMapping("/{id}/add-heat-pump")
    public ResponseEntity<HeatPump> handleAddHeatPump(@PathVariable Long id, @RequestBody ModelDTO model) {
        return heatPumpService.findById(model.getModelId())
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @PostMapping("/{id}/add-cwu")
    public ResponseEntity<DomesticHotWaterTank> handleAddCwu(@PathVariable Long id, @RequestBody ModelDTO model) {
        return cwuService.findById(model.getModelId())
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @PostMapping("/{id}/add-co")
    public ResponseEntity<CentralHeatingBufferTank> handleAddCo(@PathVariable Long id, @RequestBody ModelDTO model) {
        return coService.findById(model.getModelId())
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }




}
