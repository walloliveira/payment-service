package br.com.walloliveira.application.v1.requests

import br.com.walloliveira.domain.customer_config.CustomerConfigInput
import br.com.walloliveira.domain.vos.Api
import br.com.walloliveira.domain.vos.ClientId
import br.com.walloliveira.domain.vos.Code
import br.com.walloliveira.domain.vos.Token

data class NewCustomerConfigRequest(
    val token: String?,
    val clientId: String?,
    val api: String?,
    val codeCustomer: String?,
) {
    fun toInput(): CustomerConfigInput {
        return CustomerConfigInput(
            token = Token(this.token),
            clientId = ClientId(this.clientId),
            customerCode = Code(this.codeCustomer),
            api = Api.of(this.api),
        )
    }
}
