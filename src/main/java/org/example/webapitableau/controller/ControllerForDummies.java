package org.example.webapitableau.controller;

import org.example.webapitableau.models.Product;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.example.webapitableau.api.ProductsApi;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ControllerForDummies implements ProductsApi{
    @Override
    public ResponseEntity<List<Product>> productsGet() {
        List <Product> list = new ArrayList<Product>();
        Product pr = new Product();
        pr.setCategory("Store");
        pr.setId(1L);
        pr.setName("Dishwasher");
        pr.setPrice(200.0);
        list.add(pr);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Void> productsPost(Product body) {
        return null;
    }
}
