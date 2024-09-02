package br.rs.gov.defensoria.rhe.entity.jpa

import jakarta.persistence.*
import java.time.LocalDate
import java.util.*

@Entity
@Table(name = "rhe_dados_lic_premio_pend_historico")
class LicencaPremioPend(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ROWID")
    var rowid: Int? = null,

    @Column
    var NUMFUNC: Int? = null,

    @Column
    var NUMVINC: Int? = null,

    @Column
    var DTINI: LocalDate? = null,

    @Column
    var DTFIM: LocalDate? = null,

    @Column
    var TOTALDIAS: Int? = null,

    @Column
    var DIAS_TIRADOS: Int? = null,

    @Column
    var DIAS_VENDIDOS: Int? = null,

    @Column
    var CONT_DOBRO: Int? = null,

    @Column
    var SALDO: Int? = null
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