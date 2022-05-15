package com.mp.bechefbackend.bechefbackend.Services.ServicesImpl;

import com.mp.bechefbackend.bechefbackend.Models.UserDTO;
import com.mp.bechefbackend.bechefbackend.Repositories.UserRepository;
import com.mp.bechefbackend.bechefbackend.Services.AuthService;
import com.mp.bechefbackend.bechefbackend.Utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDTO checkData(UserDTO userToCheck) {
        UserDTO user = userRepository.findUserDTOByEmail(userToCheck.getEmail());
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder(4);
        if (bCryptPasswordEncoder.matches(user.getPassword(), bCryptPasswordEncoder.encode(user.getPassword()))) return user;
        else return null;
    }

    @Override
    public UserDTO register(UserDTO newUser) {
        if ( newUser.getPassword() == null || newUser.getPassword().isEmpty()) return null;
        String cPass = new BCryptPasswordEncoder(4).encode(newUser.getPassword());
        UserDTO user = userRepository.findUserDTOByEmail(newUser.getEmail());

        if (user == null){
            newUser.setUsername(createUsername(newUser.getEmail()));
            newUser.setToken(createToken(newUser.getUsername(), cPass, "bechef"));
            newUser.setDescription("No description");
            newUser.setAdmin(false);
            userRepository.save(newUser);
            return userRepository.findUserDTOByEmail(newUser.getEmail());
        }
        else return null;
    }

    private String createUsername(String email){
        String emailWithoutDomain = email.substring(0, email.indexOf('@'));

        while( userRepository.findUserDTOByUsername(emailWithoutDomain) != null ) emailWithoutDomain += Utils.takeRandomChar();

        return emailWithoutDomain;
    }

    private String createToken(String username, String password, String serverToken){
        return username+password+serverToken;
    }
}
