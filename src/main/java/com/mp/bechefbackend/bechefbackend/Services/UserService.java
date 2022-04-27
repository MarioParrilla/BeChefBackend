package com.mp.bechefbackend.bechefbackend.Services;

import com.mp.bechefbackend.bechefbackend.Models.UserDTO;

import java.util.List;

public interface UserService {

    public List<UserDTO> findAll();

    public UserDTO findUserById(Long userId);

}
