package br.com.walloliveira.services

import br.com.walloliveira.domains.customer.config.CustomerConfigInput
import br.com.walloliveira.repositories.CustomerConfigRepository
import javax.enterprise.context.ApplicationScoped
import javax.inject.Inject

@ApplicationScoped
class CreateCustomerConfigService(@Inject private val repository: CustomerConfigRepository) {
    fun create(input: CustomerConfigInput) {
        val customerConfig = input.toDomain()
        repository.save(customerConfig)
    }
}
