package vn.tayjava.controller;

import jakarta.validation.constraints.Min;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import vn.tayjava.dto.response.PagedResponse;
import vn.tayjava.dto.response.ResponseSuccess;
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
    public ResponseSuccess getAllUsers(@RequestParam(defaultValue = "0", required = false) int pageNo,
                                       @Min(1) @RequestParam(defaultValue = "20", required = false) int pageSize) {
        PagedResponse<User> pagedResponse = userService.getUsers(pageNo, pageSize);
        ResponseSuccess response = new ResponseSuccess(HttpStatus.OK, "ok", pagedResponse);
      //  return ResponseEntity.ok(response);
        return response;
    }

    @PostMapping
    public User createUser(@RequestBody User user) {
        return userService.createUser(user);
    }
}
