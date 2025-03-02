package com.example.collegedirectory.service;

import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.collegedirectory.User;
import com.example.collegedirectory.repository.UserRepository;

@Service
public class UserService{
    @Autowired
    private UserRepository userRepository;

    // Method to fetch all users
    public List<User> getAllUsers(){
        return userRepository.findAll();
    }
    // Method to fetch user by ID
    public Optional<User> getUserById(Long id){
        return userRepository.findById(id);
    }
    // Method to save a new user
    public User saveUser(User user){
        return userRepository.save(user);
    }
    //Method to update an existring user
    public User updateUser(Long id,User updatedUser){   
        Optional<User> existingUser = userRepository.findById(id);
        if(existingUser.isPresent()){
            User user = existingUser.get();
            user.setUsername(updatedUser.getUsername());
            user.setEmail(updatedUser.getEmail());
            user.setRole(updatedUser.getRole());
            return userRepository.save(user);
        }
        return null; // or throw an exception
    }
    public void deleteUser(Long id){
        userRepository.deleteById(id);
    }
}