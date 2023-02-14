package br.com.walloliveira.resources.v1.customer.config

import br.com.walloliveira.domains.customer.config.CustomerConfigInput
import br.com.walloliveira.domains.vos.Api
import br.com.walloliveira.domains.vos.ClientId
import br.com.walloliveira.domains.vos.Code
import br.com.walloliveira.domains.vos.Token
import br.com.walloliveira.services.CreateCustomerConfigService
import br.com.walloliveira.services.FindCustomerConfigByCustomerCodeService
import javax.inject.Inject
import javax.ws.rs.GET
import javax.ws.rs.POST
import javax.ws.rs.Path
import javax.ws.rs.PathParam
import javax.ws.rs.Produces
import javax.ws.rs.core.MediaType
import javax.ws.rs.core.Response

@Path("/v1/customer/{customerCode}/config")
@Produces(MediaType.APPLICATION_JSON)
class CustomerConfigResource @Inject constructor(
    val createService: CreateCustomerConfigService,
    val findService: FindCustomerConfigByCustomerCodeService,
) {

    @POST
    fun create(@PathParam("customerCode") customerCode: String, data: NewCustomerConfigData): Response {
        val input = CustomerConfigInput(
            token = Token(data.token),
            clientId = ClientId(data.clientId),
            customerCode = Code(customerCode),
            api = Api.of(data.api),
        )
        this.createService.create(input)
        return Response.status(Response.Status.CREATED).build();
    }

    @GET
    fun list(@PathParam("customerCode") customerCode: String): Response {
        val list = findService.find(Code(customerCode)).map { CustomerConfigData(it) }
        return Response.ok(ResourceListData(list)).build()
    }
}
