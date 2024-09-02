package br.rs.gov.defensoria.rhe.entity.jpa

import jakarta.persistence.*
import java.time.LocalDate
import java.util.*

@Entity
@Table(name = "rhe_mestre_cargos")
class Cargo(
    @Id
    @Column
    var COD_CARGO_FUNCAO: Int? = null,

    @Column
    var NOME_CARGO_FUNCAO: String? = null,

    @Column
    var TIPO_CARGO: String? = null,

    @Column
    var CARGO_FUNCAO: String? = null,

    @Column
    var CATEGORIA: String? = null,

    @Column
    var SUBCATEGORIA: String? = null,

    @Column(name="DT_EXTINCAO")
    var dtExtincao: LocalDate? = null


) {

    @Column(updatable = false)
    var CREATED: Date? = null

    @Column
    var LASTUPDATED: Date? = null

    @PrePersist
    fun onCreate() {
        CREATED = Date()
    }

    @PreUpdate
    fun onUpdate() {
        LASTUPDATED = Date()
    }
}