package vn.tayjava;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class TayJavaApplication {

	public static void main(String[] args) {
		SpringApplication.run(TayJavaApplication.class, args);
	}
    // http://localhost:8000/swagger-ui/index.html
	@Bean
	public OpenAPI openAPI() {
		return new OpenAPI()
				.info(new Info()
						.title("GiangJava APIs")
						.description("Spring Boot API sample")
						.version("1.0"));
	}
}