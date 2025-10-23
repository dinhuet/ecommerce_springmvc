package com.dinh.todo.controller.client;

import com.dinh.todo.service.OrderService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/order")
public class OrderController {
    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("/place-order")
    public String placeOrder(@RequestParam("receiverName") String receiverName,
                             @RequestParam("receiverAddress") String receiverAddress,
                             @RequestParam("receiverPhone") String receiverPhone, HttpSession session) {
        orderService.placeOrder(receiverName, receiverAddress, receiverPhone);
        session.setAttribute("cart_sum", 0);
        return "redirect:/";
    }
}
