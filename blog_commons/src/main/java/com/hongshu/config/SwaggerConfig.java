package com.hongshu.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;

/**
 * projectName: my_blog
 *
 * @author: WangYiBing
 * time: 2023/5/8 14:19 周一
 * description:
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {
    @Value("${swagger.host}")
    private String swaggerHost;

    @Bean
    public Docket docket()
    {
        return new Docket(DocumentationType.SWAGGER_2)
                .protocols(Collections.singleton("http"))
                .host(swaggerHost)
                // 详细信息定制
                .apiInfo(apiInfo())
                .select()
                // 指定包路径，生成api文档的类
                .apis(RequestHandlerSelectors.basePackage("com.hongshu.controller"))
                // 扫描所有 .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("博客api文档")
                .description("springboot+vue开发的博客项目")
                .termsOfServiceUrl("http://120.77.241.193")
                .version("1.0")
                .build();
    }
}
