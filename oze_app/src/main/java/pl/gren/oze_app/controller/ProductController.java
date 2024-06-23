package pl.gren.oze_app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.gren.oze_app.model.*;
import pl.gren.oze_app.model.db.entity.Client;
import pl.gren.oze_app.model.db.entity.product.Product;
import pl.gren.oze_app.service.*;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Controller
@RequestMapping("/products/")
public class ProductController {

    HeatPumpService heatPumpService;
    ClientService clientService;
    CWUBufforTankService cwuBufforTankService;
    COBufferTankService coBufferTankService;
    ProductService productService;

    @Autowired
    public ProductController(HeatPumpService heatPumpService, ClientService clientService, CWUBufforTankService cwuBufforTankService, COBufferTankService coBufferTankService, ProductService productService) {
        this.heatPumpService = heatPumpService;
        this.clientService = clientService;
        this.coBufferTankService = coBufferTankService;
        this.cwuBufforTankService = cwuBufforTankService;
        this.productService = productService;
    }


    @GetMapping("/add")
    public String addProductView() {
        return "forms/addProduct";
    }

//    @PostMapping("/add")
//    public ResponseEntity<OtherProduct> saveProduct(@RequestBody OtherProduct product) {
//        OtherProduct addedProduct = otherProductService.saveProduct(product);
//        return ResponseEntity.status(HttpStatus.CREATED).body(addedProduct);
//    }

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

    @GetMapping("/heatpumps/save/{clientId}")
    public String saveHeatPumpToClient(@PathVariable Long clientId, @RequestParam("producent") String producent,
                                       @RequestParam("model") String model,
                                       @RequestParam("type") String type)
    {

        Client client = clientService.findById(clientId).orElseThrow(() -> new NoSuchElementException("Nie ma klienta o takim ID"));

        HeatPump heatPump = heatPumpService.getHeatPumpByProducentModelType(producent, model, type);

        System.out.println("Model tej pompy to: " + heatPump.getModel());

            System.out.println("DODAJEMY POMPE CIEPLA DO PRODUKTOW OD KLIENTA!");
//            ClientProducts clientProducts = client.getClientProducts();
//            clientProducts.setHeatPump(heatPump);
//            client.setClientProducts(clientProducts);
//            clientProductService.updateClientProducts(clientProducts, clientProducts.getId());


//        System.out.println("Dodaliśmy pompe o modelu: " + client.getClientProducts().getHeatPump().getModel() + " do clienta o imieniu: " + client.getName());


        Long salesmanId = client.getSalesman().getId();
        System.out.println(salesmanId);

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


    // 21.05.2024
    @GetMapping("/co-cwu/save/{clientId}")
    public String saveCOCWUToClient(@RequestParam("cwuname") String cwuname, @RequestParam("coname") String coname, @RequestParam("heatingCircuits") String heatingCircruits, @RequestParam("hotWaterCirculation") String hotWaterCirculation, Model model, @PathVariable Long clientId) {

        //System.out.println("cyrkulacja: " + waterCirculation)
        System.out.println("coName" + coname);
        // ID POBIERZ od co cwu
        Client client = clientService.findById(clientId).orElseThrow(() -> new NoSuchElementException("Nie ma klienta o takim ID"));
        COBufferTank coBufferTank = coBufferTankService.getCOBufferTankByName(coname);
        CWUBufforTank cwuBufforTank = cwuBufforTankService.getCWUBufforTankByName(cwuname);

//        ClientProducts clientProducts = client.getClientProducts();
//        String producent = clientProducts.getHeatPump().getProducent();

        // dodawanie wszystkich inny produktow
        // potrzebujemy producenta, wiec najpierw trzeba dodac pompe ciepla do clientproducts, a dopiero potem co i cwu
//        List<OtherProduct> otherProducts = otherProductService.setCOCWUOthers(producent, Integer.parseInt(heatingCircruits), hotWaterCirculation);

        List<Product> clientOtherProductList = new ArrayList<>();

//        clientOtherProductList.addAll(otherProducts);

        System.out.println("Heating circuits: " + heatingCircruits);
        System.out.println("Hot water circulation: " + hotWaterCirculation); // int

        System.out.println("cwu: " + cwuBufforTank + "co: " + coBufferTank);

            System.out.println("Client products sa");
//            clientProducts.setCwuBufforTank(cwuBufforTank);
//            clientProducts.setCoBufferTank(coBufferTank);

            // adding other products to clientproducts
            // problem here

        System.out.println(clientOtherProductList);
//            clientProducts.setOtherProducts(otherProducts);

//            clientProducts.setCoBufferTank(coBufferTank);
//            coBufferTank.addToCoBufferTankClientList(clientProducts);

//            System.out.println("co z zapisu: " + clientProducts.getCoBufferTank());
//            System.out.println("co z cotank modelu: " + coBufferTank.getCoBufferTankClientList());

            // dosc
//            coBufferTankService.updateCoBufferTank(coBufferTank, coBufferTank.getId());
//            clientProductService.updateClientProducts(clientProducts, client.getClientProducts().getId());

//            System.out.println("Dodaliśmy produkty: " + client.getClientProducts().getOtherProducts());
            System.out.println("Dodaliśmy produkty: " + clientOtherProductList);
//        System.out.println("Dodaliśmy cobufferTank: " + client.getClientProducts().getCoBufferTank() + " do klienta o imieniu: " + client.getName());
//        System.out.println("Dodaliśmy cwoBufforTank: " + client.getClientProducts().getCwuBufforTank() + " do klienta o imieniu: " + client.getName());

        Long salesmanId = client.getSalesman().getId();
        return "redirect:/salesmen/clients/" + salesmanId.toString();
    }


    @GetMapping("/clients/{clientId}/show-products")
    public String showProducts(@PathVariable Long clientId) {

        Client client = clientService.findById(clientId).orElseThrow(() -> new NoSuchElementException("Brak klienta"));

//        ClientProducts clientProducts = client.getClientProducts();

//        System.out.println("Halo odpowiedz: " + clientProducts);
//        System.out.println(clientProducts.getCoBufferTank().getName());
//        System.out.println(clientProducts.getCwuBufforTank().getName());
//
        return "redirect:/salesmen";
    }



}
