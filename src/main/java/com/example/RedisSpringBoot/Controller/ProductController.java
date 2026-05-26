package com.example.RedisSpringBoot.Controller;

import com.example.RedisSpringBoot.Dto.ProductRequest;
import com.example.RedisSpringBoot.Entity.Product;
import com.example.RedisSpringBoot.Service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@Controller
@RequestMapping("/api/product")
public class ProductController {

    private ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    //find product
    @GetMapping
    public ResponseEntity<List<Product>>  getAllProduct(){
        return  productService.getAllProduct();
    }

    // find by id
    @GetMapping("/{id}")
    public  ResponseEntity<Product>  getById(@PathVariable Long id){
        return  ResponseEntity.ok( productService.getById(id));
    }

    // create product
    @PostMapping
    public ResponseEntity<Product> createProduct(@RequestBody ProductRequest productRequest){
        log.info("Creating product");
        return  ResponseEntity.status(HttpStatus.CREATED).body(  productService.createProduct(productRequest));
    }

    //update prodcut
    @PutMapping("/{id}")
    public  ResponseEntity<Product> updateProdcut(@PathVariable Long id , @RequestBody ProductRequest productRequest){
        return  ResponseEntity.ok(productService.update(id, productRequest ));
    }
    //delete product
    @DeleteMapping("/{id}")
    public  ResponseEntity<String> deleteProduct(@PathVariable Long id){
         String ans=  productService.delete(id);
        return ResponseEntity.ok(ans);
    }


}
