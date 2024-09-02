package br.rs.gov.defensoria.rhe.entity.jpa.pk

import jakarta.persistence.Embeddable
import java.io.Serializable

@Embeddable
class FuncionalPK(
    private val numfunc: Int = 0,
    private val numvinc: Int = 0
) : Serializable {

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is FuncionalPK) return false

        return numfunc == other.numfunc && numvinc == other.numvinc
    }

    override fun hashCode(): Int {
        return numfunc.hashCode() + numvinc.hashCode()
    }
}