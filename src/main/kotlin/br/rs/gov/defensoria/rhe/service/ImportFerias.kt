package br.rs.gov.defensoria.rhe.service

import br.rs.gov.defensoria.rhe.entity.jpa.Ferias
import br.rs.gov.defensoria.rhe.repository.FeriasRepo
import br.rs.gov.defensoria.rhe.utils.FileUtils
import jakarta.inject.Inject
import jakarta.inject.Singleton
import jakarta.transaction.Transactional


@Singleton
open class ImportFerias {

    @Inject
    lateinit var feriasRepo: FeriasRepo

    @Inject
    lateinit var fileUtils: FileUtils

    @Transactional
    open fun importData(fileName: String): String {
        var message: String
        try {
            val res = fileUtils.parseFileCsv(fileName)
            val dataInsert: MutableList<Ferias> = mutableListOf()
            res.forEach {

                        var data = Ferias(
                            NUMFUNC = it["NUMFUNC"]?.toInt() ?: 0,
                            NUMVINC = it["NUMVINC"],
                            DTINI = fileUtils.analyzeAndCorrectYearOfDate(it["DTINI"]),
                            DTFIM = fileUtils.analyzeAndCorrectYearOfDate(it["DTFIM"]),
                            MESANOPAGTO = it["MÊSANOPAGTO"],
                            NRODIASPAGTO = it["NRODIASPAGTO"],
                            DTINIPA = fileUtils.analyzeAndCorrectYearOfDate(it["INICIOPA"]),
                            DTFIMPA = fileUtils.analyzeAndCorrectYearOfDate(it["FIMPA"]),
                            TOTALDIAS = it["TOTALDIAS"]?.toInt() ?: 0,
                            DIASVENDIDOS = it["DIASVENDIDOS"]?.toInt() ?: 0,
                            DIASGOZADOS = it["DIASGOZADOS"]?.toInt() ?: 0,
                            SALDO = it["SALDO"]?.toInt() ?: 0,
                            ADIANTASAL = it["ADIANTASAL"],
                            OBS = it["OBS"],


                        )
                        dataInsert.add(data)

                }
            feriasRepo.updateAll(dataInsert)
            message = "[+] Importação concluída: $fileName "
        } catch (e: Exception) {
            message = "[-] Erro na importação: $fileName >> $e"
        }
        return message
    }

}