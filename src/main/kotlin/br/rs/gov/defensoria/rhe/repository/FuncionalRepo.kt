package br.rs.gov.defensoria.rhe.repository


import br.rs.gov.defensoria.rhe.entity.jpa.Funcional
import br.rs.gov.defensoria.rhe.entity.jpa.pk.FuncionalPK
import io.micronaut.data.annotation.Repository
import io.micronaut.data.jpa.repository.JpaRepository

@Repository
interface FuncionalRepo : JpaRepository<Funcional, FuncionalPK> {
    fun findByNumfunc(numfunc: Int): List<Funcional>
}

