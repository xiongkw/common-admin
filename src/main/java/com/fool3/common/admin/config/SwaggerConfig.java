package com.fool3.common.admin.config;

import com.google.common.collect.Lists;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.RequestParameterBuilder;
import springfox.documentation.oas.annotations.EnableOpenApi;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ParameterType;
import springfox.documentation.service.RequestParameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.List;

@Configuration
@EnableOpenApi
@ConditionalOnProperty("swagger.enabled")
public class SwaggerConfig {

    @Bean
    public Docket api() {
        RequestParameterBuilder aParameterBuilder = new RequestParameterBuilder();
        aParameterBuilder.name("token").in(ParameterType.HEADER).required(true).build();
        List<RequestParameter> params = Lists.newArrayList();
        params.add(aParameterBuilder.build());
        return new Docket(DocumentationType.OAS_30)
                .apiInfo(apiInfo())
                .globalRequestParameters(params)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.fool3.common.admin"))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("common-admin")
                .description("api")
                .version("1.0")
                .build();
    }
}