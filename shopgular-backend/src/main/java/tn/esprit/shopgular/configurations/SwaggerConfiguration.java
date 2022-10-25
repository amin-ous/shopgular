package tn.esprit.shopgular.configurations;

import java.util.*;
import org.springframework.context.annotation.*;
import springfox.documentation.builders.*;
import springfox.documentation.service.*;
import springfox.documentation.spi.*;
import springfox.documentation.spi.service.contexts.*;
import springfox.documentation.spring.web.plugins.*;
import springfox.documentation.swagger2.annotations.*;

@Configuration
@EnableSwagger2
public class SwaggerConfiguration {

	public static final String AUTHORIZATION_HEADER = "Authorization";

	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiEndPointsInfo()).
		securityContexts(Collections.singletonList(securityContext())).
		securitySchemes(Arrays.asList(apiKey())).select().
		apis(RequestHandlerSelectors.basePackage("tn.esprit.shopgular.controllers")).
		paths(PathSelectors.any()).build();
	}

	private ApiInfo apiEndPointsInfo() {
		return new ApiInfoBuilder().title("shopgular").version("0.0.1-SNAPSHOT").build();
	}

	private ApiKey apiKey() {
		return new ApiKey("Bearer", AUTHORIZATION_HEADER, "header");
	}

	private SecurityContext securityContext() {
		return SecurityContext.builder().securityReferences(defaultAuth()).build();
	}

	private List<SecurityReference> defaultAuth() {
		AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything");
		AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
		authorizationScopes[0] = authorizationScope;
		return Arrays.asList(new SecurityReference("Bearer", authorizationScopes));
	}

}
