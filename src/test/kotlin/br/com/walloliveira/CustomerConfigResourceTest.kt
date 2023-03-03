package br.com.walloliveira

import br.com.walloliveira.application.v1.requests.NewCustomerConfigRequest
import io.quarkus.test.junit.QuarkusTest
import io.restassured.RestAssured
import org.hamcrest.Matchers.`is`
import org.junit.jupiter.api.Test
import java.util.*
import javax.ws.rs.core.MediaType

@QuarkusTest
class CustomerConfigResourceTest {

    companion object {
        private const val V1_CUSTOMER_CONFIG_PATH = "/v1/customer-config"
    }

    @Test
    fun newCustomerConfigShouldBeCreated() {
        val codeCustomer = UUID.randomUUID().toString()
        val payload = NewCustomerConfigRequest(
            "token-123",
            "client-123",
            "PAG_SEGURO",
            codeCustomer,
        )
        RestAssured.given()
            .body(payload)
            .header("Content-Type", MediaType.APPLICATION_JSON)
            .`when`()
            .post(V1_CUSTOMER_CONFIG_PATH)
            .then()
            .statusCode(201)
        RestAssured.given()
            .header("Content-Type", MediaType.APPLICATION_JSON)
            .queryParam("customerCode", codeCustomer)
            .`when`()
            .get(V1_CUSTOMER_CONFIG_PATH)
            .then()
            .statusCode(200)
            .body("data.size()", `is`(1))
            .body("data[0].token", `is`("token-123"))
            .body("data[0].clientId", `is`("client-123"))
            .body("data[0].api", `is`("PAG_SEGURO"))
    }

    @Test
    fun newCustomerConfigShouldBeNotCreatedWhenPayloadIsInvalid() {
        val payload = NewCustomerConfigRequest(
            "",
            "",
            "",
            "",
        )
        RestAssured.given()
            .body(payload)
            .header("Content-Type", MediaType.APPLICATION_JSON)
            .`when`()
            .post(V1_CUSTOMER_CONFIG_PATH)
            .then()
            .statusCode(400)
    }

    @Test
    fun newCustomerConfigShouldBeNotCreatedWhenApiFieldOnPayloadIsInvalid() {
        val customerCode = UUID.randomUUID().toString()
        val payload = NewCustomerConfigRequest(
            "token-123",
            "client-321",
            "OTHER",
            customerCode,
        )
        RestAssured.given()
            .body(payload)
            .header("Content-Type", MediaType.APPLICATION_JSON)
            .`when`()
            .post(V1_CUSTOMER_CONFIG_PATH)
            .then()
            .statusCode(400)
    }

    @Test
    fun newCustomerConfigShouldBeNotCreatedWhenApiValueIsTheSame() {
        val customerCode = UUID.randomUUID().toString()
        val payload = NewCustomerConfigRequest(
            "token-123",
            "client-321",
            "PAG_SEGURO",
            customerCode,
        )
        RestAssured.given()
            .body(payload)
            .header("Content-Type", MediaType.APPLICATION_JSON)
            .`when`()
            .post(V1_CUSTOMER_CONFIG_PATH)
            .then()
            .statusCode(201)
        RestAssured.given()
            .body(payload)
            .header("Content-Type", MediaType.APPLICATION_JSON)
            .`when`()
            .post(V1_CUSTOMER_CONFIG_PATH)
            .then()
            .statusCode(409)
    }
}
