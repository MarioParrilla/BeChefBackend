package com.mp.bechefbackend.bechefbackend.Repositories;

import com.mp.bechefbackend.bechefbackend.Models.UserDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface UserRepository extends JpaRepository<UserDTO, Long> {

    @Query("SELECT u FROM UserDTO u where u.email = ?1")
    UserDTO findUserDTOByEmail(String email);

    UserDTO findUserDTOByUsername(String username);

    @Query("SELECT u FROM UserDTO u where u.token = ?1")
    UserDTO findUserByToken(String token);

    @Query("SELECT u.id FROM UserDTO u where u.token = ?1")
    Long findUserIdByToken(String token);

    @Query("Select case when count(u)> 0 then true else false end from UserDTO u where u.token = ?1")
    boolean existUserByToken(String token);

    @Query("Select case when count(u)> 0 then true else false end from UserDTO u where u.token = ?1 and u.username = ?2")
    boolean existUserByTokenAndUsername(String token, String username);

    UserDTO findByUsername(String username);
}
