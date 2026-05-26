package com.example.RedisSpringBoot.Dto;

import lombok.*;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductRequest {

    private  String name ;
    private  String description;
    private  String category;
    private  Integer stock;
    private  BigDecimal price;
}
