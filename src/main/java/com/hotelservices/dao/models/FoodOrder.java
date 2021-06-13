package com.hotelservices.dao.models;

import com.hotelservices.dto.DishDTO;
import com.hotelservices.enums.OrderStatus;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.Date;

@Document(collection = "foodOrder")
public class FoodOrder {

    @Getter
    @Setter
    @Id
    private String id;

    @Getter
    @Setter
    private ArrayList<DishDTO> dishes;

    @Getter
    @Setter
    private String orderType;

    @Getter
    @Setter
    private String orderVenue;

    @Getter
    @Setter
    private String specialRequirements;

    @Getter
    @Setter
    private OrderStatus orderStatus;

    @Getter
    @Setter
    private Double totalPrice;

    @Getter
    @Setter
    private Date createdAt;

    @Getter
    @Setter
    private Date updatedAt;

    public FoodOrder(String id, ArrayList<DishDTO> dishes, String orderType, String orderVenue, String specialRequirements, OrderStatus orderStatus, Double totalPrice, Date createdAt, Date updatedAt){
        this.id = id; this.dishes = dishes; this.orderType = orderType; this.orderVenue = orderVenue; this.specialRequirements = specialRequirements;
        this.orderStatus = orderStatus; this.totalPrice=totalPrice; this.createdAt = createdAt;  this.updatedAt = updatedAt;
    }
}
