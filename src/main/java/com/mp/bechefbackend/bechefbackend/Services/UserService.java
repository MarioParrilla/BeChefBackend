package com.mp.bechefbackend.bechefbackend.Services;

import com.mp.bechefbackend.bechefbackend.Models.UserDTO;

import java.util.List;

public interface UserService {

    public List<UserDTO> findAll();

    public UserDTO findUserById(Long userId);

    public UserDTO findUserByToken(String token);

    public boolean changeUsernameAndDescByToken(UserDTO user);

}
