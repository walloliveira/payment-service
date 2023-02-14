package br.com.walloliveira

import br.com.walloliveira.resources.v1.customer.config.NewCustomerConfigData
import io.quarkus.test.junit.QuarkusTest
import io.restassured.RestAssured
import org.hamcrest.Matchers.`is`
import org.junit.jupiter.api.Test
import java.util.*
import javax.ws.rs.core.MediaType

@QuarkusTest
class CustomerConfigResourceTest {

    @Test
    fun newCustomerConfigShouldBeCreated() {
        val payload = NewCustomerConfigData(
            "token-123",
            "client-123",
            "PAG_SEGURO",
        )
        val customerCode = UUID.randomUUID()
        RestAssured.given()
            .body(payload)
            .header("Content-Type", MediaType.APPLICATION_JSON)
            .`when`()
            .post("/v1/customer/{customerCode}/config", customerCode)
            .then()
            .statusCode(201)
        RestAssured.given()
            .header("Content-Type", MediaType.APPLICATION_JSON)
            .`when`()
            .get("/v1/customer/{customerCode}/config", customerCode)
            .then()
            .statusCode(200)
            .body("data.size()", `is`(1))
            .body("data[0].token", `is`("token-123"))
            .body("data[0].clientId", `is`("client-123"))
            .body("data[0].api", `is`("PAG_SEGURO"))
    }

    @Test
    fun newCustomerConfigShouldBeNotCreatedWhenPayloadIsInvalid() {
        val payload = NewCustomerConfigData(
            "",
            "",
            "",
        )
        val customerCode = UUID.randomUUID()
        RestAssured.given()
            .body(payload)
            .header("Content-Type", MediaType.APPLICATION_JSON)
            .`when`()
            .post("/v1/customer/{customerCode}/config", customerCode)
            .then()
            .statusCode(400)
    }

    @Test
    fun newCustomerConfigShouldBeNotCreatedWhenApiFieldOnPayloadIsInvalid() {
        val payload = NewCustomerConfigData(
            "token-123",
            "client-321",
            "OTHER",
        )
        val customerCode = UUID.randomUUID()
        RestAssured.given()
            .body(payload)
            .header("Content-Type", MediaType.APPLICATION_JSON)
            .`when`()
            .post("/v1/customer/{customerCode}/config", customerCode)
            .then()
            .statusCode(400)
    }

    @Test
    fun newCustomerConfigShouldBeNotCreatedWhenApiValueIsTheSame() {
        val payload = NewCustomerConfigData(
            "token-123",
            "client-321",
            "PAG_SEGURO",
        )
        val customerCode = UUID.randomUUID()
        RestAssured.given()
            .body(payload)
            .header("Content-Type", MediaType.APPLICATION_JSON)
            .`when`()
            .post("/v1/customer/{customerCode}/config", customerCode)
            .then()
            .statusCode(201)
        RestAssured.given()
            .body(payload)
            .header("Content-Type", MediaType.APPLICATION_JSON)
            .`when`()
            .post("/v1/customer/{customerCode}/config", customerCode)
            .then()
            .statusCode(409)
    }
}
