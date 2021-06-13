package com.hotelservices.service;

import com.hotelservices.constats.FoodOrderConstant;
import com.hotelservices.dao.models.Food;
import com.hotelservices.dao.repocitories.FoodRepository;
import com.hotelservices.dto.FoodDTO;
import com.hotelservices.exception.FoodOrderException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.hotelservices.util.FoodOrderServiceUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class FoodServiceImpl implements FoodService {

    @Autowired
    private FoodRepository foodRepository;

    @Override
    public FoodDTO createFood(FoodDTO foodDTO) {

        String foodId = FoodOrderServiceUtils.createUUid().toString();
        Food food = new Food(foodId, foodDTO.getItemCode(), foodDTO.getName(), foodDTO.getFoodCategory(),foodDTO.getDetails(), foodDTO.getPrice());
        Food createdFood = foodRepository.save(food);
        return FoodOrderServiceUtils.convertToFoodDTO(createdFood);
    }

    @Override
    public FoodDTO updateFood(FoodDTO foodDTO, String foodId) {
        Optional<Food> existingFood = foodRepository.findById(foodId);

        if(existingFood.isPresent()){
            Food food = new Food(foodId, foodDTO.getItemCode(), foodDTO.getName(), foodDTO.getFoodCategory(),foodDTO.getDetails(), foodDTO.getPrice());
            Food updatedFood = foodRepository.save(food);
            return FoodOrderServiceUtils.convertToFoodDTO(updatedFood);
        } else {
            throw new FoodOrderException(FoodOrderConstant.FOOD_ITEM_NOT_FOUND_ERROR_MESSAGE);
        }
    }

    @Override
    public FoodDTO getFoodByItemCode(String itemCode) {
        Food existingFood = foodRepository.findByItemCode(itemCode);
        if(existingFood != null){
            return FoodOrderServiceUtils.convertToFoodDTO(existingFood);
        } else {
            throw new FoodOrderException(FoodOrderConstant.FOOD_ITEM_NOT_FOUND_ERROR_MESSAGE);
        }
    }

    @Override public List<FoodDTO> getAllFoods() {
        List<FoodDTO> foodList = new ArrayList<>();
        List<Food> allFoods = foodRepository.findAll();
        for (Food food : allFoods) {
            foodList.add(FoodOrderServiceUtils.convertToFoodDTO(food));
        }

        return foodList;
    }

    @Override
    public List<FoodDTO> getFoodByFoodCategory(String foodCategory) {
        List<Food> foodOrders = foodRepository.findByFoodCategory(foodCategory);
        List<FoodDTO> foodList = new ArrayList<>();
        for (Food food : foodOrders) {
            foodList.add(FoodOrderServiceUtils.convertToFoodDTO(food));
        }

        return foodList;
    }

    @Override public FoodDTO deleteByFoodId(String foodId) {

        Optional<Food> existingFood = foodRepository.findById(foodId);
        try{
            foodRepository.deleteById(foodId);
            return FoodOrderServiceUtils.convertToFoodDTO(existingFood.get());
        } catch (Exception e){
            throw new FoodOrderException(FoodOrderConstant.FOOD_ITEM_CAN_NOT_DELETE);
        }
    }
}
