package pl.gren.oze_app.controller;

import org.apache.poi.ss.formula.functions.T;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.gren.oze_app.exception.Error404;
import pl.gren.oze_app.model.CustomJson;
import pl.gren.oze_app.model.Identity32;
import pl.gren.oze_app.model.db.entity.BuildingInfo;
import pl.gren.oze_app.model.db.enums.*;
import pl.gren.oze_app.model.db.repository.BuildingInfoRepository;
import pl.gren.oze_app.model.dto.building.BuildingInfoTableDto;
import pl.gren.oze_app.model.enums.BasementFloorData;
import pl.gren.oze_app.service.BuildingService;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
@RequestMapping("buildings")
public class BuildingInfoController {

    private final BuildingInfoRepository buildingInfoRepository;
    private final BuildingService buildingService;

    @Autowired
    public BuildingInfoController(BuildingInfoRepository buildingInfoRepository, BuildingService buildingService) {
        this.buildingInfoRepository = buildingInfoRepository;
        this.buildingService = buildingService;
    }

    @GetMapping("")
    public String viewAllBuildings(Model model) {
        List<BuildingInfo> buildings = buildingInfoRepository.findAll();
        model.addAttribute("rows", buildings);
        return "buildings/index";
    }

    @GetMapping("/{id}")
    public String viewBuildingInfo(Model model, @PathVariable Long id) {
        BuildingInfo building = buildingInfoRepository.findById(id).orElseThrow(Error404::new);
        model.addAttribute("building", building);
        model.addAttribute("contracts", building.getContract());
        model.addAttribute("client", building.getClient());
        addTypeEnums(model);
        return "buildings/view";
    }

    @PostMapping("/{id}/save")
    public ResponseEntity<Object> saveTable(@PathVariable Long id, @ModelAttribute BuildingInfoTableDto dto) {
        BuildingInfo building = buildingInfoRepository.findById(id).orElseThrow(Error404::new);
        try {
            this.buildingService.saveChanges(building, dto);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/old-demo")
    public String form(Model model) {
        addTypeEnums(model);
        return "building_info/form";
    }

    @GetMapping("/api/enums")
    public ResponseEntity<Object> apiGetEnums() {
        Map<String, Object> enums = new LinkedHashMap<>();
        enums.put("buildingTypes", getCustomJson(BuildingType.getTypes()));
        enums.put("wallTypes", getCustomJson(WallType.getTypes()));
        enums.put("insulationTypes", getCustomJson(InsulationType.getTypes()));
        enums.put("windowGlazingTypes", getCustomJson(WindowGlazingType.getTypes()));
        enums.put("ventTypes", getCustomJson(VentilationType.getTypes()));
        enums.put("fuelTypes", getCustomJson(FuelType.getTypes()));
        enums.put("waterUsageTypes", getCustomJson(WaterUsageType.getTypes()));
        enums.put("basementData", BasementFloorData.toJson());
        return ResponseEntity.ok(enums);
    }

    private <T extends CustomJson & Identity32> Map<Integer, Map<String, Object>> getCustomJson(List<T> json) {
        return json.stream().collect(Collectors.toMap(Identity32::getId, CustomJson::toJson));
    }

    private void addTypeEnums(Model model) {
        model.addAttribute("buildingTypes", BuildingType.getTypes());
        model.addAttribute("wallTypes", WallType.getTypes());
        model.addAttribute("insulationTypes", InsulationType.getTypes());
        model.addAttribute("windowGlazingTypes", WindowGlazingType.getTypes());
        model.addAttribute("ventTypes", VentilationType.getTypes());
        model.addAttribute("fuelTypes", FuelType.getTypes());
        model.addAttribute("waterUsageTypes", WaterUsageType.getTypes());
    }
}
