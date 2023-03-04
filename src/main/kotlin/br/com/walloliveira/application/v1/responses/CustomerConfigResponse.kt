package br.com.walloliveira.application.v1.responses

import br.com.walloliveira.domain.customer_config.CustomerConfig

class CustomerConfigResponse(customerConfig: CustomerConfig) {
    val code = customerConfig.code.valueString
    val customerCode = customerConfig.customerCode.valueString
    val token = customerConfig.token.valueString
    val clientId = customerConfig.clientId.valueString
    val api = customerConfig.api.description
}
