package com.mp.bechefbackend.bechefbackend.Repositories;

import com.mp.bechefbackend.bechefbackend.Models.CategoryDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface CategoryRepository extends JpaRepository<CategoryDTO, Long> {

}
