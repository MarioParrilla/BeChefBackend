package com.mp.bechefbackend.bechefbackend.Services.ServicesImpl;

import com.mp.bechefbackend.bechefbackend.Models.UserDTO;
import com.mp.bechefbackend.bechefbackend.Repositories.UserRepository;
import com.mp.bechefbackend.bechefbackend.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<UserDTO> findAll() {
        return userRepository.findAll();
    }

    public UserDTO findUserById(Long userId) {
        return userRepository.findById(userId).orElse(null);
    }

    public UserDTO findUserByToken(String token) {
        return userRepository.findUserByToken(token);
    }

    public boolean changeUsernameAndDescByToken(UserDTO user){
        boolean changeOK = false;

        if (userRepository.existUserByTokenAndUsername(user.getToken(), user.getUsername())){
            UserDTO oldUser = userRepository.findUserByToken(user.getToken());
            if (oldUser == null) return false;
            oldUser.setDescription(user.getDescription());
            userRepository.save(oldUser);
            changeOK = true;
        }
        else if(userRepository.findUserDTOByUsername(user.getUsername()) == null){
            if (userRepository.existUserByToken(user.getToken())){
                UserDTO oldUser = userRepository.findUserByToken(user.getToken());
                if (oldUser == null) return false;
                oldUser.setUsername(user.getUsername());
                oldUser.setDescription(user.getDescription());
                userRepository.save(oldUser);
                changeOK = true;
            }
        }

        return changeOK;
    }

}
