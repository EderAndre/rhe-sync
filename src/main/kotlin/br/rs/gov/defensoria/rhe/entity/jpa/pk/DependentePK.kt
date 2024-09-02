package br.rs.gov.defensoria.rhe.entity.jpa.pk

import jakarta.persistence.Embeddable
import java.io.Serializable

@Embeddable
class DependentePK(
    private val idFuncional: Int = 0,
    private val nrDependente: Int = 0
) : Serializable {

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is DependentePK) return false

        return idFuncional == other.idFuncional && nrDependente == other.nrDependente
    }

    override fun hashCode(): Int {
        return idFuncional.hashCode() + nrDependente.hashCode()
    }
}