package pl.gren.oze_app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import pl.gren.oze_app.model.COBufferTank;
import pl.gren.oze_app.oldrepository.ClientRepository;
import pl.gren.oze_app.service.COBufferTankService;

import java.util.List;

@Controller
@RequestMapping("/COBuffors")
public class COBufferTankController {

    private COBufferTankService COBufferTankService;


    @Autowired
    public COBufferTankController(COBufferTankService COBufferTankService) {
        this.COBufferTankService = COBufferTankService;
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