package com.sk.service;


import com.sk.dto.ProductEvent;
import com.sk.entity.Product;
import com.sk.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class ProductCommandService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private KafkaTemplate<String,Object> kafkaTemplate;


    public Product createProduct(ProductEvent productevent) {

       Product productDo=productRepository.save(productevent.getProduct());
       ProductEvent event=new ProductEvent("CreateProduct",productDo);
       kafkaTemplate.send("product-event-topic",event);
       return productDo;
    }

    public Product updateProduct(long id,ProductEvent productEvent) {
        Product existingProduct=productRepository.findById(id).get();
        Product newProduct=productEvent.getProduct();
        existingProduct.setName(newProduct.getName());
        existingProduct.setPrice(newProduct.getPrice());
        existingProduct.setDescription(newProduct.getDescription());

        Product productDo=  productRepository.save(existingProduct);
        ProductEvent event=new ProductEvent("UpdateProduct",productDo);
        kafkaTemplate.send("product-event-topic",event);
        return productDo;
    }


}
