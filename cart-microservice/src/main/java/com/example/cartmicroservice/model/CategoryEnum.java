package com.example.cartmicroservice.model;

public enum CategoryEnum {
    FURNITURE("1001"),
    DIGITALITEMS("7889"),
    ELECTRONICS("3008"),
    VAS_ITEM_CATEGORY("3342");

    private final String categoryId;

    CategoryEnum(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryId() {
        return categoryId;
    }
}
