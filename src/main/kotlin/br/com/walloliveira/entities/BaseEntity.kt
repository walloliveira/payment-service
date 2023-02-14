package br.com.walloliveira.entities

import io.quarkus.hibernate.orm.panache.kotlin.PanacheEntityBase
import java.time.LocalDateTime
import javax.persistence.Column
import javax.persistence.MappedSuperclass
import javax.persistence.PrePersist
import javax.persistence.PreUpdate

@MappedSuperclass
abstract class BaseEntity: PanacheEntityBase {
    @Column(name = "dt_updated_at")
    lateinit var createdAt: LocalDateTime

    @Column(name = "dt_created_at")
    lateinit var updatedAt: LocalDateTime

    @PrePersist
    open fun onPrePersist() {
        createdAt = LocalDateTime.now()
        updatedAt = LocalDateTime.now()
    }

    @PreUpdate
    open fun onPreUpdate() {
        updatedAt = LocalDateTime.now()
    }
}
