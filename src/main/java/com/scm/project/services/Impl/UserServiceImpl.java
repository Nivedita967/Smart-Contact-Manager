package com.scm.project.services.Impl;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.scm.project.entities.User;
import com.scm.project.repository.UserRepo;
import com.scm.project.services.UserService;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepo userRepo;

    @Override
    public User saveUser(User user) {
        String userId = UUID.randomUUID().toString();
        user.setUserId(userId);
        User savedUser = userRepo.save(user);
        return savedUser;

    }

    @Override
    public Optional<User> getUserById(String id) {
        return userRepo.findById(id);
    }

    @Override
    public Optional<User> updateUser(User user) {
        User user2 = userRepo.findById(user.getUserId()).orElse(null);
        User save= null;
        if(user2 != null){
            user2.setName(user.getName());
            user2.setEmail(user.getEmail());
            user2.setPassword(user.getPassword());
            user2.setAbout(user.getAbout());
            user2.setProfilePic(user.getProfilePic());
            user2.setEnabled(user.isEnabled());
            // save the user in database
            save = userRepo.save(user2);
        }
        return Optional.ofNullable(save);

    }

    @Override
    public void deleteUser(String id) {
        User user2 = userRepo.findById(id).orElse(null);
        if(user2 != null)
            userRepo.delete(user2);

    }

    @Override
    public boolean isUserExist(String userId) {
        User user2 = userRepo.findById(userId).orElse(null);
        return user2 != null ? true : false;
    }

    @Override
    public boolean isUserExistByEmail(String email) {
        User user = userRepo.findByEmail(email).orElse(null);
        return user != null ? true : false;
    }

    @Override
    public List<User> getAllUsers() {
        return userRepo.findAll();
    }

    @Override
    public User getUserByEmail(String email) {
        return userRepo.findByEmail(email).orElse(null);

    }

}