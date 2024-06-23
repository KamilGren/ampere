package pl.gren.oze_app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.gren.oze_app.model.Product;
import pl.gren.oze_app.oldrepository.ProductRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

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

        // jeśli jest w ogole jakikolwiek produkt nr 1 czyli pompa obiegowa
        // trzeba dodac ilosc tych produktow, a nie dodatkowe produkty
        if (heatingCircuitsAmount > 0) {
            productList.add(product);
            product.setCount(heatingCircuitsAmount);
        }

        if (hotWaterCirculation.equals("yes")) {
            Product product2 = productRepository.findById(4L).orElseThrow(() -> new NoSuchElementException("Brak produktu z takim numerem ID " + 4L));
            productList.add(product2);
        }

        Product product3 = productRepository.findById(8L).orElseThrow(() -> new NoSuchElementException("Brak produktu z takim numerem ID " + 8L));
        Product product4 = productRepository.findById(9L).orElseThrow(() -> new NoSuchElementException("Brak produktu z takim numerem ID " + 9L));
        Product product5 = productRepository.findById(10L).orElseThrow(() -> new NoSuchElementException("Brak produktu z takim numerem ID " + 10L));

        productList.add(product3);
        productList.add(product4);
        productList.add(product5);

        for (Product prod : productList) {
            System.out.println(prod);
        }

        return productList;
    }

    public Product saveProduct(Product product) {
        return productRepository.save(product);
    }

}
