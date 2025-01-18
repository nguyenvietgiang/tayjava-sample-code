package vn.tayjava.service;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import vn.tayjava.dto.response.PagedResponse;
import vn.tayjava.model.User;
import vn.tayjava.repository.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final EmailService emailService;

    public UserService(UserRepository userRepository, EmailService emailService) {
        this.userRepository = userRepository;
        this.emailService = emailService;
    }

    @Cacheable(value = "users", key = "#pageNo + '-' + #pageSize")
    public PagedResponse<User> getUsers(int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo, pageSize);
        Page<User> page = userRepository.findAll(pageable);

        // Đóng gói dữ liệu phân trang
        return new PagedResponse<>(
                page.getContent(),
                pageNo,
                pageSize,
                page.getTotalElements(),
                page.hasNext(),
                page.hasPrevious()
        );
    }

    // Thêm phương thức mới cho authentication
    public User findByEmailAndPhone(String email, String phone) {
        return userRepository.findByEmailAndPhone(email, phone)
                .orElseThrow(() -> new RuntimeException("User not found with email: " + email + " and phone: " + phone));
    }

    @CacheEvict(value = "users", allEntries = true) // Xóa cache khi có user mới được tạo
    public User createUser(User user) {
        // Lưu người dùng vào cơ sở dữ liệu
        User savedUser = userRepository.save(user);

        // Gửi email cảm ơn
        try {
            emailService.sendEmail(
                    savedUser.getEmail(),                        // Email người nhận
                    "Welcome to Our Platform",                  // Tiêu đề email
                    "EmailNotification.html",                   // Tên template
                    savedUser.getFirstName()                    // Tên người dùng để chèn vào email
            );
        } catch (Exception e) {
            // Log lỗi nếu gửi email thất bại
            System.err.println("Failed to send email: " + e.getMessage());
            // Bạn có thể xử lý thêm nếu cần (vd: ghi vào log, gửi thông báo,...)
        }

        return savedUser;
    }

    @CacheEvict(value = "users", allEntries = true) // Xóa cache khi có user mới được tạo
    public User updateUser(Long id, User updatedUser) {
        return userRepository.findById(id)
                .map(user -> {
                    user.setFirstName(updatedUser.getFirstName());
                    user.setLastName(updatedUser.getLastName());
                    user.setEmail(updatedUser.getEmail());
                    user.setPhone(updatedUser.getPhone());
                    return userRepository.save(user);
                })
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    @CacheEvict(value = "users", allEntries = true) // Xóa cache khi có user mới được tạo
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}

