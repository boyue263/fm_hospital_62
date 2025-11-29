package com.hospital.wx.config;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import org.springframework.context.annotation.Configuration;
@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "/wx",
                description = "飞马市三医院_在线医疗问诊平台开发",
                version = "3.0"
        )
)

@SecurityScheme(
        name = "token",
        type = SecuritySchemeType.HTTP,
        bearerFormat = "JWT",
        scheme = "bearer"
)

public class SpringDocConfigWx {

}