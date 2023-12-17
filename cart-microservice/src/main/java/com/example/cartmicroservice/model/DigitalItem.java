package com.example.cartmicroservice.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Getter
@Setter
public class DigitalItem extends Item{
    private int digitalItemId;
    private int categoryId;
    private int maxQuantity;
    private List<CategoryEnum> allowedCategories;

    public boolean isCategoryAllowed(String category) {
        return allowedCategories.contains(category);
    }

    public double calculateTotalPrice(int quantity) {
        return getPrice() * quantity;
    }

}
