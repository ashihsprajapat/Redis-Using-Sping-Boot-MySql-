package com.example.RedisSpringBoot.Repository;

import com.example.RedisSpringBoot.Entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository  extends JpaRepository<Product, Long > {
}
