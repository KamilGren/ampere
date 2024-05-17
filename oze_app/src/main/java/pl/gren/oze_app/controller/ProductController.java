package pl.gren.oze_app.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.gren.oze_app.model.*;
import pl.gren.oze_app.repository.ClientRepository;
import pl.gren.oze_app.service.*;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Controller
@RequestMapping("/products/")
public class ProductController {

    HeatPumpService heatPumpService;
    ClientRepository clientRepository;
    ClientProductService clientProductService;
    CWUBufforTankService cwuBufforTankService;
    COBufferTankService coBufferTankService;
    ProductService productService;

    @Autowired
    public ProductController(ProductService productService, HeatPumpService heatPumpService, ClientRepository clientRepository, CWUBufforTankService cwuBufforTankService, COBufferTankService coBufferTankService, ClientProductService clientProductService) {
        this.heatPumpService = heatPumpService;
        this.productService = productService;
        this.clientRepository = clientRepository;
        this.coBufferTankService = coBufferTankService;
        this.cwuBufforTankService = cwuBufforTankService;
        this.clientProductService = clientProductService;
    }


    @GetMapping("/add")
    public String addProductView() {
        return "forms/addProduct";
    }

    @PostMapping("/add")
    public ResponseEntity<Product> saveProduct(@RequestBody Product product) {
        Product addedProduct = productService.saveProduct(product);
        return ResponseEntity.status(HttpStatus.CREATED).body(addedProduct);
    }

    // HP, CO, CWU

    @GetMapping("/heatpumps/search/{clientId}")
    public String searchHeatPump(Model model, @PathVariable Long clientId)
    {
        model.addAttribute("clientId", clientId);
        model.addAttribute("heatPump", new HeatPump());

        List<String> heatPumpTypes = new ArrayList<>();
        heatPumpTypes.add(HeatPumpTypes.SPLIT.toString());
        heatPumpTypes.add(HeatPumpTypes.MONOBLOK.toString());
        heatPumpTypes.add(HeatPumpTypes.ALL_IN_ONE.toString());

        List<String> heatPumpsProducents = heatPumpService.getHeatPumpsProducents();
        model.addAttribute("producents", heatPumpsProducents);
        model.addAttribute("types", heatPumpTypes);

        return "forms/clientHeatPumpForm";
    }

    @GetMapping("/heatpumps/show/{clientId}")
    public String showHeatPump(Model model1, @PathVariable Long clientId, @RequestParam("producent") String producent,
                               @RequestParam("model") String model,
                               @RequestParam("type") String type) {

        HeatPump heatPump = heatPumpService.getHeatPumpByProducentModelType(producent, model, type);

        Long id = heatPump.getId();
        model1.addAttribute("heatPump", heatPump);
        model1.addAttribute("id", id);
        model1.addAttribute("producent", producent);
        model1.addAttribute("model", model);
        model1.addAttribute("type", type);
        model1.addAttribute("clientId", clientId);

        return "forms/clientHeatPumpForm";
    }

    @GetMapping("/heatpumps/{heatPumpId}/save/{clientId}")
    public String saveHeatPumpToClient(@PathVariable Long clientId, @PathVariable Long heatPumpId)
    {

        Client client = clientRepository.findClientById(clientId).orElseThrow(() -> new NoSuchElementException("Nie ma klienta o takim ID"));
        HeatPump heatPump = heatPumpService.getHeatPumpById(heatPumpId).orElseThrow(() -> new IllegalArgumentException("Brak pompy ciepla o takim ID"));

            System.out.println("DODAJEMY POMPE CIEPLA DO PRODUKTOW OD KLIENTA!");
            ClientProducts clientProducts = client.getClientProducts();
            clientProducts.setHeatPump(heatPump);
            client.setClientProducts(clientProducts);
            clientProductService.updateClientProducts(clientProducts, clientProducts.getId());


        System.out.println("Dodaliśmy pompe o modelu: " + client.getClientProducts().getHeatPump().getModel() + " do clienta o imieniu: " + client.getName());

        Long salesmanId = client.getSalesman().getId();
        return "redirect:/salesmen/clients/" + salesmanId.toString();
    }

