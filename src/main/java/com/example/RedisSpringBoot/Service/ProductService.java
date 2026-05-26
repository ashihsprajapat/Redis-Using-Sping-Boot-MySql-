package com.example.RedisSpringBoot.Service;

import com.example.RedisSpringBoot.Dto.ProductRequest;
import com.example.RedisSpringBoot.Entity.Product;
import com.example.RedisSpringBoot.Repository.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.jspecify.annotations.Nullable;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public ResponseEntity<List<Product>> getAllProduct() {

        log.info("Fetching all Products");
       // simulateSlowDBCall();
        return  ResponseEntity.status(HttpStatus.OK).body(productRepository.findAll());
    }

    @Cacheable(value = "product", key = "#id")
    public  Product getById(Long id) {
       log.info("Getting Product by Id");
       // simulateSlowDBCall();
        Optional<Product> optionalProduct =  productRepository.findById(id);
        if(optionalProduct.isPresent()){
            log.info("finding from products");
            return  optionalProduct.get();
        }
        log.info("Product not found");
        return  null;
    }

    public  Product createProduct(ProductRequest productRequest) {
        log.info("Create product int data base");
        //simulateSlowDBCall();
        Product prod= new Product();

        prod.setName(productRequest.getName());
        prod.setPrice(productRequest.getPrice());
        prod.setCategory(productRequest.getCategory());
        prod.setDescription(productRequest.getDescription());
        prod.setStoks(productRequest.getStock());

        return  productRepository.save(prod);

    }

    @CachePut(value = "product", key = "#id")
    public @Nullable Product update(Long id, ProductRequest productRequest) {
        log.info("Update product from database");
        //simulateSlowDBCall();
        Product getProd= getById(id);
        if(getProd == null){
            log.info("Product not found");
            return  null;
        }
        if(productRequest.getName() != null)
            getProd.setName(productRequest.getName());
        if(productRequest.getDescription() != null)
            getProd.setDescription(productRequest.getDescription());
        if(productRequest.getCategory() != null)
            getProd.setCategory(productRequest.getCategory());
        if(productRequest.getStock() != null)
            getProd.setStoks(productRequest.getStock());

        return  productRepository.save(getProd);


    }

    @CacheEvict(value = "product", key = "#id",  beforeInvocation = true)
    public String  delete(Long id) {
        log.info("Delete the product ");
       // simulateSlowDBCall();
        Product getProd= getById(id);
        if(getProd == null){
            log.info("Product not found");
            return "product not found ";
        }
        productRepository.deleteById(id);
        return  "product dele successfull";
    }

    private  void simulateSlowDBCall(){
        try{
            Thread.sleep(500);
        }catch (InterruptedException e){
            Thread.currentThread().interrupt();
        }
    }
}
