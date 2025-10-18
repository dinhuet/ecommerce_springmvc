package com.dinh.todo.controller.client;

import com.dinh.todo.service.CartService;
import com.dinh.todo.service.ProductService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@Controller
@RequestMapping("/cart")
public class CartController {

    private final ProductService productService;
    private final CartService cartService;

    public CartController(ProductService productService, CartService cartService) {
        this.productService = productService;
        this.cartService = cartService;
    }

    @PostMapping("/add-product-to-cart/{id}")
    public String addProductToCart(@PathVariable long id, HttpSession session) {
        session.setAttribute("cart_sum", cartService.handleAddProductToCart(id));
        return "redirect:/";
    }

    @PostMapping("/update-quantity/{cartDetailId}")
    public String updateQuantity(@PathVariable long cartDetailId, @RequestParam String action,
                                 @RequestParam(required = false) Long quantity) {
        cartService.updateCartQuantity(cartDetailId, action, Objects.requireNonNullElse(quantity, 0L));
        return "redirect:/user/cart";
    }

    @PostMapping("/delete-cartDetail/{id}")
    public String deleteCartDetail(@PathVariable Long id, HttpSession session) {
        session.setAttribute("cart_sum", cartService.deleteCartDetailById(id));
        return "redirect:/user/cart";
    }
}
