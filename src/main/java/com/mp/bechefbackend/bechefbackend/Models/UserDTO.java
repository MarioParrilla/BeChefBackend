package com.mp.bechefbackend.bechefbackend.Models;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "users")
public class UserDTO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 30, unique = true)
    private String username;

    @Column(nullable = false, length = 50, unique = true)
    private String email;

    @Column(nullable = false, length = 20)
    private String password;

    @Column(nullable = false)
    private Boolean isAdmin;

    public UserDTO() {
    }

    public UserDTO(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public UserDTO(String username, String email, String password, Boolean isAdmin) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.isAdmin = isAdmin;
    }

    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getAdmin() {
        return isAdmin;
    }

    public void setAdmin(Boolean admin) {
        isAdmin = admin;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserDTO userDTO = (UserDTO) o;
        return id.equals(userDTO.id) && username.equals(userDTO.username) && email.equals(userDTO.email) && password.equals(userDTO.password) && isAdmin.equals(userDTO.isAdmin);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, email, password, isAdmin);
    }

    @Override
    public String toString() {
        return "UserDTO{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", isAdmin=" + isAdmin +
                '}';
    }
}
