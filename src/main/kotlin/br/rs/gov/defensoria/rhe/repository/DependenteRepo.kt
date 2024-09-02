package br.rs.gov.defensoria.rhe.repository


import br.rs.gov.defensoria.rhe.entity.jpa.Dependente
import br.rs.gov.defensoria.rhe.entity.jpa.pk.DependentePK
import io.micronaut.data.annotation.Repository
import io.micronaut.data.jpa.repository.JpaRepository

@Repository
interface DependenteRepo : JpaRepository<Dependente, DependentePK> {
    fun findByIdFuncional(idFuncional: Int): List<Dependente>
}

