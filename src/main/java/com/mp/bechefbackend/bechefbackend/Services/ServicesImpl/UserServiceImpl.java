package com.mp.bechefbackend.bechefbackend.Services.ServicesImpl;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.storage.*;
import com.mp.bechefbackend.bechefbackend.Models.UserDTO;
import com.mp.bechefbackend.bechefbackend.Repositories.UserRepository;
import com.mp.bechefbackend.bechefbackend.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

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

    public String changeImgProfile(MultipartFile multipartFile) throws IOException {

        String urlImg = null;
        try{

            String objectName = generateFileName(multipartFile.getOriginalFilename());
            StorageOptions storageOptions = StorageOptions.newBuilder()
                    .setProjectId("bechefapp-6b2c7")
                    .setCredentials(GoogleCredentials
                            .fromStream(new ClassPathResource(".\\static\\firebase_admin.json").getInputStream()))
                    .build();
            Storage storage = storageOptions.getService();
            BlobId blobId = BlobId.of("bechefapp-6b2c7.appspot.com", "profilesImg/"+objectName);

            BlobInfo blobInfo = BlobInfo.newBuilder(blobId).setContentType(multipartFile.getContentType()).build();

            Blob blob = storage.create(blobInfo, multipartFile.getBytes());

            System.err.println("UPLOAD FILE" + multipartFile.getName() + " " + multipartFile.getSize() + " octet");
        }catch (Exception e){
            e.printStackTrace();
        }
        return urlImg;
    }

    private String generateFileName(String originalFileName) {
        return UUID.randomUUID().toString() + "." + getExtension(originalFileName);
    }

    private String getExtension(String originalFileName) {
        return StringUtils.getFilenameExtension(originalFileName);
    }
}
