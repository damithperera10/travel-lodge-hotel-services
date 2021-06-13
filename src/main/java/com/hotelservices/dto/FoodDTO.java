package com.hotelservices.dto;

import com.hotelservices.enums.FoodCategory;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

public class FoodDTO {

    @Getter
    @Setter
    private String foodId;

    @Getter
    @Setter
    @NotNull(message = "itemCode required")
    private String itemCode;

    @Getter
    @Setter
    @NotNull(message = "name required")
    private String name;

    @Getter
    @Setter
    @NotNull(message = "foodCategory required")
    private FoodCategory foodCategory;

    @Getter
    @Setter
    private String details;

    @Getter
    @Setter
    @NotNull(message = "price required")
    private Double price;

    public FoodDTO(String foodId, String itemCode, String name, FoodCategory foodCategory, String details, Double price){
        this.foodId=foodId; this.itemCode=itemCode; this.name=name; this.foodCategory=foodCategory; this.details=details; this.price=price;
    }

}
