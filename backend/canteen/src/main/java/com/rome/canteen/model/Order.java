package com.rome.canteen.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "orders")
public class Order {

    @Id
    private String id;
    private String customerId;
    private String foodItemId;
    private String category; // breakfast, lunch, or dinner
    private String timeSlot; // Time slot like "7:00 AM - 7:30 AM"

    // Default constructor
    public Order() {
    }

    // Parameterized constructor
    public Order(String customerId, String foodItemId, String category, String timeSlot) {
        this.customerId = customerId;
        this.foodItemId = foodItemId;
        this.category = category;
        this.timeSlot = timeSlot;
    }

    // Getters and Setters

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getFoodItemId() {
        return foodItemId;
    }

    public void setFoodItemId(String foodItemId) {
        this.foodItemId = foodItemId;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getTimeSlot() {
        return timeSlot;
    }

    public void setTimeSlot(String timeSlot) {
        this.timeSlot = timeSlot;
    }
}
