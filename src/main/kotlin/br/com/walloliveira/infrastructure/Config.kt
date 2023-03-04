package br.com.walloliveira.infrastructure

import br.com.walloliveira.domain.customer_config.repositories.CustomerConfigRepository
import br.com.walloliveira.domain.customer_config.repositories.EncryptRepository
import br.com.walloliveira.domain.customer_config.services.CustomerConfigService
import br.com.walloliveira.domain.customer_config.services.DomainCustomerConfigService
import javax.enterprise.context.ApplicationScoped
import javax.enterprise.context.Dependent
import javax.ws.rs.Produces

@Dependent
class Config {

    @Produces
    @ApplicationScoped
    fun customerConfigService(
        repository: CustomerConfigRepository,
        encryptRepository: EncryptRepository,
    ): CustomerConfigService {
        return DomainCustomerConfigService(repository, encryptRepository)
    }
}
