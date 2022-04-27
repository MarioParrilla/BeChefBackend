package com.mp.bechefbackend.bechefbackend.Services;

import com.mp.bechefbackend.bechefbackend.Models.UserDTO;

public interface AuthService {

    public UserDTO checkData(UserDTO userToCheck);

    public UserDTO register(UserDTO newUser);

}
