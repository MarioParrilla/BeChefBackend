package com.mp.bechefbackend.bechefbackend.Repositories;

import com.mp.bechefbackend.bechefbackend.Models.UserDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<UserDTO, Long> {

    @Query("SELECT u FROM UserDTO u where u.email = ?1 and u.password = ?2")
    UserDTO findUserDTOByEmailAndByPassword(String email, String password);

    UserDTO findUserDTOByUsername(String username);

}
