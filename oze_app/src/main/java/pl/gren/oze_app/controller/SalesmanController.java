package pl.gren.oze_app.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.gren.oze_app.model.Client;
import pl.gren.oze_app.model.HeatPump;
import pl.gren.oze_app.model.Salesman;
import pl.gren.oze_app.repository.SalesmanRepository;
import pl.gren.oze_app.service.Impl.RedirectServiceImpl;
import pl.gren.oze_app.service.RedirectService;
import pl.gren.oze_app.service.SalesmanService;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Controller
@RequestMapping("/salesmen")
public class SalesmanController {

    private final SalesmanService salesmanService;
    private final SalesmanRepository salesmanRepository;
    private RedirectServiceImpl redirectService;

    @Autowired
    public SalesmanController(SalesmanService salesmanService, SalesmanRepository salesmanRepository) {
        this.salesmanService = salesmanService;
        this.salesmanRepository = salesmanRepository;
    }

    @GetMapping("/")
    public String getAllSalesmen(Model model) {
        List<Salesman> salesmen = salesmanService.getAllSalesmen();
        model.addAttribute("salesmen", salesmen);
        return "forms/showSalesmen";
    }


    @GetMapping("/{id}")
    public String getSalesmanById(@PathVariable("id") Long id, Model model) {
        Optional<Salesman> salesmanOptional = salesmanService.findSalesmanById(id);
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
    public String addSalesman( @Valid Salesman salesman) {
        salesmanService.saveSalesman(salesman);

        System.out.println("imie salesman: " + salesman.getName());
        return "redirect:/salesmen/";
    }

    @GetMapping("/edit/{id}")
    public String showUpdateForm(@PathVariable("id") long id, Model model) {
        Salesman salesman= salesmanRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Zły numer id hamdlowca:" + id));

        model.addAttribute("salesman", salesman);
        return "forms/editSalesman";
    }

    @PostMapping("/edit/{id}")
    public String editSalesman(@PathVariable Long id, @ModelAttribute("salesman") Salesman salesman) {
        salesmanService.updateSalesman(salesman, id);
        return "redirect:/salesmen/";

    }

    @GetMapping("/delete/{id}")
    public String deleteSalesman(@PathVariable("id") Long id) {
        salesmanService.deleteSalesmanById(id);
        return "redirect:/salesmen/";
    }

    @GetMapping("/clients/{id}")
    public String salesmanClients(@PathVariable("id") Long id, Model model) {

        Salesman salesman = salesmanService.findSalesmanById(id).orElseThrow(() -> new IllegalArgumentException("Brak handlowca o tym numerze id: " + id));
        Set<Client> clients = salesman.getClientList();

        model.addAttribute("salesmanClients", clients);
        model.addAttribute("salesmanId", id);

        System.out.println("Salesman " + salesman.getClientList());

        return "forms/salesmanClients";
    }
}