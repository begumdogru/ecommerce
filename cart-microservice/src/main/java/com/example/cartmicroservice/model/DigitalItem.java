package com.example.cartmicroservice.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
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
