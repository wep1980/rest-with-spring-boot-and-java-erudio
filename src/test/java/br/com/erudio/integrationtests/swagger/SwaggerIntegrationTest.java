package br.com.erudio.integrationtests.swagger;

import br.com.erudio.integrationtests.container.AbstractIntegrationTest;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import br.com.erudio.config.TestConfigs;


import static io.restassured.RestAssured.given;
import static junit.framework.TestCase.assertTrue;

// DEFINED_PORT -> Pegando a porta definida no yaml de testes
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
class SwaggerIntegrationTest extends AbstractIntegrationTest {


	// Metodo que testa a integração com swagger
	@Test
	void shouldDisplaySwaggerUIPage() {
		var content = given()
				.basePath("/swagger-ui/index.html")
				.port(TestConfigs.SERVER_PORT)
				.when()
				.get()
				.then()
				.statusCode(200)
				.extract()
				.body()
				.asString();
		assertTrue(content.contains("Swagger UI"));
	}

}
