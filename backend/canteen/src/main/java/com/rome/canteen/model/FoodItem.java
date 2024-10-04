package com.rome.canteen.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "food_items")
public class FoodItem {
    @Id
    private String id;
    private String name;
    private String description;
    private double price;
    private String availability;
    private String mealType; // E.g., Breakfast, Lunch, etc.

    public FoodItem() {
    }

    public FoodItem(String name, String description, double price, String availability, String mealType) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.availability = availability;
        this.mealType = mealType;
    }

    // Getters and Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getAvailability() {
        return availability;
    }

    public void setAvailability(String availability) {
        this.availability = availability;
    }

    public String getMealType() {
        return mealType;
    }

    public void setMealType(String mealType) {
        this.mealType = mealType;
    }
}
