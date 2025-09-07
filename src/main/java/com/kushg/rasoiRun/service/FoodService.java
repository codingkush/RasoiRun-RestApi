package com.kushg.rasoiRun.service;

import com.kushg.rasoiRun.io.FoodRequest;
import com.kushg.rasoiRun.io.FoodResponse;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface FoodService {

//    String uploadFile(MultipartFile file);

    FoodResponse addFood(FoodRequest request, MultipartFile file);

    List<FoodResponse> readFoods();
    FoodResponse readFood(String id);

    void deleteFood(String id);
}
