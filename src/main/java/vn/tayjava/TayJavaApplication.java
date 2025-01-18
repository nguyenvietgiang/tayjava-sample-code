package vn.tayjava;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
// cho phép chạy Background task
@EnableScheduling
public class TayJavaApplication {
	// http://localhost:8000/swagger-ui/index.html
	public static void main(String[] args) {
		SpringApplication.run(TayJavaApplication.class, args);
	}

}