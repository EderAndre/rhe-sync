package br.rs.gov.defensoria.rhe.repository

import br.rs.gov.defensoria.rhe.entity.jpa.Dependencia
import br.rs.gov.defensoria.rhe.entity.jpa.pk.DependenciaPK
import io.micronaut.data.annotation.Repository
import io.micronaut.data.jpa.repository.JpaRepository


@Repository
interface DependenciaRepo : JpaRepository<Dependencia, DependenciaPK> {
    fun findByIdFuncional(idFuncional: Int): List<Dependencia>
}

