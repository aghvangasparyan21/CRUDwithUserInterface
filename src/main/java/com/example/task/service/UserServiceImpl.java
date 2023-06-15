package com.example.task.service;

import com.example.task.dao.UserRepository;
import com.example.task.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User findById(Long id) {
        Optional<User> user = userRepository.findById(id);
        User theUser = null;
        if (user.isPresent()) {
            theUser = user.get();
        } else {
            throw new RuntimeException("Did not found employee id - " + id);
        }
        return theUser;
    }

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    public User newPropertiesForExistingUser(User user, Long id, String firsName, String lastName, int age, String email) {
        user.setId(id);
        user.setFirstName(firsName);
        user.setLastName(lastName);
        user.setAge(age);
        user.setEmail(email);
        return user;
    }


    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}
