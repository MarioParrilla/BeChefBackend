package com.mp.bechefbackend.bechefbackend.Services.ServicesImpl;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.storage.*;
import com.mp.bechefbackend.bechefbackend.Models.RateDTO;
import com.mp.bechefbackend.bechefbackend.Models.RateInfo;
import com.mp.bechefbackend.bechefbackend.Models.RecipeDTO;
import com.mp.bechefbackend.bechefbackend.Repositories.RateRepository;
import com.mp.bechefbackend.bechefbackend.Repositories.RecipeRepository;
import com.mp.bechefbackend.bechefbackend.Repositories.UserRepository;
import com.mp.bechefbackend.bechefbackend.Services.RecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class RecipeServiceImpl implements RecipeService {
    private final String URLIMG = "https://firebasestorage.googleapis.com/v0/b/bechefapp-6b2c7.appspot.com/o/profilesImg%2F";
    private final String SUFIXURL = "?alt=media&token=4db09e73-1da8-4bd7-bd02-746e4c3a214e";

    @Autowired
    private RecipeRepository recipeRepository;
    @Autowired
    private UserRepository userRepository;

    @Autowired
    RateRepository rateRepository;

    public long countRecipes(){
        return recipeRepository.count();
    }

    @Override
    public List<RecipeDTO> findAll() {
        return recipeRepository.findAll();
    }

    public RecipeDTO findById(Long id){
        return recipeRepository.findById(id).orElse(null);
    }

    public List<RecipeDTO> findByQuery(String query) {
        return recipeRepository.findByQuery(query);
    }

    public Double findRate(Long recipeId) {
        double recipeRate = 0.0;
        List<RateDTO> rates = rateRepository.findRate(recipeId);
        RecipeDTO recipe = recipeRepository.findById(recipeId).orElse(null);

        for ( RateDTO rate : rates){
            if (recipe != null && recipe.getId_autor() == rate.getUserId() && rate.getRate() == 0) continue;
            else recipeRate += rate.getRate();
        }
        DecimalFormat df = new DecimalFormat("###.##");
        return rates.size() > 1 ? recipeRate / rates.size() : recipeRate;
    }

    public Double findRateOfUser(Long recipeId, Long userId) {
        Double recipeRate = 0.0;
        recipeRate = rateRepository.findRateOfUser(recipeId, userId);

        DecimalFormat df = new DecimalFormat("###.##");
        return recipeRate != null ? recipeRate : 0.0;
    }

    public boolean setRate(RateInfo rate) {
        boolean changed = false;

        RateDTO r = new RateDTO();

        r.setRecipeId(rate.getRecipeId());
        r.setRate(rate.getRate());
        r.setUserId(userRepository.findUserIdByToken(rate.getUserToken()));

        rateRepository.save(r);
        changed = true;

        return changed;
    }

    public boolean remove(Long recipeID){
        boolean removeOK = false;

        try{
            recipeRepository.deleteById(recipeID);
            removeOK = true;
        }catch (Exception e){
            removeOK = false;
        }

        return removeOK;
    }

    public boolean deleteRecipesOfCategory(String category){
        boolean removeOK = false;

        try{
            ArrayList<RecipeDTO> list = (ArrayList<RecipeDTO>) recipeRepository.findRecipeByCategory(category);
            recipeRepository.deleteAll(list);
            removeOK = true;
        }catch (Exception e){
            removeOK = false;
        }

        return removeOK;
    }

    @Override
    public List<RecipeDTO> findRecipesByToken(String token) {
        List<RecipeDTO> recipes = new ArrayList<>();
        Long userId = userRepository.findUserIdByToken(token);
        if(userId != null) recipes = recipeRepository.findRecipesByAutorId(userId);

        return recipes;
    }

    public List<RecipeDTO> findRecipesByUserID(Long userID) {
        List<RecipeDTO> recipes = new ArrayList<>();

        recipes = recipeRepository.findRecipesByAutorId(userID);

        return recipes;
    }

    public List<RecipeDTO> findRecipesByCategory(String category) {
        List<RecipeDTO> recipes = new ArrayList<>();

        if (category.equalsIgnoreCase("popular")){
            recipes = recipeRepository.findPopularRecipes();
            recipes = recipes.stream().distinct().collect(Collectors.toList());
        }
        else recipes = recipeRepository.findRecipesByCategory(category);

        return recipes;
    }
    public List<RecipeDTO> findRecipesByCategoryPaged(String category, Long lastID) {
        List<RecipeDTO> recipes = new ArrayList<>();

        if (category.equalsIgnoreCase("popular")){
            recipes = recipeRepository.findPopularRecipesPaged(lastID);
            recipes = recipes.stream().distinct().collect(Collectors.toList());
        }
        else recipes = recipeRepository.findRecipesByCategoryPaged(category, lastID);

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
