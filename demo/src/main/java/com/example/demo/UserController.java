package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.demo.PollManager;

@RestController
@RequestMapping("/users")
public class UserController {

    private final PollManager pollManager;

    @Autowired
    public UserController(PollManager pollManager) {
        this.pollManager = pollManager;
    }

    @PostMapping("/create")
    public String createUser(@RequestBody User user) {
        if (pollManager.getUser(user.getUsername()) != null) {
            return "User already exists!";
        }
        pollManager.addUser(user.getUsername(), user);
        return "User created successfully!";
    }

    @GetMapping("/{username}")
    public User getUser(@PathVariable String username) {
        User user = pollManager.getUser(username);
        if (user == null) {
            //throw new UserNotFoundException("User not found");
        }
        return user;
    }

    @DeleteMapping("/{username}")
    public String deleteUser(@PathVariable String username) {
        User user = pollManager.getUser(username);
        if (user == null) {
            //throw new UserNotFoundException("User not found");
        }
        pollManager.removeUser(username);
        return "User deleted successfully!";
    }
    
}
