package com.soap.moon.global.config;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.Contact;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

//http://localhost:80/swagger-ui.html
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
            .paths(PathSelectors.ant("/api/v1/**"))
            .build()
            .securityContexts(Arrays.asList(securityContext()))
            .securitySchemes(Arrays.asList(apiKey()));
    }

    private ApiKey apiKey() {
        return new ApiKey("JWT", "Authorization", "header");
    }

    private SecurityContext securityContext() {
        return springfox
            .documentation
            .spi.service
            .contexts
            .SecurityContext
            .builder()
            .securityReferences(defaultAuth()).forPaths(PathSelectors.any()).build();
    }

    List<SecurityReference> defaultAuth() {
        AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything");
        AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
        authorizationScopes[0] = authorizationScope;
        return Arrays.asList(new SecurityReference("JWT", authorizationScopes));
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
