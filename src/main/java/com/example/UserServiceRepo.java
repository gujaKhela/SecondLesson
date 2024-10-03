package com.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceRepo {
    @Autowired
    private UserRepository userRepository;

    public void saveUser(UserBase user){
        userRepository.save(user);
    }

    public UserBase fetchUserById(int id) {
        return userRepository.findById(id).orElse(null); // Handle not found case as needed
    }

    public void deleteUserById(int id) {
        userRepository.deleteById(id);
    }

    public List<UserBase> fetchAllUsers() {
        return userRepository.findAll();
    }

    public void updateUser(int id, UserBase updatedUser) {
        UserBase user = userRepository.findById(id).orElse(null);
        if (user != null) {
            user.setName(updatedUser.getName());
            userRepository.save(user);
        }
    }
}
