package br.com.walloliveira.services

import br.com.walloliveira.domains.customer.config.CustomerConfig
import br.com.walloliveira.domains.vos.Code
import br.com.walloliveira.repositories.CustomerConfigRepository
import javax.enterprise.context.ApplicationScoped
import javax.inject.Inject

@ApplicationScoped
class FindCustomerConfigByCustomerCodeService(@Inject private val repository: CustomerConfigRepository) {

    fun find(customerCode: Code): List<CustomerConfig> {
        return repository.findByCustomerCode(customerCode)
    }
}
