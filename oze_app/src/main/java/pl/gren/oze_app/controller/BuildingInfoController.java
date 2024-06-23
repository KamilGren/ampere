package pl.gren.oze_app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import pl.gren.oze_app.model.db.enums.*;

@Controller
public class BuildingInfoController {

    @GetMapping("/building-info/new")
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
