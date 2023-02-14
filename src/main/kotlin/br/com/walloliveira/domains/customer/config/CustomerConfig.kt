package br.com.walloliveira.domains.customer.config

import br.com.walloliveira.domains.vos.Api
import br.com.walloliveira.domains.vos.ClientId
import br.com.walloliveira.domains.vos.Code
import br.com.walloliveira.domains.vos.Token

class CustomerConfig(
    val code: Code,
    val customerCode: Code,
    val token: Token,
    val clientId: ClientId,
    val api: Api,
)
