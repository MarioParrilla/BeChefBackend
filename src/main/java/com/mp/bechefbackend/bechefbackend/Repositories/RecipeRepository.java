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

    @Query(nativeQuery = true, value = "SELECT * FROM recipes r where r.category = ?1 and r.id > ?2 LIMIT 10")
    List<RecipeDTO> findRecipesByCategoryPaged(String category, Long lastID);

    @Query("SELECT r FROM RecipeDTO r where r.name like ?1%")
    List<RecipeDTO> findByQuery(String query);

    @Query(value = "SELECT * FROM recipes r, (select recipe_id, avg(rate) rate  from recipes_rates group by recipe_id) rt where r.id = rt.recipe_id order by rt.rate desc LIMIT 20", nativeQuery = true)
    List<RecipeDTO> findPopularRecipes();

    @Query(value = "SELECT * FROM recipes r, (select recipe_id, avg(rate) rate  from recipes_rates group by recipe_id) rt where r.id = rt.recipe_id order by rt.rate desc LIMIT 10", nativeQuery = true)
    List<RecipeDTO> findPopularRecipesPaged(Long lastID);

    @Query("SELECT r FROM RecipeDTO r where r.category = ?1")
    List<RecipeDTO> findRecipeByCategory(String category);
}
