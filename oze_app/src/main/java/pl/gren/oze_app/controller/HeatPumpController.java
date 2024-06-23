package pl.gren.oze_app.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.gren.oze_app.model.HeatPump;
import pl.gren.oze_app.model.HeatPumpTypes;
import pl.gren.oze_app.oldrepository.ClientRepository;
import pl.gren.oze_app.service.HeatPumpService;

import java.util.*;

@Controller
@RequestMapping("/heatpumps/")
public class HeatPumpController {

    private final HeatPumpService heatPumpService;
    private final ClientRepository clientRepository;

    @Autowired
    public HeatPumpController(HeatPumpService heatPumpService, ClientRepository clientRepository) {
        this.heatPumpService = heatPumpService;
        this.clientRepository = clientRepository;
    }

    // nie działa, brak template
    @GetMapping
    public List<HeatPump> getAllHeatPump() {
        return heatPumpService.getAllHeatPump();
    }

    // działa
    @GetMapping("/search/{id}")
    public String showHeatPumpParams(@PathVariable Long id, Model model) {
        HeatPump heatPump = heatPumpService.getHeatPumpById(id).orElseThrow(() -> new NoSuchElementException("Brak tej pompy!"));
        model.addAttribute("heatPump", heatPump); // Dodaj obiekt do Model
        return "heatPumpParams"; // Wyświetl widok
    }

    @GetMapping("/showForm")
    public String showHeatPumpForm(Model model) {

        model.addAttribute("heatPump", new HeatPump());

        List<String> heatPumpTypes = new ArrayList<>();
        heatPumpTypes.add(HeatPumpTypes.SPLIT.toString());
        heatPumpTypes.add(HeatPumpTypes.MONOBLOK.toString());
        heatPumpTypes.add(HeatPumpTypes.ALL_IN_ONE.toString());


        List<String> heatPumpsProducents = heatPumpService.getHeatPumpsProducents();
        model.addAttribute("producents", heatPumpsProducents);
        model.addAttribute("types", heatPumpTypes);

        return "heatPumpForm";
    }

    @GetMapping("/{id}")
    public ResponseEntity<HeatPump> getHeatPumpById(@PathVariable Long id) {
        Optional<HeatPump> heatPump = heatPumpService.getHeatPumpById(id);
        return heatPump.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }


    @PostMapping
    public ResponseEntity<HeatPump> saveHeatPump(@Valid @RequestBody HeatPump heatPomp) {
        HeatPump savedHeatPump = heatPumpService.saveHeatPump(heatPomp);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedHeatPump);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteHeatPump(@PathVariable Long id) {
        heatPumpService.deleteHeatPump(id);
        return ResponseEntity.noContent().build();
    }


//    @GetMapping("/pumps/")
//    public String findPumpByProducentModelTyp(Model model2, @RequestParam String producent, @RequestParam String model, @RequestParam String type) {
//
//        HeatPump heatPump = heatPumpService.getHeatPumpByProducentModelTyp(producent, model, type);
//
//        // Przekaz wyniki do modelu
//        model2.addAttribute("heatPump", heatPump);
//
//        // Zwróć nazwę widoku Thymeleaf
//        return "heatPumpParams";
//    }

    @GetMapping("/getProducents")
    public String getHeatPumpByProducent(Model model) {
        List<String> heatPumpsProducents = heatPumpService.getHeatPumpsProducents();
        model.addAttribute("heat_pump", heatPumpsProducents);

        System.out.println(heatPumpsProducents.get(0));
        System.out.println(heatPumpsProducents.get(1));
        System.out.println(heatPumpsProducents.get(2));

        return "heatPumpParams";
    }

//    @GetMapping("/getModels")
//    public String getHeatPumpModels(Model model1) {
//        List<String> heatPumpsModels = heatPumpService.getHeatPumpsModels();
//        model1.addAttribute("heat_pump", heatPumpsModels);
//
//        System.out.println(heatPumpsModels.get(0));
//        System.out.println(heatPumpsModels.get(1));
//        System.out.println(heatPumpsModels.get(2));
//
//        return "heatPumpParams";
//    }


    @GetMapping("/getModels")
    public ResponseEntity<?> getHeatPumpModelsByProducentAndType(@RequestParam String producent, @RequestParam String type) {


        if (producent != null && type != null) {
            List<String> heatPumpsModels = heatPumpService.getHeatPumpModelsByProducentAndType(producent, type);
            System.out.println("Wyświetlam modele pomp");
            return ResponseEntity.ok().body(heatPumpsModels);
        } else {

            System.out.println("Producent lub typ są puste");
            return ResponseEntity.badRequest().body("Producent or model are empty!");
        }
    }


    @GetMapping("/searchModel")
    public String searchHeatPumpType(@RequestParam("producent") String producent,
                                 @RequestParam("type") String type,
                                 Model model1) {

        List<String> heatPumps = heatPumpService.getHeatPumpModelsByProducentAndType(producent, type);
        model1.addAttribute("heatPumpTypes", heatPumps);

        return "heatPumpTypes";
    }


    @GetMapping("/find-heatpump")
    public String searchHeatPump(@RequestParam("producent") String producent,
                                         @RequestParam("model") String model,
                                         @RequestParam("type") String type, Model model1)
                                          {

        HeatPump heatPump = heatPumpService.getHeatPumpByProducentModelType(producent, model, type);
        Long id = heatPump.getId();
        model1.addAttribute("heatPump", heatPump);
        model1.addAttribute("id", id);
        model1.addAttribute("producent", producent);
        model1.addAttribute("model", model);
        model1.addAttribute("type", type);

        System.out.println("Power tej pompy ciepla dla sprawdzenia: " + heatPump.getPower());

        return "heatPumpForm";
    }



}