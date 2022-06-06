package com.mp.bechefbackend.bechefbackend.Repositories;

import com.mp.bechefbackend.bechefbackend.Models.RateDTO;
import com.mp.bechefbackend.bechefbackend.Models.RateId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface RateRepository extends JpaRepository<RateDTO, RateId> {

    @Query("select r from RateDTO r where r.recipeId = ?1")
    List<RateDTO> findRate(Long recipeId);

    @Query("select r.rate from RateDTO r where r.recipeId = ?1 and r.userId = ?2")
    Double findRateOfUser(Long recipeId, Long userId);
}
