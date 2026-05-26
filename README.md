# RedisSpringBoot 🚀

A Spring Boot REST API project using:

- Spring Boot
- Redis Cache
- MySQL Database
- Docker
- JPA/Hibernate

This project demonstrates how to use Redis caching with Spring Boot for improving API performance.

---

# Features ✨

- Create Product
- Get All Products
- Get Product By Id
- Update Product
- Delete Product
- Redis Caching
- Docker Support
- REST APIs
- MySQL Integration

---

# Product Schema 🗂️

java
@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String description;

    private String category;

    private BigDecimal price;

    private Integer stoks;

    private LocalDateTime createAt;
}

Product Request Body 📦
{
  "name": "Iphone 15",
  "description": "Apple Mobile",
  "category": "Electronics",
  "stock": 10,
  "price": 80000
}

API Endpoints 🌐
Base URL (Local)
http://localhost:2020/api/product
Base URL (Production)
https://redis-using-sping-boot-mysql.onrender.com/api/product

API Routes 📌
1. Create Product
POST
POST /api/product
Body
{
  "name": "Iphone 15",
  "description": "Apple Mobile",
  "category": "Electronics",
  "stock": 10,
  "price": 80000
}
2. Get All Products
GET
GET /api/product
3. Get Product By Id
GET
GET /api/product/1
4. Update Product
PUT
PUT /api/product/1Body
{
  "name": "Updated Product",
  "description": "Updated Description",
  "category": "Electronics",
  "stock": 15,
  "price": 90000
}
5. Delete Product
DELETE
DELETE /api/product/1

Docker Setup 🐳
docker-compose.yml
version: '3.9'

services:

  Postgres:
    image: postgres:17
    container_name: Postgrsql
    shm_size: 128mb

    environment:
      POSTGRES_USER: root
      POSTGRES_PASSWORD: root
      POSTGRES_DB: mydb

    ports:
      - "5432:5432"

    volumes:
      - postgres_data:/var/lib/postgresql/data

  Redis:
    image: redis:7
    container_name: my_redis

    ports:
      - "6379:6379"

    command: redis-server --appendonly yes

    volumes:
      - redis_data:/dat_
      
