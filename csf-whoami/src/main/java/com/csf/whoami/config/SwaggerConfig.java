package com.csf.whoami.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.Parameter;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@ComponentScan
@EnableSwagger2
//@Profile("dev | local")
public class SwaggerConfig {

//    @Bean
//    public Docket swagger() {
//        return new Docket(DocumentationType.SWAGGER_2)
//                .ignoredParameterTypes(AuthenticationInfo.class)
//                .globalOperationParameters(singletonList(initParam("Authorization")))
//                .select()
//                .apis(RequestHandlerSelectors.any())
//                .paths(not(regex("/actuator.*")))
//                .paths(not(regex("/error")))
//                .build();
//    }

    private Parameter initParam(String name) {
        String type = "header";
        String modelType = "string";
        return (new ParameterBuilder())
                .name(name)                 // name of header
                .modelRef(new ModelRef(modelType))
                .parameterType(type)               // type - header
                .required(false)                // for compulsory
                .build();
    }
}

