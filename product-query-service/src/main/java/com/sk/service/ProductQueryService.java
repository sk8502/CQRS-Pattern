package com.sk.service;


import com.sk.dto.ProductEvent;
import com.sk.entity.Product;
import com.sk.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductQueryService {

    @Autowired
    public ProductRepository productRepository;

    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @KafkaListener(topics = "product-event-topic",groupId = "product-event-group")
    public void processProductEvents(ProductEvent productEvent) {
        Product product=productEvent.getProduct();
        if (productEvent.getEventType().equals("CreateProduct")) {
            productRepository.save(product);
        }if (productEvent.getEventType().equals("UpdateProduct")){
          Product existingProduct= productRepository.findById(product.getId()).get();
            existingProduct.setName(product.getName());
            existingProduct.setPrice(product.getPrice());
            existingProduct.setDescription(product.getDescription());
             productRepository.save(existingProduct);
        }

    }

}
