package br.rs.gov.defensoria.rhe.entity.jpa

import br.rs.gov.defensoria.rhe.entity.jpa.pk.CapacitacaoPK
import jakarta.persistence.*
import java.time.LocalDate
import java.util.*

@Entity
@Table(name = "rhe_dados_capacitacoes")
@IdClass(value= CapacitacaoPK::class)
class Capacitacao(
    @Id
    @Column
    var NUMFUNC: Int? = null,
    @Id
    @Column
    var EVENTO: Int? = null,

    @Column
    var NOME: String? = null,


    @Column
    var DATA: LocalDate? = null,


    @Column
    var CARGAHORARIA: String? = null,


    @Column
    var ENTIDADE: String? = null,

    @Column
    var OBS: String? = null
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