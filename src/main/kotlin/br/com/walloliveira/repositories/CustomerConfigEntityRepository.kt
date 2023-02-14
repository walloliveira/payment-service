package br.com.walloliveira.repositories

import br.com.walloliveira.domains.customer.config.CustomerConfig
import br.com.walloliveira.domains.vos.Code
import br.com.walloliveira.entities.customer.config.CustomerConfigEntity
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
}
