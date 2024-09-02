package br.rs.gov.defensoria.rhe.entity.jpa

import jakarta.persistence.*
import java.util.*

@Entity
@Table(name = "rhe_mestre_pais")
class Pais(
    @Id
    @Column(name="ID_PAIS")
    var idPais: Int? = null,

    @Column
    var NACIONALIDADE: String? = null

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