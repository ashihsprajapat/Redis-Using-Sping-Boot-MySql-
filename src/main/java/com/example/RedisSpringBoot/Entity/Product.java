package com.example.RedisSpringBoot.Entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter@NoArgsConstructor
@AllArgsConstructor
@Data
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name ;

    private  String description;

    private  String category;

    private BigDecimal price;

    private Integer stoks;

    private LocalDateTime createAt;

    @PrePersist
    public void prePersist() {
        this.createAt = LocalDateTime.now();
    }


}
