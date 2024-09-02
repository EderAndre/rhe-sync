package br.rs.gov.defensoria.rhe.entity.jpa

import jakarta.persistence.*
import java.time.LocalDate
import java.util.*

@Entity
@Table(name = "rhe_dados_publicacao")
class Publicacao(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ROWID")
    var rowid: Int? = null,

    @Column
    var NUMFUNC: Int? = null,

    @Column
    var NUMVINC: Int? = null,

    @Column
    var TIPOEVENTO: String? = null,

    @Column
    var NUMPUBL: String? = null,

    @Column
    var DATAPUBL: LocalDate? = null,

    @Column
    var TIPOPUBL: String? = null,

    @Column
    var DATADO: LocalDate? = null,

    @Column
    var TIPODO: String? = null,

    @Column
    var AUTORIDADE: String? = null,

    @Column
    var NUMERO_PROCESSO: String? = null,

    @Column
    var MOTIVO: String? = null,

    @Column
    var OBS: String? = null,

    @Column
    var PUBLICACAO: String? = null,
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