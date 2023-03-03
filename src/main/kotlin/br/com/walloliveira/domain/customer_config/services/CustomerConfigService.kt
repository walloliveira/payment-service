package br.com.walloliveira.domain.customer_config.services

import br.com.walloliveira.domain.customer_config.CustomerConfig
import br.com.walloliveira.domain.vos.Code

interface CustomerConfigService {

    fun find(customerCode: Code): List<CustomerConfig>
    fun create(newCustomerConfig: CustomerConfig)
}
