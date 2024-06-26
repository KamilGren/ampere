package pl.gren.oze_app.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.gren.oze_app.model.*;
import pl.gren.oze_app.repository.BuildingRequirementsRepository;
import pl.gren.oze_app.repository.HeatPumpRepository;
import pl.gren.oze_app.service.ClientService;

import java.io.IOException;
import java.util.*;

@Controller
@RequestMapping("/energydata")
public class EnergyDataController {


    ClientService clientService;
    HeatPumpRepository heatPumpRepository;
    BuildingRequirementsRepository buildingRequirementsRepository;

    @Autowired
    public EnergyDataController(ClientService clientService, HeatPumpRepository heatPumpRepository, BuildingRequirementsRepository buildingRequirementsRepository) {
        this.clientService = clientService;
        this.heatPumpRepository = heatPumpRepository;
        this.buildingRequirementsRepository = buildingRequirementsRepository;
    }

    @GetMapping("/temperatures/{clientId}")
    public String getData(@PathVariable Long clientId, Model model) throws IOException {

        Client client = clientService.showClientById(clientId);

        // dodanie pompy byle jakiej - pozniej bedzie to wczytywane z uzytkownika
        HeatPump heatPump = heatPumpRepository.findById(145L).orElseThrow(() -> new NoSuchElementException("Nie ma takiej pompy"));

        Map<String, MonthData> months = new LinkedHashMap<>();


        List<String> orderedMonths = new ArrayList<>();
        orderedMonths.add("STYCZEŃ");
        orderedMonths.add("LUTY");
        orderedMonths.add("MARZEC");
        orderedMonths.add("KWIECIEŃ");
        orderedMonths.add("MAJ");
        orderedMonths.add("CZERWIEC");
        orderedMonths.add("LIPIEC");
        orderedMonths.add("SIERPIEŃ");
        orderedMonths.add("WRZESIEŃ");
        orderedMonths.add("PAŹDZIERNIK");
        orderedMonths.add("LISTOPAD");
        orderedMonths.add("GRUDZIEŃ");

        if (client.getBuildingRequirements() != null) {

            // ladujemy wszystkie dane dla konkretnej pompy ciepla oraz za pomoca wlasciwosci budynku
            EnergyData energyData = new EnergyData(heatPump, client.getBuildingRequirements());

            // adding CO and CWU values to given month
            for (String month : orderedMonths) {
                months.put(month, new MonthData(energyData.getMonthCO(month), energyData.getMonthCWU(month), client.getBuildingRequirements().getKwHCost()));
                System.out.println("Miesiac: " + month);
            }

            double yearOperationCO = Math.round(energyData.getYearCO() * 100.0) / 100.0;
            double yearOperationCWU = Math.round(energyData.getYearCWU() * 100.0) / 100.0;
            double yearOperationCost = yearOperationCO + yearOperationCWU;


            model.addAttribute("orderedMonths", orderedMonths);
            model.addAttribute("heatPump", heatPump);
            model.addAttribute("months", months);
            model.addAttribute("yearOperatingCost", yearOperationCost);
            model.addAttribute("yearOperatingCO", yearOperationCO);
            model.addAttribute("yearOperatingCWU", yearOperationCWU);
            model.addAttribute("kwHCost", client.getBuildingRequirements().getKwHCost());

            System.out.println("Hej, jestem z: " + client.getBuildingRequirements().getLocation());

            return "dataToExport";

        }
        else {

            model.addAttribute("orderedMonths", orderedMonths);
            model.addAttribute("months", Collections.emptyMap());
            model.addAttribute("yearOperatingCost", 0); // Ustawienie kosztu rocznego na 0, lub inna wartość, którą chcesz przekazać

            System.out.println("Hej, jestem tutaj!");

            return "dataToExport";

        }

    }




}
