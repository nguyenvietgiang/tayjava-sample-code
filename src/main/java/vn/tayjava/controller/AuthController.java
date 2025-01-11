package vn.tayjava.controller;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vn.tayjava.dto.request.LoginRequest;
import vn.tayjava.dto.response.ResponseSuccess;
import vn.tayjava.model.User;
import vn.tayjava.service.UserService;
import vn.tayjava.util.authen.JwtTokenProvider;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final UserService userService;
    private final JwtTokenProvider jwtTokenProvider;

    public AuthController(UserService userService, JwtTokenProvider jwtTokenProvider) {
        this.userService = userService;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @PostMapping("/login")
    public ResponseSuccess login(@RequestBody LoginRequest loginRequest) {
        // Tìm user theo email và phone
        User user = userService.findByEmailAndPhone(loginRequest.getEmail(), loginRequest.getPhone());

        if (user == null) {
            throw new RuntimeException("Invalid email or phone");
        }

        // Tạo UserDetails đơn giản
        UserDetails userDetails = org.springframework.security.core.userdetails.User
                .withUsername(user.getEmail())
                .password("") // Không sử dụng password
                .authorities("USER")
                .build();

        // Tạo tokens
        String accessToken = jwtTokenProvider.generateAccessToken(userDetails);
        String refreshToken = jwtTokenProvider.generateRefreshToken(userDetails);

        Map<String, Object> response = new HashMap<>();
        response.put("accessToken", accessToken);
        response.put("refreshToken", refreshToken);
        response.put("user", user);

        return new ResponseSuccess(HttpStatus.OK, "Login successful", response);
    }
}
