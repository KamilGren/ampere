package pl.gren.oze_app.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import pl.gren.oze_app.model.CWUBufforTank;
import pl.gren.oze_app.service.CWUBufforTankService;

import java.util.List;


@Controller
@RequestMapping("/CWUBuffors")
public class CWUBufforTankController {

    private final CWUBufforTankService cwuBufforTankService;

    @Autowired
    public CWUBufforTankController(CWUBufforTankService zasobnikCWUService) {
        this.cwuBufforTankService = zasobnikCWUService;
    }

    @GetMapping
    public List<CWUBufforTank> getAllCWUBufforTanks() {
        return cwuBufforTankService.getAllCWUBufforTanks();
    }

    @GetMapping("/{id}")
    public ResponseEntity<CWUBufforTank> getCWUBufforTankById(@PathVariable Long id) {
        CWUBufforTank CWUBufforTank = cwuBufforTankService.getCWUBufforTankById(id);
        return CWUBufforTank != null ? ResponseEntity.ok(CWUBufforTank) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<CWUBufforTank> createCWUBufforTank(@Valid @RequestBody CWUBufforTank CWUBufforTank) {
        CWUBufforTank createdZasobnik = cwuBufforTankService.createCWUBufforTank(CWUBufforTank);
        return ResponseEntity.ok(createdZasobnik);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCWUBufforTank(@PathVariable Long id) {
        cwuBufforTankService.deleteCWUBufforTank(id);
        return ResponseEntity.noContent().build();
    }
}