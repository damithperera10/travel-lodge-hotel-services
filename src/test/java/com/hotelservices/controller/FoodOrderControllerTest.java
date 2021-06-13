package com.hotelservices.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hotelservices.dto.FoodOrderDTO;
import com.hotelservices.service.FoodOrderService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(MockitoJUnitRunner.class)
public class FoodOrderControllerTest {

    @Mock
    private FoodOrderService foodOrderService;

    @InjectMocks
    FoodOrderController foodOrderController;

    @Autowired
    private MockMvc mockMvc;

    private String foodOrder = "{\"foodOrderId\":\"fc46227d-f399-4939-a35c-b4184f3c6fb2\",\"dishes\":[{\"name\":\"fried rice\",\"flavor\":\"checken\",\"items\":2,\"potionSize\":\"Large\",\"foodCategory\":\"CHINESE\"},{\"name\":\"devel chicken\",\"flavor\":\"checken\",\"items\":2,\"potionSize\":\"Large\",\"foodCategory\":\"CHINESE\"},{\"name\":\"biriyani\",\"flavor\":\"mutton\",\"items\":2,\"potionSize\":\"Large\",\"foodCategory\":\"CHINESE\"}],\"orderType\":\"dining-in\",\"orderVenue\":\"main resturant\",\"specialRequirements\":\"add more spicy\",\"orderStatus\":\"PENDING\",\"createdAt\":\"2021-06-12T20:24:19.230+00:00\",\"updatedAt\":\"2021-06-12T20:24:19.230+00:00\"}";

    private FoodOrderDTO foodOrderDTOObject;

    private List<FoodOrderDTO> foodOrderDTOList = new ArrayList<>();

    @Before
    public void setUp() throws IOException {
        mockMvc = MockMvcBuilders.standaloneSetup(foodOrderController).build();
        ObjectMapper mapper = new ObjectMapper();

        foodOrderDTOObject = mapper.readValue(foodOrder, FoodOrderDTO.class);
        foodOrderDTOList.add(foodOrderDTOObject);
    }

