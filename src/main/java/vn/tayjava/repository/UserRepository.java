package vn.tayjava.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import vn.tayjava.model.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    // Phương thức tìm user theo email và phone
    Optional<User> findByEmailAndPhone(String email, String phone);

    // Phương thức tìm user theo email (có thể cần cho các chức năng khác)
    Optional<User> findByEmail(String email);

    // Phương thức kiểm tra email đã tồn tại chưa (hữu ích cho đăng ký)
    boolean existsByEmail(String email);

    // Phương thức kiểm tra phone đã tồn tại chưa (hữu ích cho đăng ký)
    boolean existsByPhone(String phone);
}

