package br.rs.gov.defensoria.rhe.entity.jpa

import br.rs.gov.defensoria.rhe.entity.jpa.pk.SetorPK
import jakarta.persistence.*
import java.time.LocalDate
import java.util.*

@Entity
@Table(name = "rhe_mestre_setor")
@IdClass(SetorPK::class)
class Setor(
    @Id
    @Column(name = "EMP_CODIGO")
    var emp_codigo: Int? = null,

    @Id
    @Column(name = "SETOR")
    var setor: Int? = null,

    @Column
    var PAISETOR: String? = null,

    @Column
    var NOMESETOR: String? = null,

    @Column
    var NOMESETORLONGO: String? = null,

    @Column
    var DATAINI: LocalDate? = null,


    @Column
    var SETOR_CORP: String? = null,

    @Column
    var HLOCAL: String? = null,

    @Column
    var TIPOSETOR: String? = null,

    @Column
    var TIPOLOG: String? = null,

    @Column
    var NOMELOG: String? = null,

    @Column
    var NUMEROLOG: String? = null,

    @Column
    var COMPLEMENTO: String? = null,

    @Column
    var BAIRRO: String? = null,

    @Column
    var CEP: String? = null,

    @Column
    var CIDADE: String? = null,

    @Column
    var UF: String? = null,

    @Column
    var FONE: String? = null,

    @Column
    var FAX: String? = null,

    @Column
    var ORGANIZACAO: String? = null,

    @Column
    var SIGLA: String? = null
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