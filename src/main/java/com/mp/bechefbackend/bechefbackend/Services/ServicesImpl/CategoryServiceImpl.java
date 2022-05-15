package com.mp.bechefbackend.bechefbackend.Services.ServicesImpl;

import com.mp.bechefbackend.bechefbackend.Models.CategoryDTO;
import com.mp.bechefbackend.bechefbackend.Repositories.CategoryRepository;
import com.mp.bechefbackend.bechefbackend.Services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public List<CategoryDTO> findAll() {
        return categoryRepository.findAll();
    }
}
