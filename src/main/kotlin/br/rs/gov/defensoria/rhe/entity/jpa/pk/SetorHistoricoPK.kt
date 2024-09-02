package br.rs.gov.defensoria.rhe.entity.jpa.pk

import jakarta.persistence.Embeddable
import java.io.Serializable

@Embeddable
class SetorHistoricoPK(
    private val emp_codigo: String ,
    private val setor: String
) : Serializable {

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is SetorHistoricoPK) return false

        return emp_codigo == other.emp_codigo && setor == other.setor
    }

    override fun hashCode(): Int {
        return emp_codigo.hashCode() + setor.hashCode()
    }
}