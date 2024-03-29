package com.brevio.restapi.controllers;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;







import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableSwagger2
public class Swagger2Config {
                                    
	    @Bean
	    public Docket api() { 
	        return new Docket(DocumentationType.SWAGGER_2)  
	          .select()                                  
	          .apis(RequestHandlerSelectors.any())              
	          .paths(PathSelectors.any())                          
	          .build()
	          .apiInfo(apiInfo());
	    }
	    private ApiInfo apiInfo() {
	    	 
	        ApiInfo apiInfo = new ApiInfoBuilder()
	                .title ("API Cartão pre-pago")
	                .description ("API RESTful by Felipe Brevio")
	                .license("Apache License Version 2.0")
	                .licenseUrl("https://www.apache.org/licenses/LICENSE-2.0")
	                .termsOfServiceUrl("/service.html")
	                .version("1.0.0")
	                .contact(new Contact("Felipe Brevio","", "brevio05@gmail.com"))
	                .build();
	 
	        return apiInfo;
	}
	
}