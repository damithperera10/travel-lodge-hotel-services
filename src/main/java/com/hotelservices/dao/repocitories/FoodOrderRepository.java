package com.hotelservices.dao.repocitories;

import com.hotelservices.dao.models.FoodOrder;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

/**
 * Food order mongo repository
 * */
public interface FoodOrderRepository extends MongoRepository<FoodOrder, String> {
    List<FoodOrder> findByOrderStatus(String status);
}
