package br.rs.gov.defensoria.rhe.entity.jpa

import br.rs.gov.defensoria.rhe.entity.jpa.pk.FuncionalPK
import jakarta.persistence.*
import java.time.LocalDate
import java.util.*

@Entity
@Table(name = "rhe_dados_funcional")
@IdClass(FuncionalPK::class)
class Funcional(
    @Id
    @Column(name = "NUMFUNC")
    var numfunc: Int? = null,

    @Id
    @Column(name = "NUMVINC")
    var numvinc: Int? = null,

    @Column
    var CPF: String? = null,

    @Column
    var NOME: String? = null,

    @Column
    var NUMRG: String? = null,

    @Column
    var UFRG: String? = null,

    @Column
    var SEXO: String? = null,

    @Column
    var DTNASC: LocalDate? = null,

    @Column
    var PAI: String? = null,

    @Column
    var MAE: String? = null,

    @Column
    var ESTCIVIL: String? = null,

    @Column
    var ESCOLARIDADE: String? = null,

    @Column
    var SETOR: String? = null,

    @Column
    var SETOR_LOTACAO: String? = null,

    @Column
    var NOMELOGENDER: String? = null,

    @Column
    var TIPOLOGENDER: String? = null,

    @Column
    var NUMENDER: String? = null,

    @Column
    var BAIRROENDER: String? = null,

    @Column
    var CEPENDER: String? = null,

    @Column
    var COMPLENDER: String? = null,

    @Column
    var UFENDER: String? = null,

    @Column
    var CIDADEENDER: String? = null,

    @Column
    var TELEFONE: String? = null,

    @Column
    var NUMTEL_CELULAR: String? = null,

    @Column
    var NACIONALIDADE: String? = null,

    @Column
    var DTVAC: String? = null,

    @Column
    var DTAPOSENT: String? = null,

    @Column
    var TIPOVINC: String? = null,

    @Column
    var REGIMEJUR: String? = null,

    @Column
    var DTEXERC: String? = null,

    @Column
    var DTPOSSE: String? = null,

    @Column
    var DTNOM: String? = null,

    @Column
    var MATRICULA: String? = null,

    @Column(name = "E_MAIL")
    var email: String? = null,

    @Column
    var BANCO: String? = null,

    @Column
    var AGENCIA: String? = null,

    @Column
    var CONTA: String? = null,

    @Column
    var PISPASEP: String? = null,

    @Column
    var COD_CIDNASC: String? = null,

    @Column
    var COD_CIDENDER: String? = null,

    @Column
    var DTINI_CESSAO: String? = null,

    @Column
    var DTAFAST: String? = null,

    @Column
    var SIT_RHE: String? = null,

    @Column(name="G_SANGUINEO")
    var gsanguineo: String? = null,

    @Column
    var RACA: String? = null,

    @Column
    var DEFICIENTE: String? = null,

    @Column
    var TIPODEFIC: String? = null,

    @Column
    var OBSERV: String? = null,

    @Column
    var ORGAORG: String? = null,

    @Column
    var EXPEDRG: String? = null,

    @Column
    var NUMTITEL: String? = null,

    @Column
    var ZONATITEL: String? = null,

    @Column
    var SECTITEL: String? = null,

    @Column
    var UFTITEL: String? = null,
    @Column
    var NUMDOCMILI: String? = null,

    @Column
    var DOCMILITAR: String? = null,

    @Column
    var SERDOCMILI: String? = null,

    @Column
    var CATMILI: String? = null,

    @Column
    var UFDOCMILI: String? = null,

    @Column
    var FORCA: String? = null,

    @Column
    var CNH: String? = null,

    @Column
    var CATCNH: String? = null,

    @Column
    var ORGEXPCNH: String? = null,

    @Column
    var DATAEXPCNH: String? = null,

    @Column
    var UFCNH: String? = null,

    @Column
    var VALIDCNH: String? = null,

    @Column
    var IDENTPROF: String? = null,

    @Column
    var TIPOIDPROF: String? = null,

    @Column(name="UF_IDENTPROF")
    var ufIdentProf: String? = null,

    @Column
    var DTEXPIDENTPROF: String? = null


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