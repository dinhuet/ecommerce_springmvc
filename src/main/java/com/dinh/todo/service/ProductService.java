package com.dinh.todo.service;

import com.dinh.todo.models.Product;
import com.dinh.todo.repository.ProductRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> findAll() {
        return productRepository.findAll();
    }

    public void save(Product product) {
        productRepository.save(product);
    }

    public Product findById(long id) { return productRepository.findById( id).orElseThrow(()->
            new EntityNotFoundException("Product not found with id: " + id)); }

    public void deleteById(long id) {
        productRepository.deleteById(id);
    }
}
