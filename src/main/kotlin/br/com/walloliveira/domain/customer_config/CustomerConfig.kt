package br.com.walloliveira.domain.customer_config

import br.com.walloliveira.domain.vos.Api
import br.com.walloliveira.domain.vos.ClientId
import br.com.walloliveira.domain.vos.Code
import br.com.walloliveira.domain.vos.Token

class CustomerConfig(
    val code: Code,
    val customerCode: Code,
    val token: Token,
    val clientId: ClientId,
    val api: Api,
)
