package br.rs.gov.defensoria.rhe.entity.jpa

import br.rs.gov.defensoria.rhe.entity.jpa.pk.FuncionalPK
import jakarta.persistence.*
import java.time.LocalDate
import java.util.*

@Entity
@Table(name = "rhe_dados_vinculo")
@IdClass(FuncionalPK::class)
class Vinculo(
    @Id
    @Column(name = "NUMFUNC")
    var numfunc: Int? = null,

    @Id
    @Column(name = "NUMVINC")
    var numvinc: Int? = null,

    @Column
    var DTVAC: LocalDate? = null,

    @Column
    var DTAPOSENT: LocalDate? = null,

    @Column
    var TIPOVINC: String? = null,

    @Column
    var REGIMEJUR: String? = null,

    @Column
    var DTEXERC: LocalDate? = null,

    @Column
    var DTPOSSE: LocalDate? = null,

    @Column
    var DTNOM: LocalDate? = null,

    @Column
    var DTESTABILIDADE: LocalDate? = null,

    @Column
    var MATRICULA: String? = null,

    @Column
    var BANCO: String? = null,

    @Column
    var AGENCIA: String? = null,

    @Column
    var CONTA: String? = null,

    @Column
    var MATRICULA1: String? = null,

    @Column
    var CLASSIFCONC: String? = null,

    @Column
    var REGPREV: String? = null,

    @Column
    var LIMITELC: String? = null,

    @Column
    var EMAILCORP: String? = null
)
{
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