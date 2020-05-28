package com.sample.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;


@Configuration
@EnableSwagger2
public class SwaggerConfig {
//    @Api：用于修饰Controller类，生成Controller相关文档信息
//    @ApiOperation：用于修饰Controller类中的方法，生成接口方法相关文档信息
//    @ApiParam：用于修饰接口中的参数，生成接口参数相关文档信息
//    @ApiModelProperty：用于修饰实体类的属性，当实体类是请求参数或返回结果时，直接生成相关文档信息

    public static <T> List<T> newArrayLis(T... items) {
        List<T> list = new ArrayList();
        for (T item : items) {
            list.add(item);
        }
        return list;
    }

    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                //为当前包下controller生成API文档
                .apis(RequestHandlerSelectors.basePackage("com.sample.controller"))
                //为有@Api注解的Controller生成API文档
//               .apis(RequestHandlerSelectors.withClassAnnotation(Api.class))
                //为有@ApiOperation注解的方法生成API文档
//               .apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))
                .paths(PathSelectors.any())
                .build();
//                .useDefaultResponseMessages(false)
//                .globalResponseMessage(
//                        RequestMethod.GET,
//                        newArrayLis(
//                                new ResponseMessageBuilder()
//                                        .code(500)
//                                        .message("服务器发生异常")
//                                        .responseModel(new ModelRef("Error"))
//                                        .build(),
//                                new ResponseMessageBuilder()
//                                        .code(403)
//                                        .message("资源不可用")
//                                        .build()
//                        )
//                );
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("swagger 使用演示API文档")
                .description("描述")
                .contact("联系方式")
                .version("1.0")
                .build();
    }
}
