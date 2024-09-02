package br.rs.gov.defensoria.rhe.repository


import br.rs.gov.defensoria.rhe.entity.jpa.Formacao
import io.micronaut.data.annotation.Repository
import io.micronaut.data.jpa.repository.JpaRepository

@Repository
interface FormacaoRepo : JpaRepository<Formacao, Int>

