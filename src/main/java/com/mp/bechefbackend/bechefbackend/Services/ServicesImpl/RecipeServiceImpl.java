package com.mp.bechefbackend.bechefbackend.Services.ServicesImpl;

import com.mp.bechefbackend.bechefbackend.Models.RecipeDTO;
import com.mp.bechefbackend.bechefbackend.Repositories.RecipeRepository;
import com.mp.bechefbackend.bechefbackend.Repositories.UserRepository;
import com.mp.bechefbackend.bechefbackend.Services.RecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RecipeServiceImpl implements RecipeService {

    @Autowired
    private RecipeRepository recipeRepository;
    @Autowired
    private UserRepository userRepository;

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
}
