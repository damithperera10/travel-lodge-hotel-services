package com.hotelservices.service;

import com.hotelservices.dao.models.FoodOrder;
import com.hotelservices.dao.repocitories.FoodOrderRepository;
import com.hotelservices.dto.FoodOrderDTO;
import com.hotelservices.exception.FoodOrderException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import com.hotelservices.util.FoodOrderServiceUtils;
import com.hotelservices.constats.FoodOrderConstant;

@Component
public class FoodOrderServiceImpl implements FoodOrderService {

    @Autowired
    private FoodOrderRepository foodOrderRepository;

    @Override
    public FoodOrderDTO createFoodOrder(FoodOrderDTO foodOrderDTO) {

        String foodOrderId = FoodOrderServiceUtils.createUUid().toString();
        Date createdAt = new Date();
        Date updatedAt = new Date();
        FoodOrder foodOrder = new FoodOrder(foodOrderId, foodOrderDTO.getDishes(), foodOrderDTO.getOrderType(), foodOrderDTO.getOrderVenue(),
            foodOrderDTO.getSpecialRequirements(), foodOrderDTO.getOrderStatus(), foodOrderDTO.getTotalPrice(), createdAt, updatedAt);

        FoodOrder createObject = foodOrderRepository.save(foodOrder);
        return FoodOrderServiceUtils.convertToFoodOrderDTO(createObject);
    }

    @Override
    public FoodOrderDTO updateFoodOrder(FoodOrderDTO foodOrderDTO, String orderId) {

        Optional<FoodOrder> existingFoodOrder = foodOrderRepository.findById(orderId);

        if(existingFoodOrder.isPresent()){
            foodOrderDTO.setUpdatedAt(new Date());
            FoodOrder foodOrder = new FoodOrder(orderId, foodOrderDTO.getDishes(), foodOrderDTO.getOrderType(), foodOrderDTO.getOrderVenue(),
                foodOrderDTO.getSpecialRequirements(), foodOrderDTO.getOrderStatus(), foodOrderDTO.getTotalPrice(), foodOrderDTO.getCreatedAt(), foodOrderDTO.getUpdatedAt());
            FoodOrder updatedFoodOrder = foodOrderRepository.save(foodOrder);
            return FoodOrderServiceUtils.convertToFoodOrderDTO(updatedFoodOrder);
        } else {
            throw new FoodOrderException(FoodOrderConstant.FOOD_ORDER_NOT_FOUND_ERROR_MESSAGE);
        }

    }

    @Override
    public FoodOrderDTO getFoodOrderById(String orderId) {
        Optional<FoodOrder> existingFoodOrder = foodOrderRepository.findById(orderId);

        if(existingFoodOrder.isPresent()){
            return FoodOrderServiceUtils.convertToFoodOrderDTO(existingFoodOrder.get());
        } else {
            throw new FoodOrderException(FoodOrderConstant.FOOD_ORDER_NOT_FOUND_ERROR_MESSAGE);
        }
    }

    @Override
    public List<FoodOrderDTO> getAllFoodOrders() {
        List<FoodOrderDTO> foodOrderList = new ArrayList<>();
        List<FoodOrder> allFoodOrders = foodOrderRepository.findAll();
        for (FoodOrder order : allFoodOrders) {
            foodOrderList.add(FoodOrderServiceUtils.convertToFoodOrderDTO(order));
        }

        return foodOrderList;
    }

    @Override public List<FoodOrderDTO> getFoodOrderByStatus(String status) {

        List<FoodOrder> foodOrders = foodOrderRepository.findByOrderStatus(status);
        List<FoodOrderDTO> foodOrderList = new ArrayList<>();
        for (FoodOrder order : foodOrders) {
            foodOrderList.add(FoodOrderServiceUtils.convertToFoodOrderDTO(order));
        }

        return foodOrderList;
    }

    @Override public FoodOrderDTO deleteFoodOrder(String orderId) {

        Optional<FoodOrder> existingFoodOrder = foodOrderRepository.findById(orderId);
        try{
            foodOrderRepository.deleteById(orderId);
            return FoodOrderServiceUtils.convertToFoodOrderDTO(existingFoodOrder.get());
        } catch (Exception e){
           throw new FoodOrderException(FoodOrderConstant.FOOD_ORDER_CAN_NOT_DELERE_ERROR_MESSAGE);
        }

    }

}
