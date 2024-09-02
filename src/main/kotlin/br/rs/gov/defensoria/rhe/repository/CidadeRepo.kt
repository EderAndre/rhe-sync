package br.rs.gov.defensoria.rhe.repository


import br.rs.gov.defensoria.rhe.entity.jpa.Cidade
import io.micronaut.data.annotation.Repository
import io.micronaut.data.jpa.repository.JpaRepository
import java.util.*

@Repository
interface CidadeRepo : JpaRepository<Cidade, Int>{
    fun findByIdCidade(idCidade: Int): Optional<Cidade>?
}

