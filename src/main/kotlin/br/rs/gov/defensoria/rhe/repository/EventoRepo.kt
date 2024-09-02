package br.rs.gov.defensoria.rhe.repository


import br.rs.gov.defensoria.rhe.entity.jpa.Evento
import io.micronaut.data.annotation.Repository
import io.micronaut.data.jpa.repository.JpaRepository
import java.util.*

@Repository
interface EventoRepo : JpaRepository<Evento, Int>{
    fun findByNUMEV(numev: Int?): Optional<Evento>
}

