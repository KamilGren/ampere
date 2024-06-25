package pl.gren.oze_app.controller;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.gren.oze_app.exception.Error404;
import pl.gren.oze_app.model.db.entity.Salesman;
import pl.gren.oze_app.model.db.repository.ContractRepository;
import pl.gren.oze_app.model.dto.SalesmanCreateDto;
import pl.gren.oze_app.model.dto.SalesmanUpdateDto;
import pl.gren.oze_app.service.ClientService;
import pl.gren.oze_app.service.SalesmanService;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Controller
@RequestMapping("/salesmen")
public class SalesmanController {

    @Autowired private final SalesmanService salesmanService;
    @Autowired private final ContractRepository contractRepository;
    @Autowired private final ClientService clientService;

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
    public String createSalesman(Model model) {
        return "salesmen/create";
    }

    @PostMapping("/create")
    public String submitSalesman(@ModelAttribute SalesmanCreateDto salesmanDto) {
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
    public String saveSalesman(
            @PathVariable("id") Long salesmanId,
            @ModelAttribute SalesmanUpdateDto salesmanDto) {
        Salesman salesman = salesmanService.findById(salesmanId).orElseThrow(Error404::new);
        salesmanService.update(salesman, salesmanDto);
        return "redirect:/salesmen/" + salesman.getId();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> saveSalesman(@PathVariable("id") Long salesmanId) {
        Optional<Salesman> $salesman = salesmanService.findById(salesmanId);
        if ($salesman.isPresent()) {
            salesmanService.deleteById(salesmanId);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.badRequest().build();
    }

}