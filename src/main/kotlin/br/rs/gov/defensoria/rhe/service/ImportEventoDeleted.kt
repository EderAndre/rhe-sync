package br.rs.gov.defensoria.rhe.service

import br.rs.gov.defensoria.rhe.entity.jpa.Evento
import br.rs.gov.defensoria.rhe.entity.jpa.EventoDeleted
import br.rs.gov.defensoria.rhe.repository.EventoDeletedRepo
import br.rs.gov.defensoria.rhe.repository.EventoRepo
import br.rs.gov.defensoria.rhe.utils.FileUtils
import jakarta.inject.Inject
import jakarta.inject.Singleton
import jakarta.transaction.Transactional


@Singleton
open class ImportEventoDeleted {

    @Inject
    lateinit var eventoDeletedRepo: EventoDeletedRepo

    @Inject
    lateinit var eventoRepo: EventoRepo

    @Inject
    lateinit var fileUtils: FileUtils

    @Transactional
    open fun importData(fileName: String): String {
        var message: String
        try {
            val res = fileUtils.parseFileCsv(fileName)
            val dataInsert: MutableList<EventoDeleted> = mutableListOf()
            res.forEach {
                if (it["OPERACAO"] == "E" || it["OPERACAO"] == "A") {
                    val registry = eventoRepo.findByNUMEV(it["NUMEV"]?.toInt())
                    if (registry.isPresent && it["OPERACAO"] == "A") {
                        val evento = registry.get()
                        evento.NUMFUNC = it["NUMFUNC"]?.toInt() ?: 0
                        evento.NUMVINC = it["NUMVINC"]
                        evento.CARGO = it["CARGO"]
                        evento.DTINI = fileUtils.analyzeAndCorrectYearOfDate(it["DTINI"])
                        evento.DTFIM = fileUtils.analyzeAndCorrectYearOfDate(it["DTFIM"])
                        val status = eventoRepo.save(evento)
                        var data = EventoDeleted(
                            NUMEV = it["NUMEV"]?.toInt() ?: 0,
                            OPERACAO = it["OPERACAO"],
                            TIPO = it["TIPO"],
                            NUMFUNC = it["NUMFUNC"]?.toInt() ?: 0,
                            NUMVINC = it["NUMVINC"],
                            CARGO = it["CARGO"],
                            DTINI = fileUtils.analyzeAndCorrectYearOfDate(it["DTINI"]),
                            DTFIM = fileUtils.analyzeAndCorrectYearOfDate(it["DTFIM"]),
                            MOTIVO = it["MOTIVO"],
                            REFERENCIA = it["REFERENCIA"],
                            STATUS_OLD = if (status.javaClass == Evento::class.java) "EVENT_SAVED" else "EVENT_NOT_FOUND"

                        )
                        dataInsert.add(data)
                    } else {
                        var status ="EVENT_NOT_FOUND"
                        if (registry.isPresent){
                            status = "EVENT_DELETED"
                            val eventoDeleted = registry.get()
                            eventoRepo.delete(eventoDeleted)
                        }


                        var data = EventoDeleted(
                            NUMEV = it["NUMEV"]?.toInt() ?: 0,
                            OPERACAO = it["OPERACAO"],
                            TIPO = it["TIPO"],
                            NUMFUNC = it["NUMFUNC"]?.toInt() ?: 0,
                            NUMVINC = it["NUMVINC"],
                            CARGO = it["CARGO"],
                            DTINI = fileUtils.analyzeAndCorrectYearOfDate(it["DTINI"]),
                            DTFIM = fileUtils.analyzeAndCorrectYearOfDate(it["DTFIM"]),
                            MOTIVO = it["MOTIVO"],
                            REFERENCIA = it["REFERENCIA"],
                            STATUS_OLD = status
                        )
                        dataInsert.add(data)
                    }
                }
            }
            eventoDeletedRepo.updateAll(dataInsert)
            message = "[+] Importação concluída: $fileName "
        } catch (e: Exception) {
            message = "[-] Erro na importação: $fileName >> $e"
        }
        return message
    }

}