package ai.wbw.service.common.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.media.StringSchema;
import io.swagger.v3.oas.models.parameters.HeaderParameter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/** 集成Swagger，可以在线测试以及查看接口文档 */
/*
@Configuration //标记配置类
@EnableSwagger2 //开启在线接口文档
public class Swagger2Config {
    */

/** 添加摘要信息(Docket) */
/*
    @Bean
    public Docket controllerApi() {

        //添加head参数
        ParameterBuilder tokenPar = new ParameterBuilder();
        List<Parameter> pars = new ArrayList<Parameter>();
        tokenPar.name("token").description("accessToken").modelRef(new ModelRef("string")).parameterType("header")
                .required(false).build();
        pars.add(tokenPar.build());

        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(new ApiInfoBuilder()
                        .title("wbwai-service_接口文档")
//                        .description("用于的接口")
                        .contact(new Contact("", null, null))
                        .version("版本号:1.0")
                        .build())
                .select()
                .apis(RequestHandlerSelectors.basePackage("ai.wbw.service.controller"))
                .paths(PathSelectors.any())
                .build()
                .globalOperationParameters(pars);
    }


}*/

@Configuration
public class Swagger2Config implements WebMvcConfigurer {

  @Bean
  public OpenAPI springShopOpenAPI() {
    return new OpenAPI()
        .components(
            new Components()
                .addParameters(
                    "token",
                    new HeaderParameter().description("accessToken").schema(new StringSchema())))
        .info(
            new Info()
                .title("wbwapi")
                .description("wbwapi在线接口文档")
                .version("v2.0")
                .license(new License().name("Apache 2.0").url("http://springdoc.org")))
        .externalDocs(new ExternalDocumentation().description("wbwai").url("#"));
  }
}
