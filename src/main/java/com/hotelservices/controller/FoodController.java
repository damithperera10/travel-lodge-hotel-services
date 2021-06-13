package com.hotelservices.controller;


import com.hotelservices.dto.FoodDTO;
import com.hotelservices.service.FoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@RestController()
@RequestMapping({ "/v1" })
public class FoodController {

    @Autowired
    private FoodService foodService;

    /**
     * Creates food.
     *
     * @param foodDTO the foodDTO pay load
     * @return the response entity
     */
    @RequestMapping(value = "/food", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<FoodDTO> createFoodOrder(@Valid @NotNull @RequestBody FoodDTO foodDTO) {
        FoodDTO response = foodService.createFood(foodDTO);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    /**
     * Update food order.
     *
     * @param foodDTO the foodDTO pay load
     * @param foodId the food id
     * @return the response entity
     */
    @RequestMapping(value = "/food/{foodId}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<FoodDTO> updateFood(@Valid @NotNull @RequestBody FoodDTO foodDTO, @Valid @NotNull @PathVariable String foodId) {

        try{
            FoodDTO response = foodService.updateFood(foodDTO, foodId);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }

    }

    /**
     * Get specific food.
     *
     * @param itemCode the food name
     * @return the response entity
     */
    @RequestMapping(value = "/food/item/{itemCode}", method = RequestMethod.GET)
    public ResponseEntity<FoodDTO> getFoodByItemCode(@Valid @NotNull @PathVariable String itemCode) {

        FoodDTO response = foodService.getFoodByItemCode(itemCode);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    /**
     * Get all foods.
     *
     * @return the response entity
     */
    @RequestMapping(value = "/food", method = RequestMethod.GET)
    public ResponseEntity<List<FoodDTO>> getAllFoods() {

        List<FoodDTO> response = foodService.getAllFoods();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    /**
     * Get specific foods by foodCategory.
     *
     * @param foodCategory the foodCategory
     * @return the response entity
     */
    @RequestMapping(value = "/food/foodCategory/{foodCategory}", method = RequestMethod.GET)
    public ResponseEntity<List<FoodDTO>> getFoodByFoodCategory(@Valid @NotNull @PathVariable String foodCategory) {

        List<FoodDTO> response = foodService.getFoodByFoodCategory(foodCategory);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    /**
     * Delete the food.
     *
     * @param foodId the food id
     * @return the response entity
     */
    @RequestMapping(value = "/food/{foodId}", method = RequestMethod.DELETE)
    public ResponseEntity<FoodDTO> deleteFoodItem(@Valid @NotNull @PathVariable String foodId) {

        try{
            FoodDTO response = foodService.deleteByFoodId(foodId);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }

    }
}
