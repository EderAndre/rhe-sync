package br.rs.gov.defensoria.rhe.entity.jpa.pk

import jakarta.persistence.Embeddable
import java.io.Serializable
import java.util.*

@Embeddable
class DependenciaPK(
    private val idFuncional: Int,
    private val nrDependente: Int ,
    private val noTipoDependencia: String,
    private val dtInicio: Date
) : Serializable {

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is DependenciaPK) return false

        return idFuncional == other.idFuncional &&
                nrDependente == other.nrDependente &&
                noTipoDependencia == other.noTipoDependencia &&
                dtInicio == other.dtInicio
    }

    override fun hashCode(): Int {
        return idFuncional.hashCode() + nrDependente.hashCode() + noTipoDependencia.hashCode() + dtInicio.hashCode()
    }
}