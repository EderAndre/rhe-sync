package br.rs.gov.defensoria.rhe.entity.jpa

import br.rs.gov.defensoria.rhe.entity.jpa.pk.DependenciaPK
import jakarta.persistence.*
import java.time.LocalDate
import java.util.*

@Entity
@Table(name = "rhe_dados_dependencias")
@IdClass(value = DependenciaPK::class)
class Dependencia(
    @Id
    @Column(name = "id_funcional")
    var idFuncional: Int? = null,
    @Id
    @Column(name = "nr_dependente")
    var nrDependente: Int? = null,
    @Id
    @Column(name = "no_tipo_dependencia")
    var noTipoDependencia: String? = null,
    @Id
    @Column(name = "dt_inicio")
    var dtInicio: Date? = null,

    @Column
    var dtTermino: LocalDate? = null
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