package br.com.walloliveira.resources.v1.customer.config

import br.com.walloliveira.domains.customer.config.CustomerConfig

class CustomerConfigData(customerConfig: CustomerConfig) {
    val code = customerConfig.code.valueString
    val customerCode = customerConfig.customerCode.valueString
    val token = customerConfig.token.value
    val clientId = customerConfig.clientId.value
    val api = customerConfig.api.description
}
