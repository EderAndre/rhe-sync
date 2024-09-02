package br.rs.gov.defensoria.rhe.entity.jpa

import jakarta.persistence.*
import java.util.*

@Entity
@Table(name = "rhe_mestre_cidades")
class Cidade(
    @Id
    @Column(name="ID_CIDADE")
    var idCidade: Int? = null,

    @Column
    var NOME_CIDADE: String? = null

) {

    @Column(updatable = false)
    var CREATED: Date? = null

    @Column
    var LASTUPDATED: Date? = null

    @PrePersist
    fun onCreate() {
        CREATED = Date()
    }

    @PreUpdate
    fun onUpdate() {
        LASTUPDATED = Date()
    }
}