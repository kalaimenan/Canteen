package com.rome.canteen.service;

import com.rome.canteen.model.Order;
import com.rome.canteen.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private TimeSlotService timeSlotService;

    // Method to place a new order
    public String placeOrder(Order order) {
        // Check if the selected time slot has availability
        if (!timeSlotService.isTimeSlotAvailable(order.getCategory(), order.getTimeSlot())) {
            return "Time slot is full. Please choose another time slot.";
        }

        // If the time slot is available, save the order and update the time slot
        timeSlotService.incrementTimeSlotOrder(order.getCategory(), order.getTimeSlot());
        orderRepository.save(order);

        return "Order placed successfully!";
    }

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }
}
