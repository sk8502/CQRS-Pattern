package com.sk.controller;


import com.sk.entity.Product;
import com.sk.service.ProductQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductQueryController {

    @Autowired
    public ProductQueryService productQueryService;


    @GetMapping
    public List<Product> fetchAllProduct() {
        return productQueryService.findAll();
    }




}
