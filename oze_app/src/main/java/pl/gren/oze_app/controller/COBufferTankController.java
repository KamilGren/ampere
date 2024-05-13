package pl.gren.oze_app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import pl.gren.oze_app.model.COBufferTank;
import pl.gren.oze_app.model.Client;
import pl.gren.oze_app.model.ClientProducts;
import pl.gren.oze_app.model.HeatPump;
import pl.gren.oze_app.repository.ClientRepository;
import pl.gren.oze_app.service.COBufferTankService;

import java.util.List;
import java.util.NoSuchElementException;

@Controller
@RequestMapping("/COBuffors")
public class COBufferTankController {

    private COBufferTankService COBufferTankService;
    private final ClientRepository clientRepository;


    @Autowired
    public COBufferTankController(COBufferTankService COBufferTankService, ClientRepository clientRepository) {
        this.COBufferTankService = COBufferTankService;
        this.clientRepository = clientRepository;
    }

    @GetMapping("/all")
    public List<COBufferTank> getAllZasobnikiCO() {
        return COBufferTankService.getAllCOBufferTanks();
    }

    @GetMapping("/{id}")
    public COBufferTank getZasobnikCOById(@PathVariable Long id) {
        return COBufferTankService.getCOBufferTankById(id);
    }

    @PostMapping("/add")
    public COBufferTank addZasobnikCO(@RequestBody COBufferTank CObufferTank) {
        return COBufferTankService.saveCOBufferTank(CObufferTank);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteZasobnikCO(@PathVariable Long id) {
        COBufferTankService.deleteCOBufferTank(id);
    }


}