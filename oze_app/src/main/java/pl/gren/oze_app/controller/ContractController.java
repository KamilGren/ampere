package pl.gren.oze_app.controller;

import io.github.wimdeblauwe.htmx.spring.boot.mvc.HtmxResponse;
import io.github.wimdeblauwe.htmx.spring.boot.mvc.HxRequest;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.gren.oze_app.exception.ApiError400;
import pl.gren.oze_app.exception.Error404;
import pl.gren.oze_app.model.db.entity.Contract;
import pl.gren.oze_app.model.db.entity.embedded.ProductQuantityId;
import pl.gren.oze_app.model.db.entity.product.CentralHeatingBufferTank;
import pl.gren.oze_app.model.db.entity.product.DomesticHotWaterTank;
import pl.gren.oze_app.model.db.entity.product.HeatPump;
import pl.gren.oze_app.model.db.entity.product.Product;
import pl.gren.oze_app.model.db.entity.product.quantity.HeatPumpQuantity;
import pl.gren.oze_app.model.db.enums.HeatPumpType;
import pl.gren.oze_app.model.db.repository.ContractRepository;
import pl.gren.oze_app.model.db.repository.product.HeatPumpRepository;
import pl.gren.oze_app.model.db.repository.product.quantity.HeatPumpQuantityRepository;
import pl.gren.oze_app.model.dto.contracts.ModelDTO;
import pl.gren.oze_app.service.COService;
import pl.gren.oze_app.service.CWUService;
import pl.gren.oze_app.service.ContractService;
import pl.gren.oze_app.service.HeatPumpService;

import java.util.*;

@Controller
@RequestMapping("contracts")
@AllArgsConstructor
public class ContractController {

    @Autowired
    private final ContractRepository contractRepository;
    @Autowired
    private final ContractService contractService;
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
        Contract contract = contractRepository.findById(id).orElseThrow(Error404::new);

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

    @GetMapping("/{id}/api/products")
    public ResponseEntity<Map<String, Collection<?>>> getProducts(@PathVariable Long id) {
        Contract contract = contractRepository.findById(id).orElseThrow(ApiError400::new);
        Map<String, Collection<?>> productMap =  new LinkedHashMap<>();
        productMap.put("heat-pumps", contract.getHeatPumps());
        productMap.put("cwus", contract.getCwuTanks());
        productMap.put("cos", contract.getCoBuffers());
        productMap.put("others", contract.getOtherProducts());
        return ResponseEntity.ok(productMap);
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
        Contract contract = contractRepository.findById(id).orElseThrow(ApiError400::new);
        HeatPump pump = heatPumpService.findById(model.getModelId()).orElseThrow(ApiError400::new);
        contractService.addHeatPump(contract, pump);
        return ResponseEntity.ok(pump);
    }

    @PostMapping("/{id}/add-cwu")
    public ResponseEntity<DomesticHotWaterTank> handleAddCwu(@PathVariable Long id, @RequestBody ModelDTO model) {
        Contract contract = contractRepository.findById(id).orElseThrow(ApiError400::new);
        var cwuTank = cwuService.findById(model.getModelId()).orElseThrow(ApiError400::new);
        contractService.addCwuTank(contract, cwuTank);
        return ResponseEntity.ok(cwuTank);
    }

    @PostMapping("/{id}/add-co")
    public ResponseEntity<CentralHeatingBufferTank> handleAddCo(@PathVariable Long id, @RequestBody ModelDTO model) {
        Contract contract = contractRepository.findById(id).orElseThrow(ApiError400::new);
        var coBuffer = coService.findById(model.getModelId()).orElseThrow(ApiError400::new);
        contractService.addCoBuffer(contract, coBuffer);
        return ResponseEntity.ok(coBuffer);
    }

    @DeleteMapping("/{id}/remove/{type}")
    public ResponseEntity<?> removeProduct(@PathVariable Long id, @PathVariable String type, @RequestParam Long productId) {
        Contract contract = contractRepository.findById(id).orElseThrow(ApiError400::new);
        switch (type) {
            case "heat-pump" -> contractService.removeHeatPump(id, productId);
            case "cwu" -> contractService.removeCwu(id, productId);
            case "co" -> contractService.removeCo(id, productId);
            default -> throw new ApiError400();
        }
        return ResponseEntity.ok().build();
    }

}
