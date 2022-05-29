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

    public long countCategories(){
        return categoryRepository.count();
    }

    public void save(String newCategory) throws Exception {
        CategoryDTO c = new CategoryDTO();
        c.setName(newCategory);

        if (categoryRepository.findByName(c.getName()) == null){
            categoryRepository.save(c);
        }else throw new Exception();
    }

    public void edit(String newCategory, String oldCategory) throws Exception {
        CategoryDTO c = new CategoryDTO();
        c.setName(newCategory);

        CategoryDTO c2 = new CategoryDTO();
        c2.setName(oldCategory);

        if (categoryRepository.findByName(c2.getName()) != null){
            delete(c2.getName());
            System.out.println(c);
            categoryRepository.save(c);
        }else throw new Exception();
    }

    public void delete(String categoryToDelete) throws Exception {
        CategoryDTO c = new CategoryDTO();
        c.setName(categoryToDelete);
        if (categoryRepository.findByName(c.getName()) != null){
            categoryRepository.delete(c);
        }else throw new Exception();
    }

}