    @GetMapping("/co-cwu/search/{clientId}")
    public String showAddCOCWUToClientForm(Model model, @PathVariable Long clientId)
    {
        model.addAttribute("clientId", clientId);
        model.addAttribute("cwuBuffor", new CWUBufforTank());
        model.addAttribute("coBuffer", new COBufferTank());


        List<String> coNames = coBufferTankService.getCOBufferTankNames();
        List<String> cwuNames = cwuBufforTankService.getCWUBufforTankNames();

        model.addAttribute("CWUNames", cwuNames);
        model.addAttribute("CONames", coNames);

        return "forms/clientCWUCO";
    }


    @GetMapping("/co-cwu/show/{clientId}")
    public String showCOCWU(@RequestParam("cwuname") String cwuname, @RequestParam("coname") String coname, @RequestParam("heatingCircuits") String heatingCircruits, @RequestParam("hotWaterCirculation") String hotWaterCirculation, Model model, @PathVariable Long clientId) {

        CWUBufforTank cwuBufforTank = cwuBufforTankService.getCWUBufforTankByName(cwuname);
        COBufferTank coBufferTank = coBufferTankService.getCOBufferTankByName(coname);

        model.addAttribute("coBuffer", coBufferTank);
        model.addAttribute("cwuBuffor", cwuBufforTank);

        model.addAttribute("cwuId", cwuBufforTank.getId());
        model.addAttribute("coId", coBufferTank.getId());

        model.addAttribute("hotWaterCirculation2", hotWaterCirculation);
        model.addAttribute("heatingCircuits2", heatingCircruits);
        model.addAttribute("clientId", clientId);

        System.out.println("Dane coBufferTank: " + coBufferTank.getName());
        System.out.println("cwuname: " + cwuname);
        System.out.println("coname: " + coname);

        return "forms/clientCWUCO";
    }

    @GetMapping("/co-cwu/save/{clientId}")
    public String saveCOCWUToClient(@RequestParam(value = "coId", required = false) Long coId, @RequestParam(value = "cwuId", required = false) Long cwuId, @PathVariable long clientId) {

        //System.out.println("cyrkulacja: " + waterCirculation)
        System.out.println("coId" + coId);
        // ID POBIERZ od co cwu
        Client client = clientRepository.findClientById(clientId).orElseThrow(() -> new NoSuchElementException("Nie ma klienta o takim ID"));
        COBufferTank coBufferTank = coBufferTankService.getCOBufferTankById(1L);
        CWUBufforTank cwuBufforTank = cwuBufforTankService.getCWUBufforTankById(2L);

        System.out.println("cwu: " + cwuBufforTank + "co: " + coBufferTank);


        if(client.getClientProducts() != null) {
            System.out.println("Client products sa");
            client.getClientProducts().setCwuBufforTank(cwuBufforTank);
            client.getClientProducts().setCoBufferTank(coBufferTank);

            ClientProducts clientProducts = client.getClientProducts();
            clientProducts.setCoBufferTank(coBufferTank);
            coBufferTank.addToCoBufferTankClientList(clientProducts);
            System.out.println("co z zapisu: " + clientProducts.getCoBufferTank());
            System.out.println("co z cotank modelu: " + coBufferTank.getCoBufferTankClientList());

            // dosc
            coBufferTankService.updateCoBufferTank(coBufferTank, coBufferTank.getId());
            clientProductService.updateClientProducts(clientProducts, client.getClientProducts().getId());
        }
           //clientProducts.setProducts(productService.setCOCWUOthers((Integer.parseInt(heatingCircuits)), hotWaterCirculation));

//        System.out.println("Dodaliśmy produkty: " + client.getClientProducts().getProducts().toString());
        System.out.println("Dodaliśmy cobufferTank: " + client.getClientProducts().getCoBufferTank() + " do klienta o imieniu: " + client.getName());
        System.out.println("Dodaliśmy cwoBufforTank: " + client.getClientProducts().getCwuBufforTank() + " do klienta o imieniu: " + client.getName());

        Long salesmanId = client.getSalesman().getId();
        return "redirect:/salesmen/clients/" + salesmanId.toString();
    }



}
