package pl.gren.oze_app.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.gren.oze_app.model.HeatPump;
import pl.gren.oze_app.model.Product;
import pl.gren.oze_app.service.ProductService;

@Controller
@RequestMapping("/products/")
public class ProductController {

    ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
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


}
