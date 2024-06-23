package pl.gren.oze_app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import org.springframework.ui.Model;
import pl.gren.oze_app.Reader.CSVReader;
import pl.gren.oze_app.model.BuildingRequirements;
import pl.gren.oze_app.model.EnergyData;
import pl.gren.oze_app.model.HeatPump;
import pl.gren.oze_app.model.db.entity.BuildingInfo;
import pl.gren.oze_app.model.db.entity.Client;
import pl.gren.oze_app.model.db.repository.BuildingInfoRepository;
import pl.gren.oze_app.service.BuildingRequirementsService;
import pl.gren.oze_app.service.ClientService;
import pl.gren.oze_app.service.HeatPumpService;

import java.io.IOException;
import java.util.*;


@Controller
@RequestMapping("/charts")
public class ChartController {

    @Autowired
    HeatPumpService heatPumpService;

    @Autowired
    ClientService clientService;

    @Autowired
    BuildingInfoRepository buildingInfoRepository;


    @Autowired
    BuildingRequirementsService buildingRequirementsService;

    CSVReader csvReader = new CSVReader();



    @GetMapping("/create/{heatPumpId}")
    @ResponseBody
    public ResponseEntity<Object> createChart(@PathVariable Long heatPumpId, Model model) throws IOException {
        BuildingInfo buildingInfo = buildingInfoRepository.findById(1L).orElseThrow();

//        buildingInfo.setCWUValue(0.5);
//        buildingInfo.setCOValue(5.09);

        HeatPump heatPump = heatPumpService.getHeatPumpById(heatPumpId).orElseThrow(() -> new NoSuchElementException("Heat pump with id: " + heatPumpId + "not found!"));

        //dane do wykresu
        EnergyData energyData = new EnergyData(heatPump, buildingInfo);

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

        Map<String, List<?>> jsonResponse = Map.of(
                "temperatures", temperatures,
                "heatingEfficiencies", heatingEfficiencyList,
                "heatPumpsPowerWithHeater", heatPumpsPowerWithHeaterList,
                "energyDemandValues", energyDemandValueList
        );
        return ResponseEntity.ok(jsonResponse);
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
    public ResponseEntity<Object> createChartForYearTemperaturesCount(@PathVariable Long clientId) throws IOException {

        // wszystko z klienta pobierzemy
        Client client = clientService.findById(clientId).orElseThrow(() -> new NoSuchElementException("Brak klienta z takim nr Id: " + clientId));

        BuildingInfo info = new BuildingInfo();
//        BuildingRequirements buildingRequirements = client.getBuildingRequirements();
//        buildingRequirements.setCWUValue(0.5);
//        buildingRequirements.setCOValue(5.09);

        HeatPump heatPump = heatPumpService.getHeatPumpById(155L).orElseThrow(() -> new NoSuchElementException("Heat pump with id: " + 155 + "not found!"));

        //dane do wykresu
        EnergyData energyData = new EnergyData(heatPump, info);

        List<Integer> temperatures = energyData.getExternalTemperature();
        HashMap<Integer, Double> yearTemperaturesCount = csvReader.getYearTemperaturesforChart();

        List<Double> yearTemperatureValues = new ArrayList<>();
        yearTemperatureValues.addAll(yearTemperaturesCount.values());
        Map<String, List<?>> json = new LinkedHashMap<>();
        json.put("temperatures", temperatures);
        json.put("yearTemperaturesCount", yearTemperatureValues);
        return ResponseEntity.ok(json);
    }


}