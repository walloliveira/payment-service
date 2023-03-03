package br.com.walloliveira.infrastructure.entities.payment

import javax.persistence.Column
import javax.persistence.DiscriminatorValue
import javax.persistence.Entity
import javax.persistence.Table

@Entity
@Table(name = "credit_card_payment")
@DiscriminatorValue("CREDIT_CARD")
class CreditCardPaymentEntity : PaymentEntity() {

    @Column(name = "int_installments")
    var installments: Int = 0
}
