package br.com.walloliveira.domain.customer_config.repositories

import br.com.walloliveira.domain.customer_config.CustomerConfig
import br.com.walloliveira.domain.vos.Api
import br.com.walloliveira.domain.vos.Code

interface CustomerConfigRepository {
    fun save(customerConfig: CustomerConfig)
    fun list(): List<CustomerConfig>
    fun findByCustomerCode(customerCode: Code): List<CustomerConfig>
    fun findByCustomerCodeAndApi(customerCode: Code, api: Api): CustomerConfig?
}
