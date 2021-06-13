package com.hotelservices.dto;

import com.hotelservices.enums.OrderStatus;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Date;

public class FoodOrderDTO {

    @Getter
    @Setter
    private String foodOrderId;

    @Getter
    @Setter
    @NotNull(message = "Foods required")
    @NotEmpty(message = "At least one food required")
    private ArrayList<DishDTO> dishes;

    @Getter
    @Setter
    @NotNull(message = "orderType required")
    private String orderType;

    @Getter
    @Setter
    @NotNull(message = "orderVenue required")
    private String orderVenue;

    @Getter
    @Setter
    private String specialRequirements;

    @NotNull(message = "Order status required." )
    @Getter
    @Setter
    private OrderStatus orderStatus;

    @NotNull(message = "Order price required." )
    @Getter
    @Setter
    private Double totalPrice;

    @Getter
    @Setter
    private Date createdAt;

    @Getter
    @Setter
    private Date updatedAt;

    public FoodOrderDTO(String foodOrderId, ArrayList<DishDTO> dishes, String orderType, String orderVenue, String specialRequirements, OrderStatus orderStatus, Double totatlPrice, Date createdAt, Date updatedAt){
        this.foodOrderId = foodOrderId; this.dishes = dishes; this.orderType = orderType; this.orderVenue = orderVenue; this.specialRequirements = specialRequirements;
        this.orderStatus = orderStatus; this.totalPrice = totatlPrice; this.createdAt = createdAt; this.updatedAt = updatedAt;
    }
}
