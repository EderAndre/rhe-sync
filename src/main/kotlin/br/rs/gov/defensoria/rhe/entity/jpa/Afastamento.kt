package br.rs.gov.defensoria.rhe.entity.jpa

import jakarta.persistence.*
import java.time.LocalDate
import java.util.*

@Entity
@Table(name = "rhe_dados_afast")
class Afastamento(
    @Id
    @Column
    var CHAVE: Int? = null,

    @Column
    var NUMFUNC: Int? = null,

    @Column
    var NUMVINC: String? = null,

    @Column
    var DTINI: LocalDate? = null,

    @Column
    var DTFIM: LocalDate? = null,

    @Column
    var MOTIVO: String? = null,

    @Column
    var DTPREVFIM: LocalDate? = null
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