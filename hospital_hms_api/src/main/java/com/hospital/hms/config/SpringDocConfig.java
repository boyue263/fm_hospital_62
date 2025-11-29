package com.hospital.hms.config;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import org.springframework.context.annotation.Configuration;
@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "/fm_hospital_hms",
                description = "飞马市三医院_在线医疗问诊平台开发",
                version = "4.0"
        )
)

@SecurityScheme(
        name = "token",
        type = SecuritySchemeType.HTTP,
        bearerFormat = "JWT",
        scheme = "bearer"
)

public class SpringDocConfig {

}