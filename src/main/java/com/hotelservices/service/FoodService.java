package com.hotelservices.service;

import com.hotelservices.dto.FoodDTO;

import java.util.List;

/**
 * Interface for Food service.
 */
public interface FoodService {

    /**
     * Create the Food.
     *
     * @param foodDTO food payload
     * @return the Food
     */

    FoodDTO createFood(FoodDTO foodDTO);

    /**
     * Update the Food.
     *
     * @param foodDTO food payload
     * @param foodId foodId
     * @return the Food
     */

    FoodDTO updateFood(FoodDTO foodDTO, String foodId);

    /**
     * Get Food by itemCode.
     *
     * @param itemCode the food name
     * @return the Food
     */
    FoodDTO getFoodByItemCode(String itemCode);

    /**
     * Get all Foods.
     *
     * @return the Food
     */
    List<FoodDTO> getAllFoods();


    /**
     * Get food by food category.
     *
     * @param foodCategory the food name
     * @return the Food
     */
    List<FoodDTO> getFoodByFoodCategory(String foodCategory);

    /**
     * Delete Food by foodid.
     *
     * @param foodId the food name
     * @return the Food
     */
    FoodDTO deleteByFoodId(String foodId);
}
