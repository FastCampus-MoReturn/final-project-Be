package com.fastcampus.finalprojectbe.global.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.HttpAuthenticationScheme;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.Arrays;
import java.util.List;

@EnableWebMvc
@Configuration
public class SwaggerConfig extends WebMvcConfigurationSupport {

//    private static final String REFERENCE = "Bearer";

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.OAS_30) // 3.0 문서버전으로 세팅
                .groupName("파이널 프로젝트 1조")
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.fastcampus.finalprojectbe"))
//                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build()
                .useDefaultResponseMessages(false)
                .apiInfo(apiInfo());
//                .ignoredParameterTypes(AuthenticationPrincipal.class)
//                .securityContexts(List.of(securityContext()))
//                .securitySchemes(List.of(bearerAuthSecurityScheme()));

    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("HolyMoly")
                .description("{<br>" +
                        "\"code\": HttpCode,<br>" +
                        "  \"message\": \"기본: 요청에 성공하였습니다.\"<br>" +
                        "  \"data\": {   },<br>" +
                        "  \"isSuccess\": {success or false},<br>" +
                        "  }"+"<br>" +"모든 반환 값은 기본양식의 data에 담겨서 반환됩니다.")
//                .contact(new Contact("이름","홈페이지","email"))
//                .license("라이센스소유자")
//                .licenseUrl("라이센스URL")
                .version("1.0")
                .build();
    }

//    private HttpAuthenticationScheme bearerAuthSecurityScheme() {
//        return HttpAuthenticationScheme.JWT_BEARER_BUILDER
//                .name(REFERENCE).build();
//    }

    //JWT SecurityContext 구성
//    private SecurityContext securityContext() {
//        return SecurityContext.builder().securityReferences(defaultAuth()).build();
//    }
//
//    private List<SecurityReference> defaultAuth() {
//        AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEveryThing");
//        AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
//        authorizationScopes[0] = authorizationScope;
//        return Arrays.asList(new SecurityReference(REFERENCE, authorizationScopes));
//    }

    @Bean
    public InternalResourceViewResolver defaultViewResolver() {
        return new InternalResourceViewResolver();
    }
}