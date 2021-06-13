package com.hotelservices.dao.models;

import com.hotelservices.enums.FoodCategory;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "food")
public class Food {

    @Getter
    @Setter
    @Id
    private String foodId;

    @Getter
    @Setter
    private String itemCode;

    @Getter
    @Setter
    private String name;

    @Getter
    @Setter
    private FoodCategory foodCategory;

    @Getter
    @Setter
    private String details;

    @Getter
    @Setter
    private Double price;

    public Food(String foodId, String itemCode, String name, FoodCategory foodCategory, String details, Double price){
        this.foodId=foodId; this.itemCode=itemCode; this.name=name; this.foodCategory=foodCategory; this.details=details; this.price=price;
    }
}
