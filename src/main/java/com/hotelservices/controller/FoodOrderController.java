package com.hotelservices.controller;

import com.hotelservices.dto.FoodOrderDTO;
import com.hotelservices.service.FoodOrderService;
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
public class FoodOrderController {

    @Autowired
    private FoodOrderService foodOrderService;

    /**
     * Creates food order.
     *
     * @param foodOrderDTO the foodOrderDTO pay load
     * @return the response entity
     */
    @RequestMapping(value = "/foodorder", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<FoodOrderDTO> createFoodOrder(@Valid @NotNull @RequestBody FoodOrderDTO foodOrderDTO) {

        FoodOrderDTO response = foodOrderService.createFoodOrder(foodOrderDTO);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    /**
     * Update food order.
     *
     * @param foodOrderDTO the foodOrderDTO pay load
     * @param orderId the order id
     * @return the response entity
     */
    @RequestMapping(value = "/foodorder/{orderId}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<FoodOrderDTO> updateFoodOrder(@Valid @NotNull @RequestBody FoodOrderDTO foodOrderDTO, @Valid @NotNull @PathVariable String orderId) {

        try{
            FoodOrderDTO response = foodOrderService.updateFoodOrder(foodOrderDTO, orderId);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }

    }

    /**
     * Get specific food order.
     *
     * @param orderId the orderId
     * @return the response entity
     */
    @RequestMapping(value = "/foodorder/{orderId}", method = RequestMethod.GET)
    public ResponseEntity<FoodOrderDTO> getFoodOderById(@Valid @NotNull @PathVariable String orderId) {

        try{
            FoodOrderDTO response = foodOrderService.getFoodOrderById(orderId);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * Get all food orders.
     *
     * @return the response entity
     */
    @RequestMapping(value = "/foodorder", method = RequestMethod.GET)
    public ResponseEntity<List<FoodOrderDTO>> getAllFoodOrders() {

        List<FoodOrderDTO> response = foodOrderService.getAllFoodOrders();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    /**
     * Get specific food order.
     *
     * @param status the status
     * @return the response entity
     */
    @RequestMapping(value = "/foodorder/status/{status}", method = RequestMethod.GET)
    public ResponseEntity<List<FoodOrderDTO>> getFoodOderByStatus(@Valid @NotNull @PathVariable String status) {

        List<FoodOrderDTO> response = foodOrderService.getFoodOrderByStatus(status);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    /**
     * Delete the foodOrder.
     *
     * @param orderId the foodOrder id
     * @return the response entity
     */
    @RequestMapping(value = "/foodorder/{orderId}", method = RequestMethod.DELETE)
    public ResponseEntity<FoodOrderDTO> deleteFoodOrder(@Valid @NotNull @PathVariable String orderId) {

        try{
            FoodOrderDTO response = foodOrderService.deleteFoodOrder(orderId);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }

    }

}
