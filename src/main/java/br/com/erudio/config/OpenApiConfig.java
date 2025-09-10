package br.com.erudio.config;

import br.com.erudio.service.InstanceInformationService;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Classe de configuração do OPENAPI (Swagger).
 *
 * Retorna o json com a documentacao - http://localhost:8080/v3/api-docs
 * Documentação com a interface - http://localhost:8080/swagger-ui/index.html
 */
@Configuration
public class OpenApiConfig {

    @Autowired
    InstanceInformationService service;

    @Bean
    OpenAPI customOpenAPI(){
        return new OpenAPI()
                .info(new Info()
                        .title("REST API's RESTful do zero com Java, Spring Boot, Kubernetes and Docker V1 " +  service.retrieveInstanceInfo())
                        .version("v1")
                        .description("REST API's RESTful do zero com Java, Spring Boot, Kubernetes and Docker")
                        .termsOfService("https://pub.erudio.com.br/meus-cursos")
                        .license(new License()
                                .name("Apache 2.0")
                                .url("https://pub.erudio.com.br/meus-cursos")
                        )
                );
    }
}
