package cn.owem.wecare.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.oas.annotations.EnableOpenApi;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author Owem
 * @date 2022/11/28 20:22
 * @description TODO
 **/
@Configuration  // 告诉 Spring 容器, 这个类是一个配置类
@EnableOpenApi  // 启用 Swagger3 功能
public class SwaggerConfig {
    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                // cn 包下所有 API 都交给 Swagger2 管理
                .apis(RequestHandlerSelectors.basePackage("cn"))
                .paths(PathSelectors.any()).build();
    }

    // API 文档页面显示信息
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("WeCare Project API")
                .description("")
                .version("1.0")
                .build();
    }
}
