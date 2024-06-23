package pl.gren.oze_app.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.gren.oze_app.model.*;
import pl.gren.oze_app.model.db.entity.Client;
import pl.gren.oze_app.service.ClientService;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Controller
@RequestMapping("/offers/")
public class OfferController {

    private ClientService clientService;

    @Autowired
    public OfferController(ClientService clientService) {
        this.clientService = clientService;
    }


    @GetMapping("/calc-invoice/{clientId}")
    public String calcInvoice(@PathVariable Long clientId, @RequestParam("overhead") double overhead, @RequestParam("vat") double vat, @RequestParam("margin") double margin, Model model)
    {

        Client client = clientService.findById(clientId).orElseThrow(() -> new NoSuchElementException("Brak klienta z takim nr Id: " + clientId));

//        List<Product> clientProductsList = new ArrayList<>();
//      now in orders
//        HeatPump heatPump = client.getClientProducts().getHeatPump();
//        heatPump.priceWithOverhead(overhead);
//        heatPump.priceWithMargin(margin);
//        heatPump.priceWithVat(vat);
//        clientProductsList.add(heatPump);
//
//        System.out.println("VAT: " + heatPump.getPriceWithVat());
//
//        CWUBufforTank cwuBufforTank = client.getClientProducts().getCwuBufforTank();
//        cwuBufforTank.priceWithOverhead(overhead);
//        cwuBufforTank.priceWithMargin(margin);
//        cwuBufforTank.priceWithVat(vat);
//        clientProductsList.add(cwuBufforTank);
//
//
//        COBufferTank coBufferTank = client.getClientProducts().getCoBufferTank();
//        coBufferTank.priceWithOverhead(overhead);
//        coBufferTank.priceWithMargin(margin);
//        coBufferTank.priceWithVat(vat);
//        clientProductsList.add(coBufferTank);

//        List<OtherProduct> otherProductList = client.getClientProducts().getOtherProducts();
//
//        for(int i = 0; i < otherProductList.size(); i++) {
//
//            client.getClientProducts().getOtherProducts().get(i).priceWithOverhead(overhead);
//            client.getClientProducts().getOtherProducts().get(i).priceWithOverhead(margin);
//            client.getClientProducts().getOtherProducts().get(i).priceWithOverhead(vat);
//        }
//
//        // here we have all products from this client
//        clientProductsList.addAll(otherProductList);

//        model.addAttribute("products", clientProductsList);

        return "offer/calculationsDataTables";
    }


    // faktura
    @GetMapping("/show-invoice/{clientId}")
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

