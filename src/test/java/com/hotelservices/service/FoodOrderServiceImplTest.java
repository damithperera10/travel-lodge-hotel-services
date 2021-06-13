package com.hotelservices.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hotelservices.constats.FoodOrderConstant;
import com.hotelservices.dao.repocitories.FoodOrderRepository;
import com.hotelservices.dto.FoodOrderDTO;
import com.hotelservices.exception.FoodOrderException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class FoodOrderServiceImplTest {

    @InjectMocks
    private FoodOrderServiceImpl foodOrderServiceImpl;

    @Mock
    private FoodOrderRepository foodOrderRepository;

    private String foodOrder = "{\"foodOrderId\":\"fc46227d-f399-4939-a35c-b4184f3c6fb2\",\"dishes\":[{\"name\":\"fried rice\",\"flavor\":\"checken\",\"items\":2,\"potionSize\":\"Large\",\"foodCategory\":\"CHINESE\"},{\"name\":\"devel chicken\",\"flavor\":\"checken\",\"items\":2,\"potionSize\":\"Large\",\"foodCategory\":\"CHINESE\"},{\"name\":\"biriyani\",\"flavor\":\"mutton\",\"items\":2,\"potionSize\":\"Large\",\"foodCategory\":\"CHINESE\"}],\"orderType\":\"dining-in\",\"orderVenue\":\"main resturant\",\"specialRequirements\":\"add more spicy\",\"orderStatus\":\"PENDING\",\"createdAt\":\"2021-06-12T20:24:19.230+00:00\",\"updatedAt\":\"2021-06-12T20:24:19.230+00:00\"}";

    private FoodOrderDTO foodOrderDTO;

    private List<FoodOrderDTO> foodOrderDTOList = new ArrayList<>();

    @Before
    public void setUp() throws IOException {
        foodOrderServiceImpl = new FoodOrderServiceImpl();
        MockitoAnnotations.initMocks(this);
        ObjectMapper mapper = new ObjectMapper();

        foodOrderDTO = mapper.readValue(foodOrder, FoodOrderDTO.class);
        foodOrderDTOList.add(foodOrderDTO);

    }

    @Test
    public void createFoodOrder() {
        Mockito.when(foodOrderRepository.save(Mockito.any())).thenReturn(foodOrderDTO);
        assertEquals(foodOrderDTO, foodOrderServiceImpl.createFoodOrder(foodOrderDTO));
        verify(foodOrderRepository).save(Mockito.any());
    }

    @Test
    public void updateFoodOrder() {
        Mockito.when(foodOrderRepository.findById(Mockito.any())).thenReturn(Mockito.any());
        Mockito.when(foodOrderRepository.save(Mockito.any())).thenReturn(foodOrderDTO);
        foodOrderServiceImpl.updateFoodOrder(foodOrderDTO, foodOrderDTO.getFoodOrderId());
        verify(foodOrderRepository).findById(Mockito.anyString());
        verify(foodOrderRepository).save(Mockito.any());

    }

    @Test(expected = FoodOrderException.class)
    public void updateFoodOrderException_whenFoodOrderNotFound() {

        Mockito.when(foodOrderRepository.findById(Mockito.anyString())).thenReturn(Mockito.any());
        when(foodOrderServiceImpl.updateFoodOrder(foodOrderDTO, foodOrderDTO.getFoodOrderId()))
            .thenThrow(new FoodOrderException(FoodOrderConstant.FOOD_ORDER_NOT_FOUND_ERROR_MESSAGE));
    }

    @Test
    public void getAllFoodOderTest() {

        Mockito.when(foodOrderRepository.findAll()).thenReturn(Mockito.any());
        assertEquals(foodOrderDTOList, foodOrderRepository.findAll());
        verify(foodOrderRepository).findAll();
    }

    @Test
    public void getFoodOrderByIdTest() {

        Mockito.when(foodOrderRepository.findById(Mockito.anyString())).thenReturn(Mockito.any());
        assertNotNull(foodOrderServiceImpl.getFoodOrderById(Mockito.anyString()));
        verify(foodOrderRepository).findById(Mockito.anyString());
    }

    @Test(expected = FoodOrderException.class)
    public void getFoodOrderByIdNotFoundTest() {

        Mockito.when(foodOrderRepository.findById(Mockito.anyString())).thenReturn(Mockito.any());
        when(foodOrderServiceImpl.getFoodOrderById(Mockito.anyString())).thenThrow(new FoodOrderException(FoodOrderConstant.FOOD_ORDER_NOT_FOUND_ERROR_MESSAGE));
    }

    @Test
    public void getFoodOrderByStatusTest() {

        Mockito.when(foodOrderRepository.findByOrderStatus(Mockito.anyString())).thenReturn(Mockito.any());
        assertNotNull(foodOrderServiceImpl.getFoodOrderByStatus(Mockito.anyString()));
        verify(foodOrderRepository).findByOrderStatus(Mockito.anyString());
    }

    @Test
    public void DeleteFoodOrderTest() {
        Mockito.doNothing().when(foodOrderRepository).deleteById(Mockito.anyString());
        Mockito.when(foodOrderRepository.findById(Mockito.any())).thenReturn(Mockito.any());
        assertNotNull(foodOrderServiceImpl.deleteFoodOrder(Mockito.anyString()));
        verify(foodOrderRepository).deleteById(Mockito.anyString());
    }

    @Test(expected = FoodOrderException.class)
    public void DeleteFoodOrderException_WhenFoodOrderNotFoundTest() {
        Mockito.doNothing().when(foodOrderRepository).deleteById(Mockito.anyString());
        Mockito.when(foodOrderRepository.findById(Mockito.any())).thenReturn(Mockito.any());
        when(foodOrderServiceImpl.deleteFoodOrder(Mockito.anyString())).thenThrow(new FoodOrderException(FoodOrderConstant.FOOD_ORDER_NOT_FOUND_ERROR_MESSAGE));
    }

}
