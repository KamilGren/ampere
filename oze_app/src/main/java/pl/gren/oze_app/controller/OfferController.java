package pl.gren.oze_app.controller;


import org.apache.commons.math3.optim.linear.NoFeasibleSolutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.*;
import pl.gren.oze_app.model.*;
import pl.gren.oze_app.model.offer.Credit;
import pl.gren.oze_app.model.offer.FinancialProgram;
import pl.gren.oze_app.model.offer.Offer;
import pl.gren.oze_app.repository.ClientRepository;
import pl.gren.oze_app.service.ClientService;
import pl.gren.oze_app.service.OfferService;

import java.util.NoSuchElementException;

@Controller
@RequestMapping("/offers/")
public class OfferController {

    private OfferService offerService;
    private ClientRepository clientRepository;
    private ClientService clientService;

    @Autowired
    public OfferController(OfferService offerService, ClientRepository clientRepository, ClientService clientService) {
        this.offerService = offerService;
        this.clientRepository = clientRepository;
        this.clientService = clientService;
    }


    //     public String calcInvoice(@PathVariable Long clientId, @RequestParam("overhead") double overhead, @RequestParam("vat") double vat, @RequestParam("margin") double margin, Model model) {
    @RequestMapping(value = "calcInvoice/{clientId}", method = RequestMethod.GET, produces = MimeTypeUtils.APPLICATION_JSON_VALUE )
    public ResponseEntity<Iterable<ClientProducts>> calcInvoice(@PathVariable Long clientId) {

        Client client = clientRepository.findClientById(clientId).orElseThrow(() -> new NoSuchElementException("Brak klienta z takim nr Id: " + clientId));
        double overhead = 5000;
        double margin = 25;
        double vat = 8;

        HeatPump heatPump = client.getClientProducts().getHeatPump();
        heatPump.priceWithOverhead(overhead);
        heatPump.priceWithMargin(margin);
        heatPump.priceWithVat(vat);

        System.out.println("VAT: " + heatPump.getPriceWithVat());

        CWUBufforTank cwuBufforTank = client.getClientProducts().getCwuBufforTank();
        cwuBufforTank.priceWithOverhead(overhead);
        cwuBufforTank.priceWithMargin(margin);
        cwuBufforTank.priceWithVat(vat);

        COBufferTank coBufferTank = client.getClientProducts().getCoBufferTank();
        coBufferTank.priceWithOverhead(overhead);
        coBufferTank.priceWithMargin(margin);
        coBufferTank.priceWithVat(vat);

        try {
            return new ResponseEntity<Iterable<ClientProducts>>(clientService.findClientClientProducts(), HttpStatus.OK);
        }
        catch (Exception e) {
            return new ResponseEntity<Iterable<ClientProducts>>(HttpStatus.BAD_REQUEST);
        }
    }


    // faktura
    @GetMapping("/showInvoice/{clientId}")
    public String showInvoice(@PathVariable Long clientId, Model model) {

        model.addAttribute("clientId", clientId);
        return "offer/calculationsDataTables";

    }

//    @PostMapping("/create-offer")
//    public Offer createOffer(@RequestBody Offer offerRequest) {
//        // Assuming OfferRequest holds data to create Offer (creditYes, Credit details, FinancialProgram details)
//        Credit credit = new Credit(offerRequest.getCredit());
//        FinancialProgram financialProgram = new FinancialProgram(offerRequest.getFinancialProgram());
//
//        return offerService.createOffer(offerRequest.isCreditYes(), credit, financialProgram);
//    }

    // Additional methods for Offer functionalities can be added here (e.g., GET requests to retrieve offers)
}

// robimy tutaj datatables, razem z obliczeniami
//    @PostMapping(path = "/listing")
//    @ResponseBody
//    public ResponseContainer postListing(@RequestBody ServerSideRequest ssr) {
//        // JSON response: https://datatables.net/manual/server-side#Returned-data
//        return service.getListing(ssr);
//    }

