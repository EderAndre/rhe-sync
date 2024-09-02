package br.rs.gov.defensoria.rhe.entity.jpa

import jakarta.persistence.*
import java.util.*

@Entity
@Table(name = "rhe_mestre_trinomio")
class Trinomio(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ROWID")
    var rowid: Int? = null,

    @Column
    var TIPOVINC: String? = null,

    @Column
    var CATEGORIA: String? = null,

    @Column
    var REGIMEJUR: String? = null
)
{
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