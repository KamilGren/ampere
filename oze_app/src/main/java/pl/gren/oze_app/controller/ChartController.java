package pl.gren.oze_app.controller;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import org.springframework.ui.Model;
import pl.gren.oze_app.Reader.CSVReader;
import pl.gren.oze_app.model.BuildingRequirements;
import pl.gren.oze_app.model.Client;
import pl.gren.oze_app.model.EnergyData;
import pl.gren.oze_app.model.HeatPump;
import pl.gren.oze_app.oldrepository.ClientRepository;
import pl.gren.oze_app.service.BuildingRequirementsService;
import pl.gren.oze_app.service.HeatPumpService;

import java.io.IOException;
import java.util.*;


@Controller
@RequestMapping("/charts")
public class ChartController {

    @Autowired
    HeatPumpService heatPumpService;

    @Autowired
    ClientRepository clientRepository;

    @Autowired
    BuildingRequirementsService buildingRequirementsService;

    CSVReader csvReader = new CSVReader();



    @GetMapping("/create/{heatPumpId}")
    @ResponseBody
    public String createChart(@PathVariable Long heatPumpId, Model model) throws IOException {

        BuildingRequirements buildingRequirements = buildingRequirementsService.getBuildingReqById(1L);
        buildingRequirements.setCWUValue(0.5);
        buildingRequirements.setCOValue(5.09);

        HeatPump heatPump = heatPumpService.getHeatPumpById(heatPumpId).orElseThrow(() -> new NoSuchElementException("Heat pump with id: " + heatPumpId + "not found!"));

        //dane do wykresu
        EnergyData energyData = new EnergyData(heatPump, buildingRequirements);

        // wydajnosć grzewcza lista
        List<Double> heatingEfficiencyList = new ArrayList<>();

        // wydajność grzewcza + grzałka
        List<Double> heatPumpsPowerWithHeaterList = new ArrayList<>();

        // wszystkie dostepne temperatury
        List<Integer> temperatures = energyData.getExternalTemperature();

        // zapotrzebowanie energetyczne
        List<Double> energyDemandValueList = new ArrayList<>();


        Double heatPumpHeatingEfficiency;
        Double heatPumpsPowerWithHeater;

        //wyswietla wszystkie wartosci heatingEfficiency od -20 do 29 stopni dla pompy ciepła ktora zostala wybrana i zapisuje do listy
        for (int i = 0; i < temperatures.size(); i++) {
            heatPumpHeatingEfficiency = energyData.getHeatPumpChartValue(heatPump, temperatures.get(i));
            heatPumpsPowerWithHeater = heatPumpHeatingEfficiency + energyData.getHeaterChart1Value();
            heatingEfficiencyList.add(heatPumpHeatingEfficiency);
            heatPumpsPowerWithHeaterList.add(heatPumpsPowerWithHeater);
            energyDemandValueList.add(energyData.getEnergyDemandValue(temperatures.get(i)));
        }

        JsonArray jsonTemperatures = new JsonArray();
        JsonArray jsonHeatingEfficiencies = new JsonArray();
        JsonArray jsonHeatPumpsPowerWithHeater = new JsonArray();
        JsonArray jsonEnergyDemandValues = new JsonArray();

        JsonObject json = new JsonObject();

        heatingEfficiencyList.forEach(heatingEfficiency -> {
            jsonHeatingEfficiencies.add(heatingEfficiency);
        });
        temperatures.forEach(temperature -> {
            jsonTemperatures.add(temperature);
        });
        heatPumpsPowerWithHeaterList.forEach(heatPumpPowerWithHeater -> {
            jsonHeatPumpsPowerWithHeater.add(heatPumpPowerWithHeater);
        });

        energyDemandValueList.forEach(energyDemandValue -> {
            jsonEnergyDemandValues.add(energyDemandValue);
        });

        json.add("temperatures", jsonTemperatures);
        json.add("heatingEfficiencies", jsonHeatingEfficiencies);
        json.add("heatPumpsPowerWithHeater", jsonHeatPumpsPowerWithHeater);
        json.add("energyDemandValues", jsonEnergyDemandValues);


        // potrzebne poniewaz bedziemy ladowac cala strone heatPumpForm i tam powinny byc zapisane uzyte juz dane z formularza
        model.addAttribute("heatPump", heatPump);
        model.addAttribute("id", heatPump.getId());
        model.addAttribute("producent", heatPump.getProducent());
        model.addAttribute("model", heatPump.getModel());
        model.addAttribute("type", heatPump.getType());

        return json.toString();
    }

