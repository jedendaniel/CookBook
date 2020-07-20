package cookbook;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
public class CookBookMain {
    public static void main(String[] args) {
        SpringApplication.run(CookBookMain.class, args);
    }
}
