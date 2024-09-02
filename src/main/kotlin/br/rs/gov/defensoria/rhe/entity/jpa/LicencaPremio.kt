package br.rs.gov.defensoria.rhe.entity.jpa

import jakarta.persistence.*
import java.time.LocalDate
import java.util.*

@Entity
@Table(name = "rhe_dados_lic_premio_historico")
class LicencaPremio(
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
    var DTPREVFIM: LocalDate? = null,

    @Column
    var DTFIM: LocalDate? = null,

    @Column
    var DTINIPA: LocalDate? = null,

    @Column
    var DTFIMPA: LocalDate? = null,

    @Column
    var ADIANTASAL: String? = null,

    @Column
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