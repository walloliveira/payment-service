package br.com.walloliveira.domain.customer_config.services

import br.com.walloliveira.domain.customer_config.CustomerConfig
import br.com.walloliveira.domain.customer_config.NewCustomerConfig
import br.com.walloliveira.domain.customer_config.exceptions.DuplicatedCustomerConfigException
import br.com.walloliveira.domain.customer_config.repositories.CustomerConfigRepository
import br.com.walloliveira.domain.customer_config.repositories.EncryptRepository
import br.com.walloliveira.domain.vos.Code

class DomainCustomerConfigService(
    private val repository: CustomerConfigRepository,
    private val encryptRepository: EncryptRepository,
) : CustomerConfigService {
    override fun find(customerCode: Code): List<CustomerConfig> = repository.findByCustomerCode(customerCode)

    override fun create(newCustomerConfig: NewCustomerConfig) {
        val customerConfigFound =
            repository.findByCustomerCodeAndApi(newCustomerConfig.customerCode, newCustomerConfig.api)
        customerConfigFound?.let { throw DuplicatedCustomerConfigException(it) }
        val customerConfig = CustomerConfig.toEncrypt(
            newCustomerConfig,
            encryptRepository.getKey(),
            encryptRepository.getEncryptor(),
        )
        repository.save(customerConfig)
    }
}
