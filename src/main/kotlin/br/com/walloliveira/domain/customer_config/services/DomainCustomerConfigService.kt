package br.com.walloliveira.domain.customer_config.services

import br.com.walloliveira.domain.customer_config.CustomerConfig
import br.com.walloliveira.domain.customer_config.exceptions.DuplicatedCustomerConfigException
import br.com.walloliveira.domain.customer_config.repositories.CustomerConfigRepository
import br.com.walloliveira.domain.vos.Code

class DomainCustomerConfigService(private val repository: CustomerConfigRepository) : CustomerConfigService {
    override fun find(customerCode: Code): List<CustomerConfig> = repository.findByCustomerCode(customerCode)

    override fun create(newCustomerConfig: CustomerConfig) {
        val customerConfig = repository.findByCustomerCodeAndApi(newCustomerConfig.customerCode, newCustomerConfig.api)
        customerConfig?.let { throw DuplicatedCustomerConfigException(it) }
        repository.save(newCustomerConfig)
    }
}
