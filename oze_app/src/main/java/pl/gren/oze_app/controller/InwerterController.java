package pl.gren.oze_app.controller;


import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.gren.oze_app.model.db.entity.Inverter;
import pl.gren.oze_app.service.InverterService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/inwertery")
public class InwerterController {

    private final InverterService inverterService;

    @Autowired
    public InwerterController(InverterService inverterService) {
        this.inverterService = inverterService;
    }

    @GetMapping("/all")
    public List<Inverter> getAll() {
        return inverterService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Inverter> getById(@PathVariable Long id) {
        Optional<Inverter> inverter = inverterService.findById(id);
        return inverter.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/add")
    public Inverter addInverter(@Valid @RequestBody Inverter inverter) {
        return inverterService.save(inverter);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteInverter(@PathVariable Long id) {
        inverterService.deleteById(id);
    }
}
