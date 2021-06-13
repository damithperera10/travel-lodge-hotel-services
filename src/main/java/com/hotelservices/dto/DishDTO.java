package com.hotelservices.dto;

import com.hotelservices.enums.FoodCategory;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

public class DishDTO {

    /**
     * DishDTO name
     */
    @Getter
    @Setter
    @NotNull(message = "Dish name is required.")
    private String name;

    /**
     * DishDTO flavor
     */
    @Getter
    @Setter
    @NotNull(message = "Dish flavor is required.")
    private String flavor;

    /**
     * Number of items
     */
    @Getter
    @Setter
    @NotNull(message = "Dish items is required.")
    private int items;

    /**
     * Potion Size
     */
    @Getter
    @Setter
    @NotNull(message = "Dish potionSize is required.")
    private String potionSize;

    @Getter
    @Setter
    @NotNull(message = "Dish foodCategory is required.")
    private FoodCategory foodCategory;

    public DishDTO(String name, String flavor, int items, String potionSize, FoodCategory foodCategory){
        this.name = name;
        this.flavor = flavor;
        this.items = items;
        this.potionSize = potionSize;
        this.foodCategory = foodCategory;
    }
}
