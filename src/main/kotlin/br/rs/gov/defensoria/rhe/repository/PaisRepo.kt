package br.rs.gov.defensoria.rhe.repository


import br.rs.gov.defensoria.rhe.entity.jpa.Pais
import io.micronaut.data.annotation.Repository
import io.micronaut.data.jpa.repository.JpaRepository
import java.util.*

@Repository
interface PaisRepo : JpaRepository<Pais, Int>{
    fun findByIdPais(idPais: Int): Optional<Pais>?
}

