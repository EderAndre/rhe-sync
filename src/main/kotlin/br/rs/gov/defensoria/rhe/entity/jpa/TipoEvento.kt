package br.rs.gov.defensoria.rhe.entity.jpa

import jakarta.persistence.*
import java.util.*

@Entity
@Table(name = "rhe_mestre_tipoevento")
class TipoEvento(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ROWID")
    var rowid: Int? = null,

    @Column
    var TIPOEVENTO: String? = null,

    @Column
    var NOME: String? = null,

    @Column
    var NATUREZA: String? = null,

    @Column
    var NATUREZA_PRINCIPAL: String? = null,

    @Column
    var TIPO_CARGO: String? = null
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