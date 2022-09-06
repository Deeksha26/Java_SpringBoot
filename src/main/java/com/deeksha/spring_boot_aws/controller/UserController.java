package com.deeksha.spring_boot_aws.controller;

import com.deeksha.spring_boot_aws.entity.User;
import com.deeksha.spring_boot_aws.exception.ResourceNotFoundException;
import com.deeksha.spring_boot_aws.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    //Get All Users
    @GetMapping
    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    //Get User by Id
    @GetMapping("/{id}")
    public User getUserById(@PathVariable(value = "id") Long id){
        return userRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("User Not Found with id : "+id));
    }

    //Create user
    @PostMapping
    public User createUser(@RequestBody User user){
        return userRepository.save(user);
    }
    //update user
    @PutMapping("/{id}")
    public User updateUser(@RequestBody User user, @PathVariable(value = "id") long userId){
        User existingUser = userRepository.findById(userId)
                .orElseThrow(()->new ResourceNotFoundException("User Not found with Id : "+userId));

        existingUser.setFirstName(user.getFirstName());
        existingUser.setLastName(user.getLastName());
        existingUser.setEmail(user.getEmail());

        return userRepository.save(existingUser);
    }

    //delete user by id
    @DeleteMapping("/{id}")
    public ResponseEntity<User> deleteUser(@PathVariable(value = "id") long userId){
        User existingUser = userRepository.findById(userId)
                .orElseThrow(()->new ResourceNotFoundException("User Not found with Id : "+userId));

        userRepository.delete(existingUser);
        return ResponseEntity.ok().build();
    }
}
