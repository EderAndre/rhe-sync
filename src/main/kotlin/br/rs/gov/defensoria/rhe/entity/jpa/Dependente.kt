package br.rs.gov.defensoria.rhe.entity.jpa

import br.rs.gov.defensoria.rhe.entity.jpa.pk.DependentePK
import jakarta.persistence.*
import java.time.LocalDate
import java.util.*

@Entity
@Table(name = "rhe_dados_dependentes")
@IdClass(value = DependentePK::class)
class Dependente(
    @Id
    @Column(name = "id_funcional")
    var idFuncional: Int? = null,
    @Id
    @Column(name = "nr_dependente")
    var nrDependente: Int? = null,


    @Column
    var nrPens: String? = null,

    @Column
    var idFuncionalDependente: String? = null,

    @Column
    var nrcpf: String? = null,

    @Column
    var noDependente: String? = null,

    @Column
    var noParentesco: String? = null,

    @Column
    var dtNascimento: LocalDate? = null,

    @Column
    var cdSexo: String? = null,

    @Column
    var dtInicio: LocalDate? = null,

    @Column
    var dtTermino: LocalDate? = null,

    @Column
    var noEstadoCivil: String? = null,

    @Column
    var fgEstudante: String? = null,

    @Column
    var fgUniversitario: String? = null,

    @Column
    var fgInvalido: String? = null,

    @Column
    var fgExcepcional: String? = null,

    @Column
    var noTipoInvalidez: String? = null,

    @Column
    var noTipoExcepcional: String? = null,

    @Column
    var nrRg: String? = null,

    @Column
    var tpRg: String? = null,

    @Column
    var deOrgaoRg: String? = null,

    @Column
    var cdUfRg: String? = null,
    @Column
    var dtExpedicaoRg: String? = null,
    @Column
    var tpDocCertnc: String? = null,
    @Column
    var nrCertnc: String? = null,

    @Column
    var nrLivroCertnc: String? = null,

    @Column
    var nrFolhaCertnc: String? = null,

    @Column
    var nrCartorioCertnc: String? = null,

    @Column
    var cdUfCertnc: String? = null,

    @Column
    var cdMunicipioCertnc: String? = null,

    @Column
    var nrMatriculaCertnc: String? = null,

    @Column
    var tpDocCertod: String? = null,

    @Column
    var dtCertod: String? = null,

    @Column
    var nrCertod: String? = null,

    @Column
    var nrLivroCertod: String? = null,

    @Column
    var nrFolhaCertod: String? = null,

    @Column
    var nrCartoriioCertod: String? = null,

    @Column
    var cdUfCertod: String? = null,

    @Column
    var cdMunicipioCertod: String? = null,

    @Column
    var ndMatriculaCertod: String? = null,

    @Column
    var regraAlimentado: String? = null
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