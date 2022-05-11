package com.mp.bechefbackend.bechefbackend.Repositories;

import com.mp.bechefbackend.bechefbackend.Models.RecipeDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface RecipeRepository extends JpaRepository<RecipeDTO, Long> {

    @Query("SELECT r FROM RecipeDTO r where r.id_autor = ?1")
    List<RecipeDTO> findRecipesByAutorId(Long id);

    @Query("SELECT r FROM RecipeDTO r where r.category = ?1")
    List<RecipeDTO> findRecipesByCategory(String category);

}
