package vn.tayjava.controller;

import org.springframework.web.bind.annotation.*;
import vn.tayjava.model.User;
import vn.tayjava.service.UserService;

import java.util.List;
@RestController
@RequestMapping("/customer")
public class CustomerController {
    private final UserService userService;

    public CustomerController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @PostMapping
    public User createUser(@RequestBody User user) {
        return userService.createUser(user);
    }

    @PutMapping("/{id}")
    public User updateUser(@PathVariable Long id, @RequestBody User updatedUser) {
        return userService.updateUser(id, updatedUser);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
    }
}
