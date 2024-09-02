package br.rs.gov.defensoria.rhe.entity.jpa

import jakarta.persistence.*
import java.time.LocalDate
import java.util.*

@Entity
@Table(name = "rhe_dados_evento")
class Evento(
    @Id
    @Column
    var NUMEV: Int? = null,

    @Column
    var NUMFUNC: Int? = null,

    @Column
    var NUMVINC: String? = null,

    @Column
    var CARGO: String? = null,

    @Column
    var DTINI: LocalDate? = null,

    @Column
    var DTFIM: LocalDate? = null,

    @Column
    var TIPOEVENTO: String? = null,

    @Column
    var REFERENCIA: String? = null,


    @Column
    var CARGO_FUNCAO: String? = null,

    @Column
    var SETOR: String? = null,

    @Column
    var ESPECIEEVENTO: String? = null,

    @Column
    var FRACAO: String? = null,

    @Column
    var REPR: String? = null,

    @Column
    var PROVESP: String? = null,

    @Column(columnDefinition = "TEXT")
    var OBS: String? = null
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