package br.rs.gov.defensoria.rhe.entity.jpa

import jakarta.persistence.*
import java.time.LocalDate
import java.util.*

@Entity
@Table(name = "rhe_dados_formacoes")
class Formacao(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ROWID")
    var rowid: Int? = null,

    @Column
    var NUMFUNC: Int? = null,

    @Column
    var CURSOFORM: String? = null,

    @Column
    var HABILITACAO: String? = null,

    @Column
    var ESCOLA: String? = null,

    @Column
    var DTINI: LocalDate? = null,

    @Column
    var DTFIM: LocalDate? = null,

    @Column
    var CARGAHOR: String? = null,

    @Column
    var PTSCONTA: String? = null,

    @Column
    var VALIDPROMO: String? = null,

    @Column
    var PAGO: String? = null,

    @Column
    var PONTOLIB: String? = null,

    @Column
    var BOLSA: String? = null,

    @Column
    var DESCRICAO: String? = null,

    @Column
    var DATA: LocalDate? = null,

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