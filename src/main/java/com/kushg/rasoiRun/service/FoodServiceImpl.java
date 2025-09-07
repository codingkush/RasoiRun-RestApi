package com.kushg.rasoiRun.service;

import com.kushg.rasoiRun.entity.FoodEntity;
import com.kushg.rasoiRun.io.FoodRequest;
import com.kushg.rasoiRun.io.FoodResponse;
import com.kushg.rasoiRun.repository.FoodRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FoodServiceImpl implements FoodService{

//    @Autowired
    private final CloudinaryService cloudinaryService;
    private final FoodRepository foodRepository;

    @Override
    public FoodResponse addFood(FoodRequest request, MultipartFile file) {

        FoodEntity newfoodEntity = convertToEntity(request);
        String imageUrl = cloudinaryService.uploadFile(file); // upload image and get URL

        newfoodEntity.setImgUrl(imageUrl);
        newfoodEntity = foodRepository.save(newfoodEntity);

        return convertToResponse(newfoodEntity);
    }

    @Override
    public List<FoodResponse> readFoods() {
        List<FoodEntity> databaseEntries = foodRepository.findAll();
        return databaseEntries.stream().map(object -> convertToResponse(object)).collect(Collectors.toList());
    }

    @Override
    public FoodResponse readFood(String id) {
        FoodEntity existingFood = foodRepository.findById(id).orElseThrow(()-> new RuntimeException("food not found for the id:" + id));
        return convertToResponse(existingFood);
    }


    @Override
    public void deleteFood(String id) {
        FoodResponse response = readFood(id);
        String imageUrl = response.getImgUrl();
        System.out.println("Fetched Image URL: " + imageUrl);

        String publicId = extractPublicId(imageUrl);
        System.out.println("Extracted Cloudinary Public ID: " + publicId);

        boolean isFileDeleted = cloudinaryService.deleteFile(publicId);
        System.out.println("Is file deleted from Cloudinary? " + isFileDeleted);

        if (isFileDeleted) {
            foodRepository.deleteById(response.getId());
            System.out.println("Food deleted from DB with ID: " + response.getId());
        } else {
            throw new RuntimeException("Failed to delete image from Cloudinary.");
        }
    }

    private String extractPublicId(String imageUrl) {
        // Step 1: Extract the part after "/upload/"
        String afterUpload = imageUrl.substring(imageUrl.indexOf("/upload/") + 8);

        // Step 2: Remove the version prefix like "v123456/"
        String withoutVersion = afterUpload.replaceFirst("^v\\d+/", "");

        // Step 3: Remove the file extension (e.g., ".png", ".jpg")
        String publicId = withoutVersion.substring(0, withoutVersion.lastIndexOf('.'));

        return publicId;
    }



    private FoodEntity convertToEntity(FoodRequest request){
       return FoodEntity.builder()
               .name(request.getName())
               .description(request.getDescription())
               .category((request.getCategory()))
               .price(request.getPrice())
               .build();
    }

    private FoodResponse convertToResponse(FoodEntity entity){
        return  FoodResponse.builder()
                  .id(entity.getId())
                  .name(entity.getName())
                  .description(entity.getDescription())
                  .category(entity.getCategory())
                  .price(entity.getPrice())
                  .imgUrl(entity.getImgUrl())
                  .build();

    }
}
