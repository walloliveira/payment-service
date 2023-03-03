package br.com.walloliveira.application.v1.requests

import br.com.walloliveira.domain.customer_config.CustomerConfig
import br.com.walloliveira.domain.vos.Api
import br.com.walloliveira.domain.vos.Code
import br.com.walloliveira.domain.vos.StringValue

data class NewCustomerConfigRequest(
    val token: String?,
    val clientId: String?,
    val api: String?,
    val codeCustomer: String?,
) {

    fun toCustomerConfig(): CustomerConfig {
        return CustomerConfig(
            token = StringValue(this.token),
            clientId = StringValue(this.clientId),
            customerCode = Code.of(this.codeCustomer),
            api = Api.of(this.api),
            code = Code.new(),
        )
    }
}
