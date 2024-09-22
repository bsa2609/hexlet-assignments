package exercise;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDateTime;

import exercise.daytime.Daytime;
import exercise.daytime.Day;
import exercise.daytime.Night;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.web.context.annotation.RequestScope;
import org.springframework.web.context.annotation.SessionScope;

// BEGIN

// END

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    // BEGIN
    //@RequestScope
    @SessionScope
    @Bean
    public Daytime getDaytime() {
        LocalDateTime currentDateTime = LocalDateTime.now();
        if (currentDateTime.getHour() >= 6 && currentDateTime.getHour() < 22) {
            return new Day();
        } else {
            return new Night();
        }
    }
    // END
}
