package com.soap.moon.global.config;

import java.util.ArrayList;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

//http://localhost:8080/swagger-ui.html
@Configuration
@EnableSwagger2
public class SwaggerConfig {
    @Bean
    public Docket apiV1(){
        return new Docket(DocumentationType.SWAGGER_2)
            .apiInfo(this.apiInfo())
            .groupName("groupName1")
            .select()
            .apis(RequestHandlerSelectors.
                basePackage("com.soap.moon.domains"))
            .paths(PathSelectors.ant("/api/v1/**")).build();
    }

    @Bean
    public Docket apiV2(){
        return new Docket(DocumentationType.SWAGGER_2)
            .useDefaultResponseMessages(false)
            .groupName("members")
            .select()
            .apis(RequestHandlerSelectors.
                basePackage("com.soap.moon.domains"))
            .paths(PathSelectors.ant("/api/v1/members/**")).build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfo(
            "title",
            "description",
            "version",
            "https://woowacourse.github.io/javable/",
            new Contact("Contact Me", "https://woowacourse.github.io/javable/", "tigger@tigger.com"),
            "tigger Licenses",
            "https://woowacourse.github.io/javable/",
            new ArrayList<>()
        );
    }

}
