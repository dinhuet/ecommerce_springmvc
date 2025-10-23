package com.dinh.todo.service;

import com.dinh.todo.models.*;
import com.dinh.todo.repository.CartDetailRepository;
import com.dinh.todo.repository.CartRepository;
import com.dinh.todo.repository.OrderDetailrepository;
import com.dinh.todo.repository.OrderRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class OrderService {
    private final UserService userService;
    private final OrderRepository orderRepository;
    private final OrderDetailrepository orderDetailRepository;
    private final CartRepository cartRepository;
    private final CartDetailRepository cartDetailRepository;

    public OrderService(UserService userService, OrderRepository orderRepository, OrderDetailrepository orderDetailRepository, CartRepository cartRepository, CartDetailRepository cartDetailRepository) {
        this.userService = userService;
        this.orderRepository = orderRepository;
        this.orderDetailRepository = orderDetailRepository;
        this.cartRepository = cartRepository;
        this.cartDetailRepository = cartDetailRepository;
    }

    public void placeOrder(String receiverName, String receiverAddress, String receiverPhone) {
        User user = userService.getCurrentUser();

        Order order = Order.builder()
                .receiverPhone(receiverPhone)
                .receiverName(receiverName)
                .user(user)
                .receiverAddress(receiverAddress)
                .build();

        order = orderRepository.save(order);

        Cart cart = user.getCart();
        log.info("User cart: " + cart);

        if (cart != null) {
            List<CartDetail> cartDetails = cart.getCartDetails();

            for (CartDetail cartDetail : cartDetails) {
                OrderDetail orderDetail = OrderDetail.builder()
                        .quantity(cartDetail.getQuantity())
                        .product(cartDetail.getProduct())
                        .price(cartDetail.getPrice())
                        .order(order)
                        .build();

                orderDetailRepository.save(orderDetail);
                cartDetailRepository.delete(cartDetail);
            }
            user.setCart(null);
            userService.save(user);

            cartRepository.delete(cart);
        }

    }


}
