package com.sk.controller;


import com.sk.dto.ProductEvent;
import com.sk.entity.Product;
import com.sk.service.ProductCommandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/products")
public class ProductCommandController {

    @Autowired
    private ProductCommandService productCommandService;

    @PostMapping
    public Product createProduct(@RequestBody ProductEvent productEvent) {

        return productCommandService.createProduct(productEvent);
    }

    @PutMapping("/{id}")
    public Product updateProduct(@PathVariable long id, @RequestBody ProductEvent productEvent) {
        return productCommandService.updateProduct(id, productEvent);

    }

}
