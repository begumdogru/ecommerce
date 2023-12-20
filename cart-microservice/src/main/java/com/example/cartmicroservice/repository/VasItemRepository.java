package com.example.cartmicroservice.repository;

import com.example.cartmicroservice.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VasItemRepository extends JpaRepository<Item, Integer> , ItemRepository{
}
