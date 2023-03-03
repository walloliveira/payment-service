package br.com.walloliveira.domain.customer_config

import br.com.walloliveira.domain.vos.Api
import br.com.walloliveira.domain.vos.ClientId
import br.com.walloliveira.domain.vos.Code
import br.com.walloliveira.domain.vos.Token

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
