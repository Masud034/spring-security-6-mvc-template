package com.example.securitymvctemplate.common.services;

import com.example.securitymvctemplate.common.entities.UserEntity;
import com.example.securitymvctemplate.common.model.SignupRequest;
import com.example.securitymvctemplate.common.repositories.UserEntityRepository;
import com.example.securitymvctemplate.role.Role;
import com.example.securitymvctemplate.role.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserEntityRepository userRepository;

    private final RoleRepository roleRepository;

    private final BCryptPasswordEncoder encoder;

    public UserEntity findUserByUsername(String username) {
        return userRepository.findByUserNameIgnoreCase(username.toLowerCase());
    }

    public UserEntity saveUser(SignupRequest request) {
        UserEntity user = new UserEntity();
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setUserName(request.getUsername());
        user.setPassword(encoder.encode(request.getPassword()));
        Role userRole = roleRepository.findByName("ROLE_USER");
        user.setRoles(Arrays.asList(userRole));
        return userRepository.save(user);
    }

    public List<Role> getRoles() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        return new ArrayList<>(userRepository.findByUserName(username).getRoles());
    }

//    public int changePassword(ChangePasswordRequestModel requestModel) {
//        if (!requestModel.getNewPassword().equals(requestModel.getRepeatPassword()))
//            return 0;
//        UserEntity userEntity = userRepository.findByUserName(SecurityContextHolder.getContext().getAuthentication().getName());
//        if (isValidUserPassword(userEntity, requestModel.getOldPassword())) {
//            userEntity.setPassword(passwordEncoder.encode(requestModel.getNewPassword()));
//            userRepository.save(userEntity);
//            return 1;
//        }
//        return 2;
//    }

    public boolean isValidUserPassword(UserEntity userEntity, String oldPassword) {
        boolean isMatched = encoder.matches(oldPassword, userEntity.getPassword());
        return isMatched;
    }

//    public void editUserInfo(String id, EditUserInfoModel user) {
//        UserEntity fetched = userRepository.findById(UUID.fromString(id)).get();
//        fetched.setFullName(user.getFullName());
//        fetched.setUserName(user.getUserName());
//        fetched.setEmail(user.getEmail());
//        fetched.setPhoneNumber(user.getPhoneNumber());
//        fetched.setStatus(user.isStatus());
//        fetched.setRoles(List.of(roleRepository.findById(UUID.fromString(user.getRoleId())).get()));
//        userRepository.save(fetched);
//    }

//    public int changeUserPasswordFromAdmin(String userId, ChangePasswordRequestModel requestModel) {
//        if (!requestModel.getNewPassword().equals(requestModel.getRepeatPassword()))
//            return 0;
//        UserEntity user = userRepository.findById(UUID.fromString(userId)).get();
//        user.setPassword(encoder.encode(requestModel.getNewPassword()));
//        userRepository.save(user);
//        return 1;
//    }

    public UserEntity getUser(String userId) {
        return userRepository.findById(UUID.fromString(userId)).get();
    }

    public List<UserEntity> getAllUsers() {
        return userRepository.findAll();
    }
}