    // nie wiem jak inaczej przekazac id na frontend
    // od tego zaczynamy wyswietlanie wykresu
    @GetMapping("/show/{heatPumpId}")
    public String chart(@PathVariable Long heatPumpId, Model model) throws IOException {


//        BuildingRequirements buildingRequirements = buildingRequirementsService.getBuildingReqById(1L);
//        buildingRequirements.setCWUValue(0.5);
//        buildingRequirements.setCOValue(5.09);
//        HeatPump heatPump = heatPumpService.getHeatPumpById(heatPumpId).orElseThrow(() -> new NoSuchElementException("Heat pump with id: " + heatPumpId + "not found!"));
//
//        EnergyData energyData = new EnergyData(heatPump, )

        model.addAttribute("heatPumpId", heatPumpId);


        return "heatPumpChart";
    }

//    @GetMapping("/data/{heatPumpId}")
//    public ResponseEntity<?> getYearChartData(@PathVariable Long heatPumpId) throws IOException {
//
//        BuildingRequirements buildingRequirements = buildingRequirementsService.getBuildingReqById(1L);
//        buildingRequirements.setCWUValue(0.5);
//        buildingRequirements.setCOValue(5.09);
//        HeatPump heatPump = heatPumpService.getHeatPumpById(heatPumpId).orElseThrow(() -> new NoSuchElementException("Heat pump with id: " + heatPumpId + "not found!"));
//
//        EnergyData energyData = new EnergyData(heatPump, buildingRequirements);
//
//        double yearCO = energyData.getYearCO();
//        double yearCWU = energyData.getYearCWU();
//        double SCOP = energyData.getSCOP();
//
//        System.out.println(energyData.getSCOP());
//        System.out.println(energyData.getYearEnergyProduced());
//
//      String jsonData = "{\"yearCO\": " + yearCO + ", \"yearCWU\": " + yearCWU + ", \"SCOP\": " + SCOP + "}";
//
//        return ResponseEntity.ok(jsonData);
//
//    }

    @GetMapping("/year-temperatures/show/{clientId}")
    public String showTemperatureValuesChart(@PathVariable Long clientId, Model model)  {

        model.addAttribute("clientId", clientId);

        return "/charts/yearTemperatureValuesChart";
    }

    // tworzymy wykres temperatur na caly rok przy pobieraniu clientId oraz heatPumpId
    @GetMapping("/year-temperatures/create/{clientId}")
    @ResponseBody
    public String createChartForYearTemperaturesCount(@PathVariable Long clientId) throws IOException {

        // wszystko z klienta pobierzemy
        Client client = clientRepository.findClientById(clientId).orElseThrow(() -> new NoSuchElementException("Brak klienta z takim nr Id: " + clientId));

        BuildingRequirements buildingRequirements = client.getBuildingRequirements();
        buildingRequirements.setCWUValue(0.5);
        buildingRequirements.setCOValue(5.09);

        HeatPump heatPump = heatPumpService.getHeatPumpById(155L).orElseThrow(() -> new NoSuchElementException("Heat pump with id: " + 155 + "not found!"));

        //dane do wykresu
        EnergyData energyData = new EnergyData(heatPump, buildingRequirements);

        List<Integer> temperatures = energyData.getExternalTemperature();
        HashMap<Integer, Double> yearTemperaturesCount = csvReader.getYearTemperaturesforChart();

        JsonArray jsonTemperatures = new JsonArray();
        JsonArray jsonYearTemperaturesCount = new JsonArray();


        List<Double> yearTemperatureValues = new ArrayList<>();
        yearTemperatureValues.addAll(yearTemperaturesCount.values());

        for(Double entry : yearTemperatureValues) {
            jsonYearTemperaturesCount.add(entry);
        }

        temperatures.forEach(temperature -> {
            jsonTemperatures.add(temperature);
        });

        JsonObject json = new JsonObject();

        json.add("temperatures", jsonTemperatures);
        json.add("yearTemperaturesCount", jsonYearTemperaturesCount);

        return json.toString();
    }





}