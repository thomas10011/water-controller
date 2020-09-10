package cn.tanhuiri.watercontroller;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@ComponentScan("com.zsd.watercontrolandroid")
public class WaterControllerApplication {

    public static void main(String[] args) {
        SpringApplication.run(WaterControllerApplication.class, args);
    }



}
