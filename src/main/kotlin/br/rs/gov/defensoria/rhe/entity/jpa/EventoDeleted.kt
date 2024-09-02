package br.rs.gov.defensoria.rhe.entity.jpa

import jakarta.persistence.*
import java.time.LocalDate
import java.util.*

@Entity
@Table(name = "rhe_dados_evento_deleted")
class EventoDeleted(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ROWID")
    var rowid: Int? = null,

    @Column
    var NUMEV: Int? = null,

    @Column
    var OPERACAO: String? = null,

    @Column
    var TIPO: String? = null,

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
    var MOTIVO: String? = null,

    @Column
    var REFERENCIA: String? = null,


    @Column
    var STATUS_OLD: String? = null
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