package com.example.demo.config;


import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * SpringDoc API文档相关配置
 * Created by macro on 2022/3/4.
 */
@Configuration
public class SpringDocConfig {
    @Bean
    public OpenAPI mallTinyOpenAPI() {
        return new OpenAPI()
                .info(new Info().title("Kubernetes controller API doc")
                        .description("Kubernetes controller API 演示")
                        .version("v1.0.0")
                        .license(new License().name("Apache 2.0").url("https://github.com/EasyCode-Platform/EasyCode_Maintainer")))
                /*.externalDocs(new ExternalDocumentation()
                        .description("SpringBoot实战电商项目mall全套文档")
                        .url("http://www.macrozheng.com"))*/;
    }

//    @Bean
//    public GroupedOpenApi publicApi() {
//        return GroupedOpenApi.builder()
//                .group("brand")
//                .pathsToMatch("/brand/**")
//                .build();
//    }
//
//    @Bean
//    public GroupedOpenApi adminApi() {
//        return GroupedOpenApi.builder()
//                .group("admin")
//                .pathsToMatch("/admin/**")
//                .build();
//    }
}