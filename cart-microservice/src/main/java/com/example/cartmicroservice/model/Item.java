package com.example.cartmicroservice.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
@NoArgsConstructor
@Entity
@Getter
@Setter
@Table(name = "item")
public abstract class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int itemId;
    private int categoryId;
    private int sellerId;
    private double price;
    private int quantity;
    private List<VasItem> vasItems;


}
