package br.rs.gov.defensoria.rhe.entity.jpa

import jakarta.persistence.*
import java.time.LocalDate
import java.util.*

@Entity
@Table(name = "rhe_dados_dir_reg")
class DiretorRegional(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ROWID")
    var rowid: Int? = null,

    @Column
    var NUMFUNCTIT: Int? = null,

    @Column
    var NUMVINCTIT: String? = null,

    @Column
    var DTINITIT: LocalDate? = null,

    @Column
    var DTFIMTIT: LocalDate? = null,

    @Column
    var MUNICIPIO: String? = null,

    @Column
    var NUMFUNCSUBST: Int? = null,

    @Column
    var NUMVINCSUBST: String? = null,

    @Column
    var DTINISUBST: LocalDate? = null,

    @Column
    var DTFIMSUBST: LocalDate? = null,

    @Column
    var IDENTIFICADOR: String? = null
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