package com.hotelservices.dao.repocitories;

import com.hotelservices.dao.models.Food;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface FoodRepository extends MongoRepository<Food, String> {
    List<Food> findByFoodCategory(String foodCategory);
    Food findByItemCode(String name);
}
