package br.rs.gov.defensoria.rhe.service

import br.rs.gov.defensoria.rhe.entity.jpa.Evento
import br.rs.gov.defensoria.rhe.repository.EventoRepo
import br.rs.gov.defensoria.rhe.utils.FileUtils
import jakarta.inject.Inject
import jakarta.inject.Singleton
import jakarta.transaction.Transactional


@Singleton
open class ImportEvento {

    @Inject
    lateinit var eventoRepo: EventoRepo

    @Inject
    lateinit var fileUtils: FileUtils

    @Transactional
    open fun importData(fileName: String): String {
        var message: String
        try {
            val res = fileUtils.parseFileCsv(fileName)
            val dataInsert: MutableList<Evento> = mutableListOf()
            res.forEach {

                var data = Evento(
                    NUMEV = it["NUMEV"]?.toInt() ?: 0,
                    NUMFUNC = it["NUMFUNC"]?.toInt() ?: 0,
                    NUMVINC = it["NUMVINC"],
                    CARGO = it["CARGO"],
                    DTINI = fileUtils.analyzeAndCorrectYearOfDate(it["DTINI"]),
                    DTFIM = fileUtils.analyzeAndCorrectYearOfDate(it["DTFIM"]),
                    TIPOEVENTO = it["TIPOEVENTO"],
                    REFERENCIA = it["REFERENCIA"],
                    CARGO_FUNCAO = it["CARGO_FUNCAO"],
                    SETOR = it["SETOR"],
                    ESPECIEEVENTO = it["ESPECIEEVENTO"],
                    FRACAO = it["FRACAO"],
                    REPR = it["REPR"],
                    PROVESP = it["PROVESP"],
                    OBS = it["OBS"],


                    )
                dataInsert.add(data)

            }
            eventoRepo.updateAll(dataInsert)
            message = "[+] Importação concluída: $fileName "
        } catch (e: Exception) {
            message = "[-] Erro na importação: $fileName >> $e"
        }
        return message
    }

}