package com.mp.bechefbackend.bechefbackend.Services.ServicesImpl;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.storage.*;
import com.mp.bechefbackend.bechefbackend.Models.RecipeDTO;
import com.mp.bechefbackend.bechefbackend.Repositories.RecipeRepository;
import com.mp.bechefbackend.bechefbackend.Repositories.UserRepository;
import com.mp.bechefbackend.bechefbackend.Services.RecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class RecipeServiceImpl implements RecipeService {

    @Autowired
    private RecipeRepository recipeRepository;
    @Autowired
    private UserRepository userRepository;

    private final String URLIMG = "https://firebasestorage.googleapis.com/v0/b/bechefapp-6b2c7.appspot.com/o/profilesImg%2F";
    private final String SUFIXURL = "?alt=media&token=4db09e73-1da8-4bd7-bd02-746e4c3a214e";

    @Override
    public List<RecipeDTO> findAll() {
        return recipeRepository.findAll();
    }

    @Override
    public List<RecipeDTO> findRecipesByToken(String token) {
        List<RecipeDTO> recipes = new ArrayList<>();

        Long userId = userRepository.findUserIdByToken(token);
        if(userId != null) recipes = recipeRepository.findRecipesByAutorId(userId);

        return recipes;
    }

    public boolean changeInfoRecipe(RecipeDTO recipe){
        boolean changeOK = false;

        recipeRepository.save(recipe);
        changeOK = true;

        return changeOK;
    }

    public String changeImgProfile(MultipartFile multipartFile){

        String urlImg = null;
        try{

            String objectName = generateFileName(multipartFile.getOriginalFilename());
            StorageOptions storageOptions = StorageOptions.newBuilder()
                    .setProjectId("bechefapp-6b2c7")
                    .setCredentials(GoogleCredentials
                            .fromStream(new ClassPathResource(".\\static\\firebase_admin.json").getInputStream()))
                    .build();
            Storage storage = storageOptions.getService();
            BlobId blobId = BlobId.of("bechefapp-6b2c7.appspot.com", "profilesImg/"+objectName);

            BlobInfo blobInfo = BlobInfo.newBuilder(blobId).setContentType("image/"+getExtension(multipartFile.getOriginalFilename())).build();

            Blob blob = storage.create(blobInfo, multipartFile.getBytes());

            System.err.println(objectName);
            urlImg = URLIMG+objectName+SUFIXURL;
        }catch (Exception e){
            e.printStackTrace();
        }
        return urlImg;
    }

    private String generateFileName(String originalFileName) {
        return UUID.randomUUID().toString() + "." + getExtension(originalFileName);
    }

    private String getExtension(String originalFileName) {
        return StringUtils.getFilenameExtension(originalFileName);
    }
}
