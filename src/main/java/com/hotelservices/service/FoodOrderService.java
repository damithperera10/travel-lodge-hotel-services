package com.hotelservices.service;

import com.hotelservices.dto.FoodOrderDTO;

import java.util.List;

/**
 * Interface for FoodOrderService service.
 */
public interface FoodOrderService {

    /**
     * Create the Food Order.
     *
     * @return the Food Order
     */
    FoodOrderDTO createFoodOrder(FoodOrderDTO foodOrderDTO);

    /**
     * Update the Food Order.
     *
     * @param foodOrderDTO payload
     * @param orderId orderId
     * @return the FoodOrderDTO
     */
    FoodOrderDTO updateFoodOrder(FoodOrderDTO foodOrderDTO, String orderId);

    /**
     * Get the Food Order by orderId.
     *
     * @param orderId orderId
     * @return the FoodOrderDTO
     */
    FoodOrderDTO getFoodOrderById(String orderId);

    /**
     * Get all Food Orders.
     *
     * @return the Food Order list
     */
    List<FoodOrderDTO> getAllFoodOrders();

    /**
     * Get the Food Order by status.
     *
     * @param status orderId
     * @return the FoodOrderDTO
     */
    List<FoodOrderDTO> getFoodOrderByStatus(String status);

    /**
     * Delete the Food Order by orderId.
     *
     * @param orderId orderId
     * @return the FoodOrderDTO
     */
    FoodOrderDTO deleteFoodOrder(String orderId);
}
