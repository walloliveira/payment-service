package br.com.walloliveira.application.v1.responses

import br.com.walloliveira.domain.customer_config.CustomerConfig

class CustomerConfigResponse(customerConfig: CustomerConfig) {
    val code = customerConfig.code.valueString
    val customerCode = customerConfig.customerCode.valueString
    val token = customerConfig.token.value
    val clientId = customerConfig.clientId.value
    val api = customerConfig.api.description
}
