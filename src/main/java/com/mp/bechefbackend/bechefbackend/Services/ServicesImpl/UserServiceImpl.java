package com.mp.bechefbackend.bechefbackend.Services.ServicesImpl;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.storage.*;
import com.mp.bechefbackend.bechefbackend.Models.RecipeDTO;
import com.mp.bechefbackend.bechefbackend.Models.UserDTO;
import com.mp.bechefbackend.bechefbackend.Repositories.UserRepository;
import com.mp.bechefbackend.bechefbackend.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import java.util.List;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    private final String URLIMG = "https://firebasestorage.googleapis.com/v0/b/bechefapp-6b2c7.appspot.com/o/profilesImg%2F";
    private final String SUFIXURL = "?alt=media&token=4db09e73-1da8-4bd7-bd02-746e4c3a214e";

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<UserDTO> findAll() {
        return userRepository.findAll();
    }

    public List<UserDTO> findByQuery(String query) {
        return userRepository.findByQuery(query);
    }

    public UserDTO findUserById(Long userId) {
        return userRepository.findById(userId).orElse(null);
    }

    public UserDTO findUserByToken(String token) {
        return userRepository.findUserByToken(token);
    }

    public boolean save(UserDTO newUser){
        boolean result = false;

        try{
            userRepository.save(newUser);
            result = true;
        }catch (Exception e){
            result = false;
        }

        return result;
    }
    public boolean remove(Long userID){
        boolean removeOK = false;

        try{
            userRepository.deleteById(userID);
            removeOK = true;
        }catch (Exception e){
            removeOK = false;
        }

        return removeOK;
    }

    public long countUsers(){
        return userRepository.count();
    }

    //TODO: REFACTORIZAR PARA REUTILIZAR COODIGO
    public boolean changeUsernameAndDescByToken(UserDTO user){
        boolean changeOK = false;

        if(user.getUrlImg() != null){
            if (userRepository.existUserByTokenAndUsername(user.getToken(), user.getUsername())){
                UserDTO oldUser = userRepository.findUserByToken(user.getToken());
                if (oldUser == null) return false;
                oldUser.setDescription(user.getDescription());
                oldUser.setUrlImg(user.getUrlImg());
                userRepository.save(oldUser);
                changeOK = true;
            }
            else if(userRepository.findUserDTOByUsername(user.getUsername()) == null){
                if (userRepository.existUserByToken(user.getToken())){
                    UserDTO oldUser = userRepository.findUserByToken(user.getToken());
                    if (oldUser == null) return false;
                    oldUser.setUsername(user.getUsername());
                    oldUser.setDescription(user.getDescription());
                    oldUser.setUrlImg(user.getUrlImg());
                    userRepository.save(oldUser);
                    changeOK = true;
                }
            }
        }

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

    public String changeImgProfile(MultipartFile multipartFile){

        String urlImg = null;
        try{

            String objectName = generateFileName(multipartFile.getOriginalFilename());
            StorageOptions storageOptions = StorageOptions.newBuilder()
                    .setProjectId("bechefapp-6b2c7")
                    .setCredentials(GoogleCredentials
                            .fromStream(new ClassPathResource(".\\src\\main\\static\\firebase_admin.json").getInputStream()))
                    .build();
            Storage storage = storageOptions.getService();
            BlobId blobId = BlobId.of("bechefapp-6b2c7.appspot.com", "profilesImg/"+objectName);

            BlobInfo blobInfo = BlobInfo.newBuilder(blobId).setContentType("image/"+getExtension(multipartFile.getOriginalFilename())).build();

            Blob blob = storage.create(blobInfo, multipartFile.getBytes());

            System.err.println(objectName);
            urlImg = URLIMG+objectName+SUFIXURL;
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
