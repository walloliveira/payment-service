package br.com.walloliveira.infrastructure.entities.payment

import br.com.walloliveira.infrastructure.entities.BaseEntity
import java.math.BigDecimal
import javax.persistence.Column
import javax.persistence.DiscriminatorColumn
import javax.persistence.DiscriminatorType
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.Inheritance
import javax.persistence.InheritanceType
import javax.persistence.SequenceGenerator
import javax.persistence.Table


@Entity
@Table(name = "payment")
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "enum_type", discriminatorType = DiscriminatorType.STRING)
abstract class PaymentEntity : BaseEntity() {
    @SequenceGenerator(name = "PaymentSeq", sequenceName = "payment_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PaymentSeq")
    @Id
    @Column(name = "int_id")
    open var id: Long? = null

    @Column(name = "enum_type")
    open lateinit var type: String

    @Column(name = "code_id")
    open lateinit var code: String

    @Column(name = "str_description")
    open var description: String? = null

    @Column(name = "num_value")
    open lateinit var value: BigDecimal

    @Column(name = "code_customer_config")
    open lateinit var codeCustomerConfig: String

    @Column(name = "enum_status")
    open lateinit var status: String

    @Column(name = "str_currency")
    open lateinit var currency: String

}
