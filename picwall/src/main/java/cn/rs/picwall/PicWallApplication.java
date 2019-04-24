package cn.rs.picwall;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@PropertySource(value = {"classpath:application.properties",
        "classpath:application-${spring.profiles.active}.properties"})
public class PicWallApplication {

    public static void main(String[] args) {
        SpringApplication.run(PicWallApplication.class, args);
    }


}
