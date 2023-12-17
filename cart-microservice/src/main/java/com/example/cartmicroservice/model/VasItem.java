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
