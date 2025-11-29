package com.hospital.wx;
import com.hospital.wx.controller.PatientUserController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;

import java.util.Arrays;

@SpringBootApplication
@ComponentScan("com.hospital.wx.*")
@ComponentScan("com.hospital.common.*")
public class WxApplication {
    public static void main(String[] args) {
        ConfigurableApplicationContext ctx = SpringApplication.run(WxApplication.class, args);
        String[] beanNames = ctx.getBeanNamesForType(PatientUserController.class);
        System.out.println("注册的Controller: " + Arrays.toString(beanNames));
    }
}
