package pl.gren.oze_app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.gren.oze_app.model.db.entity.BuildingInfo;
import pl.gren.oze_app.model.db.entity.Client;
import pl.gren.oze_app.model.db.enums.*;
import pl.gren.oze_app.model.db.repository.BuildingInfoRepository;

import java.util.List;

@Controller
@RequestMapping("buildings")
public class BuildingInfoController {

    private final BuildingInfoRepository buildingInfoRepository;

    @Autowired
    public BuildingInfoController(BuildingInfoRepository buildingInfoRepository) {
        this.buildingInfoRepository = buildingInfoRepository;
    }

    @GetMapping("")
    public String viewAllBuildings(Model model) {
        List<BuildingInfo> buildings = buildingInfoRepository.findAll();
        model.addAttribute("rows", buildings);
        return "buildings/index";
    }

    @GetMapping("/old-demo")
    public String form(Model model) {
        addTypeEnums(model);
        return "building_info/form";
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