    @Test
    public void createFoodOderTest() throws Exception {
        Mockito.when(foodOrderService.createFoodOrder(Mockito.any())).thenReturn(foodOrderDTOObject);
        RequestBuilder request = MockMvcRequestBuilders.post("/v1/foodorder").content(foodOrder).contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);
        MvcResult result = mockMvc.perform(request).andExpect(status().isCreated()).andReturn();
        verify(foodOrderService).createFoodOrder(Mockito.any());
        assertEquals("{\"foodOrderId\":\"fc46227d-f399-4939-a35c-b4184f3c6fb2\",\"dishes\":[{\"name\":\"fried rice\",\"flavor\":\"checken\",\"items\":2,\"potionSize\":\"Large\",\"foodCategory\":\"CHINESE\"},{\"name\":\"devel chicken\",\"flavor\":\"checken\",\"items\":2,\"potionSize\":\"Large\",\"foodCategory\":\"CHINESE\"},{\"name\":\"biriyani\",\"flavor\":\"mutton\",\"items\":2,\"potionSize\":\"Large\",\"foodCategory\":\"CHINESE\"}],\"orderType\":\"dining-in\",\"orderVenue\":\"main resturant\",\"specialRequirements\":\"add more spicy\",\"orderStatus\":\"PENDING\",\"createdAt\":\"2021-06-12T20:24:19.230+00:00\",\"updatedAt\":\"2021-06-12T20:24:19.230+00:00\"}", result.getResponse().getContentAsString());
    }

    @Test
    public void updateFoodOrderTest() throws Exception {
        Mockito.when(foodOrderService.updateFoodOrder(Mockito.any(), Mockito.anyString())).thenReturn(foodOrderDTOObject);
        RequestBuilder request = MockMvcRequestBuilders.put("/v1/foodorder/c2c077ca-f933-4c87-a4b0-becf36b9c364333").content(foodOrder).contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON);
        MvcResult result = mockMvc.perform(request).andExpect(status().isOk()).andReturn();
        verify(foodOrderService).updateFoodOrder(Mockito.any(),Mockito.anyString());
        assertEquals("{\"foodOrderId\":\"fc46227d-f399-4939-a35c-b4184f3c6fb2\",\"dishes\":[{\"name\":\"fried rice\",\"flavor\":\"checken\",\"items\":2,\"potionSize\":\"Large\",\"foodCategory\":\"CHINESE\"},{\"name\":\"devel chicken\",\"flavor\":\"checken\",\"items\":2,\"potionSize\":\"Large\",\"foodCategory\":\"CHINESE\"},{\"name\":\"biriyani\",\"flavor\":\"mutton\",\"items\":2,\"potionSize\":\"Large\",\"foodCategory\":\"CHINESE\"}],\"orderType\":\"dining-in\",\"orderVenue\":\"main resturant\",\"specialRequirements\":\"add more spicy\",\"orderStatus\":\"PENDING\",\"createdAt\":\"2021-06-12T20:24:19.230+00:00\",\"updatedAt\":\"2021-06-12T20:24:19.230+00:00\"}", result.getResponse().getContentAsString());
    }

    @Test
    public void getFoodOderByIdTest() throws Exception {

        Mockito.when(foodOrderService.getFoodOrderById(Mockito.anyString())).thenReturn(foodOrderDTOObject);

        RequestBuilder request = MockMvcRequestBuilders.get("/v1/foodorder/c2c077ca-f933-4c87-a4b0-becf36b9c364333", Mockito.anyString()).accept(MediaType.APPLICATION_JSON);
        MvcResult result = mockMvc.perform(request).andExpect(status().isOk()).andReturn();

        verify(foodOrderService).getFoodOrderById(Mockito.anyString());
        assertNotNull(result.getResponse().getContentAsString());
    }

    @Test
    public void getAllFoodOrdersTest() throws Exception {

        Mockito.when(foodOrderService.getAllFoodOrders()).thenReturn(foodOrderDTOList);

        RequestBuilder request = MockMvcRequestBuilders.get("/v1/foodorder", Mockito.anyString()).accept(MediaType.APPLICATION_JSON);
        MvcResult result = mockMvc.perform(request).andExpect(status().isOk()).andReturn();

        verify(foodOrderService).getAllFoodOrders();
        assertNotNull(result.getResponse().getContentAsString());
    }

    @Test
    public void getFoodOrderByStatusTest() throws Exception {

        Mockito.when(foodOrderService.getFoodOrderByStatus(Mockito.anyString())).thenReturn(foodOrderDTOList);

        RequestBuilder request = MockMvcRequestBuilders.get("/v1/foodorder/status/PENDING", Mockito.anyString()).accept(MediaType.APPLICATION_JSON);
        MvcResult result = mockMvc.perform(request).andExpect(status().isOk()).andReturn();

        verify(foodOrderService).getFoodOrderByStatus(Mockito.anyString());
        assertNotNull(result.getResponse().getContentAsString());
    }

    @Test
    public void deleteFoodOrderTest() throws Exception {

        Mockito.when(foodOrderService.deleteFoodOrder(Mockito.anyString())).thenReturn(foodOrderDTOObject);
        RequestBuilder request = MockMvcRequestBuilders.delete("/v1/foodorder/c2c077ca-f933-4c87-a4b0-becf36b9c364333").content(foodOrder).contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON);
        MvcResult result = mockMvc.perform(request).andExpect(status().isOk()).andReturn();
        verify(foodOrderService).deleteFoodOrder(Mockito.anyString());
        assertEquals("{\"foodOrderId\":\"fc46227d-f399-4939-a35c-b4184f3c6fb2\",\"dishes\":[{\"name\":\"fried rice\",\"flavor\":\"checken\",\"items\":2,\"potionSize\":\"Large\",\"foodCategory\":\"CHINESE\"},{\"name\":\"devel chicken\",\"flavor\":\"checken\",\"items\":2,\"potionSize\":\"Large\",\"foodCategory\":\"CHINESE\"},{\"name\":\"biriyani\",\"flavor\":\"mutton\",\"items\":2,\"potionSize\":\"Large\",\"foodCategory\":\"CHINESE\"}],\"orderType\":\"dining-in\",\"orderVenue\":\"main resturant\",\"specialRequirements\":\"add more spicy\",\"orderStatus\":\"PENDING\",\"createdAt\":\"2021-06-12T20:24:19.230+00:00\",\"updatedAt\":\"2021-06-12T20:24:19.230+00:00\"}", result.getResponse().getContentAsString());

    }

}
