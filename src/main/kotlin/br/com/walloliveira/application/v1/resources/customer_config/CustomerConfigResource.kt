package br.com.walloliveira.application.v1.resources.customer_config

import br.com.walloliveira.application.v1.requests.NewCustomerConfigRequest
import br.com.walloliveira.application.v1.responses.CustomerConfigResponse
import br.com.walloliveira.application.v1.responses.ResourceListResponse
import br.com.walloliveira.domain.customer_config.services.CustomerConfigService
import br.com.walloliveira.domain.vos.Code
import javax.inject.Inject
import javax.ws.rs.GET
import javax.ws.rs.POST
import javax.ws.rs.Path
import javax.ws.rs.Produces
import javax.ws.rs.QueryParam
import javax.ws.rs.core.MediaType
import javax.ws.rs.core.Response

@Path("/v1/customer-config")
@Produces(MediaType.APPLICATION_JSON)
class CustomerConfigResource @Inject constructor(
    val customerConfigService: CustomerConfigService,
) {

    @POST
    fun create(data: NewCustomerConfigRequest): Response {
        this.customerConfigService.create(data.toInput())
        return Response.status(Response.Status.CREATED).build();
    }

    @GET
    fun list(@QueryParam("customerCode") customerCode: String): Response {
        val list = customerConfigService.find(Code(customerCode)).map { CustomerConfigResponse(it) }
        return Response.ok(ResourceListResponse(list)).build()
    }
}
