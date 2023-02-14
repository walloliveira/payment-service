package br.com.walloliveira.repositories

import br.com.walloliveira.domains.customer.config.CustomerConfig
import br.com.walloliveira.domains.vos.Api
import br.com.walloliveira.domains.vos.Code

interface CustomerConfigRepository {
    fun save(customerConfig: CustomerConfig)
    fun list(): List<CustomerConfig>
    fun findByCustomerCode(customerCode: Code): List<CustomerConfig>
    fun findByCustomerCodeAndApi(customerCode: Code, api: Api): CustomerConfig?
}
