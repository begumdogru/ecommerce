package com.example.cartmicroservice.repository;

import com.example.cartmicroservice.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface DigitalItemRepository extends JpaRepository<Item,Integer>, ItemRepository {
    @Override
    Optional<Item> findById(Integer integer);
}
