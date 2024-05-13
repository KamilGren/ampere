package pl.gren.oze_app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.gren.oze_app.model.BuildingRequirements;
import pl.gren.oze_app.model.Client;
import pl.gren.oze_app.model.HeatPump;
import pl.gren.oze_app.model.Salesman;
import pl.gren.oze_app.service.ClientService;
import pl.gren.oze_app.service.HeatPumpService;
import pl.gren.oze_app.service.Impl.RedirectServiceImpl;
import pl.gren.oze_app.service.SalesmanService;

import java.util.HashSet;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;

@Controller
@RequestMapping("/clients")
public class ClientController {

    private final ClientService clientService;
    private final SalesmanService salesmanService;
    private RedirectServiceImpl redirectService;

    @Autowired
    public ClientController(ClientService clientService, SalesmanService salesmanService) {
        this.clientService = clientService;
        this.salesmanService = salesmanService;
    }


    @GetMapping("/")
    public String showAllClients(Model model) {

        List<Client> clientList = clientService.showClients();
        model.addAttribute("clients", clientList);

        return "/forms/showClients";
    }

//    @GetMapping("/{id}/showAll")
//    public String showAllSalesmanClients(@PathVariable Long id, Model model) {
//
//        Salesman salesman = salesmanService.findSalesmanById(id).orElseThrow(() -> new NoSuchElementException("Niestety nie znaleziono handlowca"));
//
//        List<Client> clientList = clientService.showClientsBySalesman(salesman.getId());
//
//        model.addAttribute("clients", clientList);
//
//        return "/forms/showClients";
//    }

    @GetMapping("/add/{id}")
    public String addClientForm(@PathVariable Long id, Model model) {
        model.addAttribute("salesmanId", id);
        return "/forms/addClient";
    }

    @PostMapping("/add/{id}")
    public String addClient(@PathVariable Long id, Client client) {

        Salesman salesman = salesmanService.findSalesmanById(id).orElseThrow(() -> new IllegalArgumentException("Brak handlowca o takim numerze id:" + id));
        //System.out.println("Salesman id: " + client.getSalesman().getId());
        salesman.addClient(client); // mappedy by salesman to glowny, wiec do niego tylko dodaje
        client.setSalesman(salesman);
        salesmanService.updateSalesman(salesman, id);
        clientService.addClient(client);

        System.out.println("lISTA KLIENTOW OD SALESMAN: " + salesman.getId() + " " + salesman.getClientList());

        return "redirect:/clients/";
    }

    @GetMapping("/edit/{id}")
    public String showEditClientForm(@PathVariable Long id, Model model) {
        Client client = clientService.showClientById(id);
        model.addAttribute("client", client);
        return "/forms/updateClient";
    }

    @PostMapping("/edit/{id}")
    public String editClient(@PathVariable Long id, @ModelAttribute("client") Client client) {
        client.setId(id); // Ustawienie ID klienta zgodnie z wartością w ścieżce URL
        clientService.updateClient(client, id);
        return "redirect:/clients/";
    }

    @GetMapping("/delete/{id}")
    public String deleteClient(@PathVariable("id") Long id) {
        clientService.deleteClient(id);
        return "redirect:/clients/";
    }

    @GetMapping("/calculateBuilding/{id}")
    public String showBuildingRequirementsForm(Model model, @PathVariable Long id) {

        // Tworzymy nowy obiekt BuildingRequirements, który będzie używany przez formularz
        BuildingRequirements buildingRequirements = new BuildingRequirements();

        // Przesyłamy pusty obiekt do widoku formularza
        model.addAttribute("buildingRequirements", buildingRequirements);
        model.addAttribute("clientId", id);

        // Zwracamy nazwę widoku formularza
        return "buildingRequirementsForm";
    }

}
