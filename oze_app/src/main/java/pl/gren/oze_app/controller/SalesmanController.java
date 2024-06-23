package pl.gren.oze_app.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.gren.oze_app.model.db.entity.Client;
import pl.gren.oze_app.model.db.entity.Salesman;
import pl.gren.oze_app.service.SalesmanService;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Controller
@RequestMapping("/salesmen")
public class SalesmanController {

    private final SalesmanService salesmanService;

    @Autowired
    public SalesmanController(SalesmanService salesmanService) {
        this.salesmanService = salesmanService;
    }

    @GetMapping("/")
    public String getAllSalesmen(Model model) {
        List<Salesman> salesmen = salesmanService.findAll();
        model.addAttribute("salesmen", salesmen);
        return "forms/showSalesmen";
    }


    @GetMapping("/{id}")
    public String getSalesmanById(@PathVariable("id") Long id, Model model) {
        Optional<Salesman> salesmanOptional = salesmanService.findById(id);
        if (salesmanOptional.isPresent()) {
            Salesman salesman = salesmanOptional.get();
            model.addAttribute("salesman", salesman);
            return "forms/salesmanDetails";
        } else {
            return "public.error/404";
        }
    }

    @GetMapping("/add")
    public String showAddSalesmanForm() {
        return "forms/addSalesman";
    }

    @PostMapping("/add")
    public String addSalesman(@Valid Salesman salesman) {
        salesmanService.save(salesman);
        System.out.println("imie salesman: " + salesman.getFirstName());
        return "redirect:/salesmen/";
    }

    @GetMapping("/edit/{id}")
    public String showUpdateForm(@PathVariable("id") long id, Model model) {
        Salesman salesman= salesmanService.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Zły numer id hamdlowca:" + id));
        model.addAttribute("salesman", salesman);
        return "forms/editSalesman";
    }

    @PostMapping("/edit/{id}")
    public String editSalesman(@PathVariable Long id, @ModelAttribute("salesman") Salesman salesman) {
        salesmanService.save(salesman);
        return "redirect:/salesmen/";
    }

    @GetMapping("/delete/{id}")
    public String deleteSalesman(@PathVariable("id") Long id) {
        salesmanService.deleteById(id);
        return "redirect:/salesmen/";
    }

    @GetMapping("/clients/{id}")
    public String salesmanClients(@PathVariable("id") Long id, Model model) {

        Salesman salesman = salesmanService.findById(id).orElseThrow(() -> new IllegalArgumentException("Brak handlowca o tym numerze id: " + id));
        Set<Client> clients = salesman.getClients();

        model.addAttribute("salesmanClients", clients);
        model.addAttribute("salesmanId", id);

        System.out.println("Salesman " + clients);

        return "forms/salesmanClients";
    }
}