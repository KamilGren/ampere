package pl.gren.oze_app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.gren.oze_app.model.Product;
import pl.gren.oze_app.repository.ProductRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class ProductService {

    ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Product getProduct(Long id) {
        return productRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Brak produktu z takim numerem ID: " + id));
    }

    public List<Product> setCOCWUOthers(Integer heatingCircuitsAmount, String hotWaterCirculation) {

        List<Product> productList = new ArrayList<>();

        Product product = productRepository.findById(1L).orElseThrow(() -> new NoSuchElementException("Brak produktu z takim numerem ID " + 1L));

        for (int i = 0; i <= heatingCircuitsAmount; i++) {
            productList.add(product);
        }
        if (hotWaterCirculation.equals("yes")) {
            Product product2 = productRepository.findById(4L).orElseThrow(() -> new NoSuchElementException("Brak produktu z takim numerem ID " + 4L));
            productList.add(product);
        }

        return productList;
    }

    public Product saveProduct(Product product) {
        return productRepository.save(product);
    }

}
