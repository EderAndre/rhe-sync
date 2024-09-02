package br.rs.gov.defensoria.rhe.repository


import br.rs.gov.defensoria.rhe.entity.jpa.LicencaPremioPend
import io.micronaut.data.annotation.Repository
import io.micronaut.data.jpa.repository.JpaRepository

@Repository
interface LicencaPremioPendRepo : JpaRepository<LicencaPremioPend, Int>

