package com.example.demo.config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
@Configuration
@EnableSwagger2
public class SwaggerConfig {
    @Bean
    public Docket createRestApi(){
        return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo()).groupName("用户组")
                .select() .apis(RequestHandlerSelectors.withClassAnnotation(RestController.class))
                .paths(PathSelectors.ant("/**"))
                .build();
    }
    private ApiInfo apiInfo(){
        return new ApiInfoBuilder()
                .title("Api接口测试")
                .description("API描述")
                .version("1.0") .termsOfServiceUrl("https://www.baidu.com") .build(); }


}

