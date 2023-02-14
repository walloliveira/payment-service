package br.com.walloliveira.domains.customer.config

import br.com.walloliveira.domains.vos.Api
import br.com.walloliveira.domains.vos.ClientId
import br.com.walloliveira.domains.vos.Code
import br.com.walloliveira.domains.vos.Token

data class CustomerConfigInput(
    val token: Token,
    val clientId: ClientId,
    val customerCode: Code,
    val api: Api,
) {
    fun toDomain(): CustomerConfig = CustomerConfig(
        code = Code.new(),
        customerCode = customerCode,
        token = token,
        clientId = clientId,
        api = api,
    )
}
