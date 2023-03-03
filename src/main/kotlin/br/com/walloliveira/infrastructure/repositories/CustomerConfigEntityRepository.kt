package br.com.walloliveira.infrastructure.repositories

import br.com.walloliveira.domain.customer_config.CustomerConfig
import br.com.walloliveira.domain.customer_config.repositories.CustomerConfigRepository
import br.com.walloliveira.domain.vos.Api
import br.com.walloliveira.domain.vos.Code
import br.com.walloliveira.infrastructure.entities.customer_config.CustomerConfigEntity
import io.quarkus.hibernate.orm.panache.kotlin.PanacheRepository
import javax.enterprise.context.ApplicationScoped
import javax.transaction.Transactional

@ApplicationScoped
class CustomerConfigEntityRepository : CustomerConfigRepository, PanacheRepository<CustomerConfigEntity> {
    @Transactional
    override fun save(customerConfig: CustomerConfig) {
        persist(CustomerConfigEntity.of(customerConfig))
    }

    override fun list(): List<CustomerConfig> {
        return findAll().list().map { it.toDomain() }
    }

    override fun findByCustomerCode(customerCode: Code): List<CustomerConfig> {
        return find("customerCode", customerCode.valueString).list().map { it.toDomain() }
    }

    override fun findByCustomerCodeAndApi(customerCode: Code, api: Api): CustomerConfig? {
        return find(
            "customerCode = ?1 and api = ?2",
            customerCode.valueString,
            api.description,
        ).firstResult()?.toDomain()
    }
}
