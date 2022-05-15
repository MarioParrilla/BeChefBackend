package com.mp.bechefbackend.bechefbackend.Services.ServicesImpl;

import com.mp.bechefbackend.bechefbackend.Models.UserDTO;
import com.mp.bechefbackend.bechefbackend.Repositories.UserRepository;
import com.mp.bechefbackend.bechefbackend.Services.UserSecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class UserSecurityServiceImpl implements UserSecurityService, UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        String role = "USER";
        UserDTO user = userRepository.findByUsername(username);
        Set<GrantedAuthority> authorities = new HashSet<>();

        if (user.getAdmin()) role = "ADMIN";
        else role = "USER";

        authorities.add(new SimpleGrantedAuthority(role));

        return new User(username, user.getPassword(), authorities);
    }

}
