package br.com.walloliveira.services

import br.com.walloliveira.domains.customer.config.CustomerConfigInput
import br.com.walloliveira.domains.exceptions.DuplicatedCustomerConfigurationByApiException
import br.com.walloliveira.repositories.CustomerConfigRepository
import javax.enterprise.context.ApplicationScoped
import javax.inject.Inject

@ApplicationScoped
class CreateCustomerConfigService(@Inject private val repository: CustomerConfigRepository) {
    fun create(input: CustomerConfigInput) {
        val newCustomerConfig = input.toDomain()
        val customerConfig = repository.findByCustomerCodeAndApi(newCustomerConfig.customerCode, newCustomerConfig.api)
        customerConfig?.let { throw DuplicatedCustomerConfigurationByApiException(newCustomerConfig) }
        repository.save(newCustomerConfig)
    }
}
