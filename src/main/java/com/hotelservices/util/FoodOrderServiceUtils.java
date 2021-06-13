package com.hotelservices.util;

import com.hotelservices.dao.models.Food;
import com.hotelservices.dao.models.FoodOrder;
import com.hotelservices.dto.FoodDTO;
import com.hotelservices.dto.FoodOrderDTO;

import java.util.UUID;

public class FoodOrderServiceUtils {

    /**
     * Create uuid.
     *
     * @return uuid
     */
    public static UUID createUUid(){
        return UUID.randomUUID();
    }

    /**
     * Convert foodOrder object to foodOrderDTO.
     *
     * @param foodOrder the food order object
     * @return FoodOrderDTO
     */
    public static FoodOrderDTO convertToFoodOrderDTO(FoodOrder foodOrder) {
        if(foodOrder != null) {
            return new FoodOrderDTO(foodOrder.getId(), foodOrder.getDishes(), foodOrder.getOrderType(), foodOrder.getOrderVenue(), foodOrder.getSpecialRequirements(),
                foodOrder.getOrderStatus(), foodOrder.getTotalPrice(), foodOrder.getCreatedAt(), foodOrder.getUpdatedAt());
        } else return null;
    }

    /**
     * Convert food object to foodDTO.
     *
     * @param food the food object
     * @return FoodDTO
     */
    public static FoodDTO convertToFoodDTO(Food food) {
        if(food != null) {
            return new FoodDTO(food.getFoodId(),food.getItemCode(),food.getName(), food.getFoodCategory(), food.getDetails(), food.getPrice());
        } else return null;
    }
}
