package com.example.cartmicroservice.service;

import com.example.cartmicroservice.model.CategoryEnum;
import com.example.cartmicroservice.model.DefaultItem;
import com.example.cartmicroservice.model.DigitalItem;
import com.example.cartmicroservice.model.Item;
import com.example.cartmicroservice.model.VasItem;
import com.example.cartmicroservice.repository.CartRepository;
import com.example.cartmicroservice.repository.DigitalItemRepository;
import com.example.cartmicroservice.repository.ItemRepository;
import com.example.cartmicroservice.repository.VasItemRepository;
import lombok.AllArgsConstructor;
import lombok.Builder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor

public class CartService {
    private static final CategoryEnum DIGITAL_ITEM_CATEGORY = CategoryEnum.DIGITALITEMS;
    private static final int MAX_DIGITAL_ITEM_QUANTITY = 5;
    private static final int VAS_ITEM_SELLER_ID = 5003;
    private final ItemRepository itemRepository;
    private final DigitalItemRepository digitalItemRepository;
    private final VasItemRepository vasItemRepository;
    private final ItemService itemService;

    //TODO: add, delete, and reset items in the cart
    public List<Item> findAllItems() {
        List<Item> items = itemRepository.findAll();
        return items.size() <= 10 ? items : null;
    }

    public Item addPriceToItem(int itemId, double price) {
        Item item = itemService.findItemById(itemId).orElseThrow(() -> new RuntimeException("Item not found"));
        item.setPrice(price);
        return itemRepository.save(item);
    }

    public DigitalItem addDigitalItem(String categoryId, DigitalItem newDigitalItem) {
        validateCategory(categoryId);

        if (newDigitalItem.getVasItems() != null && !newDigitalItem.getVasItems().isEmpty()) {
            for (VasItem vasItem : newDigitalItem.getVasItems()) {
                if (vasItem.getPrice() > newDigitalItem.getPrice()) {
                    throw new Error("The price of VasItem cannot be higher than the DigitalItem's price.");
                }
            }
        }

        Optional<Item> existingDigitalItem = digitalItemRepository.findById(newDigitalItem.getItemId());
        if (existingDigitalItem.isPresent()) {
            Item digitalItemToUpdate = existingDigitalItem.get();
            int updatedQuantity = Math.min(digitalItemToUpdate.getQuantity() + newDigitalItem.getQuantity(), MAX_DIGITAL_ITEM_QUANTITY);
            digitalItemToUpdate.setQuantity(updatedQuantity);
            return (DigitalItem) digitalItemRepository.save(digitalItemToUpdate);
        } else {
            return digitalItemRepository.save(newDigitalItem);
        }

    }
    public VasItem addVasItem(String categoryId, VasItem newVasItem) {
        validateVasItemConstraints(categoryId, newVasItem);

        DefaultItem defaultItem = (DefaultItem) itemRepository.findById(newVasItem.getItemId())
                .orElseThrow(() -> new RuntimeException("DefaultItem not found"));

        validateVasItemPrice(defaultItem, newVasItem);
        validateVasItemCount(defaultItem);

        Optional<Item> existingVasItem = vasItemRepository.findById(newVasItem.getItemId());
        if (existingVasItem.isPresent()) {
            Item vasItemToUpdate = existingVasItem.get();
            int updatedQuantity = Math.min(vasItemToUpdate.getQuantity() + newVasItem.getQuantity(), 3);
            vasItemToUpdate.setQuantity(updatedQuantity);
            return (VasItem) vasItemRepository.save(vasItemToUpdate);
        } else {
            return vasItemRepository.save(newVasItem);
        }
    }
    private void validateVasItemPrice(DefaultItem defaultItem, VasItem vasItem) {
        if (vasItem.getPrice() > defaultItem.getPrice()) {
            throw new Error("The price of VasItem cannot be higher than the DefaultItem's price.");
        }
    }
    private void validateVasItemConstraints(String categoryId, VasItem vasItem) {
        if (!CategoryEnum.FURNITURE.getCategoryId().equals(categoryId) &&
                !CategoryEnum.ELECTRONICS.getCategoryId().equals(categoryId)) {
            throw new Error("VasItems can only be added as sub-items to DefaultItems in Furniture or Electronics categories.");
        }

        if (!CategoryEnum.VAS_ITEM_CATEGORY.getCategoryId().equals(vasItem.getCategoryId())) {
            throw new Error("Invalid category ID for VasItem: " + vasItem.getCategoryId());
        }

        if (vasItem.getSellerId() != VAS_ITEM_SELLER_ID) {
            throw new Error("VasItems can only be defined with a seller ID of 5003.");
        }
    }
    private void validateVasItemCount(DefaultItem defaultItem) {
        int vasItemCount = countVasItems(defaultItem);
        if (vasItemCount >= 3) {
            throw new Error("A maximum of 3 VasItems can be added to a DefaultItem.");
        }
    }
    private int countVasItems(DefaultItem defaultItem) {
        return (int) defaultItem.getVasItems().stream()
                .filter(item -> CategoryEnum.VAS_ITEM_CATEGORY.getCategoryId().equals(item.getCategoryId()))
                .count();
    }
    private void validateCategory(String categoryId) {
        if (!DIGITAL_ITEM_CATEGORY.getCategoryId().equals(categoryId)) {
            throw new Error("Invalid category ID for DigitalItems: " + categoryId);
        }
    }
}
