package com.example.cartmicroservice.repository;

import com.example.cartmicroservice.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ItemRepository extends JpaRepository<Item,Integer> {

}

