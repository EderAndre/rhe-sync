package br.rs.gov.defensoria.rhe.entity.jpa.pk

import jakarta.persistence.Embeddable
import java.io.Serializable

@Embeddable
class CapacitacaoPK(
    private val NUMFUNC: Int = 0,
    private val EVENTO: Int = 0
) : Serializable {

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is CapacitacaoPK) return false

        return NUMFUNC == other.NUMFUNC && EVENTO == other.EVENTO
    }

    override fun hashCode(): Int {
        return NUMFUNC.hashCode() + EVENTO.hashCode()
    }
}