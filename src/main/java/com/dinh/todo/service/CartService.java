package com.dinh.todo.service;

import com.dinh.todo.models.Cart;
import com.dinh.todo.models.CartDetail;
import com.dinh.todo.models.Product;
import com.dinh.todo.models.User;
import com.dinh.todo.repository.CartDetailRepository;
import com.dinh.todo.repository.CartRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CartService {

    private final CartRepository cartRepository;
    private final CartDetailRepository cartDetailRepository;
    private final UserService userService;
    private final ProductService productService;

    public CartService(CartRepository cartRepository, CartDetailRepository cartDetailRepository, UserService userService, ProductService productService) {
        this.cartRepository = cartRepository;
        this.cartDetailRepository = cartDetailRepository;
        this.userService = userService;
        this.productService = productService;
    }

    public Cart save(Cart cart) {
        return cartRepository.save(cart);
    }

    public void updateCartQuantity(long cartDetailId, String action, Long quantity) {
        CartDetail cartDetail = cartDetailRepository.findById(cartDetailId)
                .orElseThrow(() -> new RuntimeException("Cart not existed"));

        if (action.equals("decrease")) {
            cartDetail.setQuantity(cartDetail.getQuantity() - 1);
        } else if (action.equals("increase")) {
            cartDetail.setQuantity(cartDetail.getQuantity() + 1);
        } else {
            if (cartDetail != null && quantity > 0) {
                cartDetail.setQuantity(quantity);
            }
        }

        cartDetailRepository.save(cartDetail);
    }

    public CartDetail handleAddProductToCart(Long productId, HttpSession session) {
        User user = userService.getCurrentUser();
        Cart cart = user.getCart();

        // check xem user có cart chưa
        if (cart == null) {
            cart = new Cart();
            cart.setUser(user);
            cart.setSum(0);
            save(cart);
        }

        Product product = productService.findById(productId);
        Optional<CartDetail> existingDetail = cart.getCartDetails()
                .stream()
                .filter(detail -> detail.getProduct().equals(product))
                .findFirst();

        if (existingDetail.isPresent()) {
            existingDetail.get().setQuantity(existingDetail.get().getQuantity() + 1);
            cartDetailRepository.save(existingDetail.get());

            save(cart);
            session.setAttribute("cart_sum", cart.getSum());
            return existingDetail.orElse(null);
        } else {
            CartDetail cartDetail = new CartDetail();
            cartDetail.setProduct(product);
            cartDetail.setQuantity(1);
            cartDetail.setCart(cart);
            cartDetail.setPrice(product.getPrice());

            cart.setSum(cart.getSum() + 1);

            cartDetailRepository.save(cartDetail);

            save(cart);
            session.setAttribute("cart_sum", cart.getSum());
            return cartDetail;
        }
    }

    public int deleteCartDetailById(Long id) {
        int sum = updateCartSum(id, "decrease", 0L);
        cartDetailRepository.deleteById(id);
        return sum;
    }

    public int updateCartSum(long cartDetailId, String action, Long quantity) {
        CartDetail cartDetail = cartDetailRepository.findById(cartDetailId)
                .orElseThrow(() -> new RuntimeException("no cart"));

        Cart cart = cartDetail.getCart();
        if (action.equals("decrease")) {
            cart.setSum(cart.getSum() - 1);
        } else if (action.equals("increase")) {
            cart.setSum(cart.getSum() + 1);
        } else {
            if (quantity > 0) {
                cart.setSum(Math.toIntExact(quantity));
            }
        }
        cartRepository.save(cart);
        return cart.getSum();
    }
}
