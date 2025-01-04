package vn.tayjava.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vn.tayjava.dto.request.UserRequestDTO;

@RestController
@RequestMapping("/user")
@Tag(name = "Account", description = "Account management APIs")
public class UserController {
   @Operation(method = "POST", summary = "Add new user", description = "Send a request via this API to create new user")
    @PostMapping("/")
    public String addUser(@RequestBody UserRequestDTO user) {
        return "User added";
    }
}
