package com.davy.restapi.product.dto;

import com.davy.restapi.category.dto.CategoryDetails;
import com.davy.restapi.inventory.dto.InventoryItems;
import com.davy.restapi.inventory.entity.Inventory;
import com.davy.restapi.subcategory.dto.SubCategoryItems;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class ProductDetails {

    @JsonProperty("id")
    private  Long id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("imageUrl")
    private String imageUrl;

    @JsonProperty("description")
    private String description;

    @JsonProperty("purchasePrice")
    private float purchasePrice;

    @JsonProperty("sellingPrice")
    private float sellingPrice;

    @JsonProperty("vat")
    private int vat;

    @JsonProperty("category")
    private CategoryDetails category;

    @JsonIgnore
    @JsonProperty("subCategory")
    private SubCategoryItems subCategory;

    @JsonProperty("inventory")
    private InventoryItems inventory;

    public ProductDetails(Long id,
                          String name,
                          String description,
                          String imageUrl,
                          float purchasePrice,
                          float sellingPrice,
                          int vat,
                          CategoryDetails category,
                          SubCategoryItems subCategory,
                          InventoryItems inventory
    ) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.imageUrl = imageUrl;
        this.purchasePrice = purchasePrice;
        this.sellingPrice = sellingPrice;
        this.vat = vat;
        this.category = category;
        this.subCategory = subCategory;
        this.inventory = inventory;
    }
}
