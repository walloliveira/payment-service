package br.com.walloliveira.domain.customer_config

import br.com.walloliveira.domain.vos.Api
import br.com.walloliveira.domain.vos.Code
import br.com.walloliveira.domain.vos.StringValue

class CustomerConfig(
    val code: Code,
    val customerCode: Code,
    val token: StringValue,
    val clientId: StringValue,
    val api: Api,
)
