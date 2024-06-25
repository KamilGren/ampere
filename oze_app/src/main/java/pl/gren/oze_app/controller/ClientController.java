package pl.gren.oze_app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.gren.oze_app.exception.Error404;
import pl.gren.oze_app.model.db.entity.Client;
import pl.gren.oze_app.model.dto.client.ClientCreateDto;
import pl.gren.oze_app.model.dto.client.ClientUpdateDto;
import pl.gren.oze_app.service.ClientService;
import pl.gren.oze_app.service.SalesmanService;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/clients")
public class ClientController {

    private final ClientService clientService;
    private final SalesmanService salesmanService;

    @Autowired
    public ClientController(ClientService clientService, SalesmanService salesmanService) {
        this.clientService = clientService;
        this.salesmanService = salesmanService;
    }

    @GetMapping("")
    public String viewAllClients(Model model) {
        List<Client> clients = clientService.findAll();
        model.addAttribute("rows", clients);
        return "clients/index";
    }

    @GetMapping("/{id}")
    public String viewClient(Model model, @PathVariable Long id) {
        Client client = clientService.findById(id).orElseThrow(Error404::new);
        model.addAttribute("client", client);
        model.addAttribute("salesman", client.getSalesman());
        model.addAttribute("contracts", client.getContracts());
        model.addAttribute("buildings", client.getBuildings());
        return "clients/view";
    }

    @GetMapping("/create")
    public String createClient(Model model) {
        model.addAttribute("salesmen", this.salesmanService.findAll());
        return "clients/create";
    }

    @PostMapping("/create")
    public String createClient(@ModelAttribute ClientCreateDto dto) {
        Client salesman = clientService.insert(dto);
        return "redirect:/clients/" + salesman.getId();
    }


    @GetMapping("/{id}/edit")
    public String editClient(Model model, @PathVariable Long id) {
        Client client = clientService.findById(id).orElseThrow(Error404::new);
        model.addAttribute("client", client);
        model.addAttribute("salesmen", this.salesmanService.findAll());
        model.addAttribute("selectedSalesmanId", client.getSalesman().getId());
        return "clients/edit";
    }

    @PostMapping("/{id}/edit")
    public String updateClient(@PathVariable Long id, @ModelAttribute ClientUpdateDto dto) {
        Client client = clientService.findById(id).orElseThrow(Error404::new);
        clientService.update(client, dto);
        return "redirect:/clients/" + client.getId();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteClient(@PathVariable Long id) {
        Optional<Client> optional = clientService.findById(id);
        if (optional.isPresent()) {
            clientService.delete(optional.get());
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.badRequest().build();
    }


}
