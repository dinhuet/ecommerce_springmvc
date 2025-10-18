package com.dinh.todo.service;

import com.dinh.todo.models.Cart;
import com.dinh.todo.models.CartDetail;
import com.dinh.todo.models.Product;
import com.dinh.todo.models.User;
import com.dinh.todo.repository.CartDetailRepository;
import com.dinh.todo.repository.CartRepository;
import com.dinh.todo.repository.ProductRepository;
import com.dinh.todo.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.Principal;
import java.util.*;

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
