package com.example.Pr5.controllers;

import com.example.Pr5.model.Order;
import com.example.Pr5.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/orders")
public class OrderController {

    private final OrderRepository orderRepository;

    @Autowired
    public OrderController(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @GetMapping
    public String showAllOrders(Model model) {
        List<Order> orders = orderRepository.findAll();
        model.addAttribute("orders", orders);
        return "order/orderList";
    }

    @GetMapping("/{id}")
    public String showOrderDetails(@PathVariable Long id, Model model) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid order Id:" + id));
        model.addAttribute("order", order);
        return "order/orderDetails";
    }

    @GetMapping("/new")
    public String showOrderForm(Model model) {
        model.addAttribute("order", new Order());
        return "order/orderForm";
    }

    @PostMapping
    public String createOrder(@ModelAttribute Order order) {
        orderRepository.save(order);
        return "redirect:/orders";
    }

    @GetMapping("/{id}/edit")
    public String showEditForm(@PathVariable Long id, Model model) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid order Id:" + id));
        model.addAttribute("order", order);
        return "order/editOrderForm";
    }

    @PostMapping("/{id}/edit")
    public String updateOrder(@PathVariable Long id, @ModelAttribute Order updatedOrder) {
        orderRepository.save(updatedOrder);
        return "redirect:/orders";
    }

    @GetMapping("/{id}/delete")
    public String deleteOrder(@PathVariable Long id) {
        orderRepository.deleteById(id);
        return "redirect:/orders";
    }
}
