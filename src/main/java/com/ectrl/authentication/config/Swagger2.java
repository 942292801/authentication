package com.ectrl.authentication.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 *@Author: Wen zhenwei
 *@date: 2020/4/7 13:38
 *@Description:
 *@Param:
 *@return:
 */
@Configuration
@EnableSwagger2
public class Swagger2 {

    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.ectrl.authentication.controller"))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("认证服务器")
                .description("Spring Boot中使用Swagger2构建RESTful APIs")
                //.termsOfServiceUrl("http://192.168.1.198:10070/platformgroup/ms-admin")
                .contact("Wen zhenwei")
                .version("1.0.0")
                .build();
    }

}

