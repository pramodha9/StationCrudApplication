package com.station.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.ApiInfo;

import static springfox.documentation.builders.PathSelectors.regex;

@SpringBootApplication(scanBasePackages={"com.station.rest"})
@EnableSwagger2
public class StationCrudApplication  {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

	public static void main(String[] args) {
        SpringApplication.run(StationCrudApplication.class, args);
    }

    @Bean
    public Docket stationApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.station.rest.controller"))
                .paths(regex("/station.*"))
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Station Data Rest API")
                .description("Rest API for Station Data")
                .build();
    }
}