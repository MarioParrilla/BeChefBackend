package com.mp.bechefbackend.bechefbackend.Services;

import com.mp.bechefbackend.bechefbackend.Models.CategoryDTO;

import java.util.List;

public interface CategoryService {

    List<CategoryDTO> findAll();
}
