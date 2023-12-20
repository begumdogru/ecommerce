package com.example.cartmicroservice.controller;

import com.example.cartmicroservice.model.DigitalItem;
import com.example.cartmicroservice.model.Item;
import com.example.cartmicroservice.model.VasItem;
import com.example.cartmicroservice.service.CartService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
public class CartController {
    private final CartService cartService;

    @GetMapping("/items")
    public ResponseEntity<List<Item>> getAllItems() {
        List<Item> items = cartService.findAllItems();
        return new ResponseEntity<>(items, HttpStatus.OK);
    }
    @PutMapping("/addPrice")
    public ResponseEntity<Item> addPriceToItem(@RequestBody int itemId, @RequestBody double price) {
        Item addItemPrice = cartService.addPriceToItem(itemId, price);
        if (addItemPrice != null) {
            return new ResponseEntity<>(addItemPrice, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/addDigitalItem")
    public ResponseEntity<String> addDigitalItem(@RequestBody String categoryId, @RequestBody DigitalItem item) {
        try {
            DigitalItem addDigitalItem = cartService.addDigitalItem(categoryId, item);
            return new ResponseEntity<>("Digital item added successfully.", HttpStatus.OK);
        } catch (Error e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/addVasItem")
    public ResponseEntity<String> addVasItem(@RequestBody String categoryId, @RequestBody VasItem newVasItem) {
        try {
            VasItem addedVasItem = cartService.addVasItem(categoryId, newVasItem);
            return new ResponseEntity<>("VasItem added successfully.", HttpStatus.OK);
        } catch (Error e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
