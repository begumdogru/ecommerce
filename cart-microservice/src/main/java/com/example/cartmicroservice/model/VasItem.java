package com.example.cartmicroservice.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
@NoArgsConstructor
@Getter
@Setter
public class VasItem extends Item {
    private int categoryId;
    private List<CategoryEnum> allowedItems;
    @Override
    public int getSellerId() {
        if (super.getSellerId() != 5003) {
            throw new IllegalStateException("VasItem seller ID must be 5003");
        }
        return super.getSellerId();
    }



}
