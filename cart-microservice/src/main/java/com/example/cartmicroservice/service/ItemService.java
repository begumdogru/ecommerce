package com.example.cartmicroservice.service;

import com.example.cartmicroservice.model.Item;
import com.example.cartmicroservice.repository.ItemRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ItemService {
    private final ItemRepository itemRepository;

    public ItemService(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }
    public Optional<Item> findItemById(int itemId) {
        return itemRepository.findById(itemId);
    }
}
