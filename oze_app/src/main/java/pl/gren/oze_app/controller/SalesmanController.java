package pl.gren.oze_app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.gren.oze_app.exception.Error404;
import pl.gren.oze_app.model.db.entity.Salesman;
import pl.gren.oze_app.model.dto.salesman.SalesmanCreateDto;
import pl.gren.oze_app.model.dto.salesman.SalesmanUpdateDto;
import pl.gren.oze_app.service.SalesmanService;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/salesmen")
public class SalesmanController {
    private final SalesmanService salesmanService;

    public SalesmanController(@Autowired SalesmanService salesmanService) {
        this.salesmanService = salesmanService;
    }

    @GetMapping("")
    public String viewAllSalesmen(Model model) {
        List<Salesman> salesmen = salesmanService.findAll();
        model.addAttribute("salesmen", salesmen);
        return "salesmen/index";
    }

    @GetMapping("/{id}")
    public String viewSalesman(Model model, @PathVariable("id") Long salesmanId) {
        Salesman salesman = salesmanService.findById(salesmanId).orElseThrow(Error404::new);
        model.addAttribute("salesman", salesman);
        model.addAttribute("clients", salesman.getClients());
        model.addAttribute("contracts", salesman.getContracts());
        return "salesmen/view";
    }

    @GetMapping("/create")
    public String createSalesman() {
        return "salesmen/create";
    }

    @PostMapping("/create")
    public String createSalesman(@ModelAttribute SalesmanCreateDto salesmanDto) {
        Salesman salesman = salesmanService.insert(salesmanDto);
        return "redirect:/salesmen/" + salesman.getId();
    }


    @GetMapping("/{id}/edit")
    public String editSalesman(Model model, @PathVariable("id") Long salesmanId) {
        Salesman salesman = salesmanService.findById(salesmanId).orElseThrow(Error404::new);
        model.addAttribute("salesman", salesman);
        model.addAttribute("isAdminSelected", salesman.getRole().equals("ADMIN"));
        return "salesmen/edit";
    }

    @PostMapping("/{id}/edit")
    public String updateSalesman(
            @PathVariable("id") Long salesmanId,
            @ModelAttribute SalesmanUpdateDto salesmanDto) {
        Salesman salesman = salesmanService.findById(salesmanId).orElseThrow(Error404::new);
        salesmanService.update(salesman, salesmanDto);
        return "redirect:/salesmen/" + salesman.getId();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteSalesman(@PathVariable("id") Long salesmanId) {
        Optional<Salesman> $salesman = salesmanService.findById(salesmanId);
        if ($salesman.isPresent()) {
            salesmanService.deleteById(salesmanId);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.badRequest().build();
    }

}