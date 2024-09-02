package br.rs.gov.defensoria.rhe.service

import br.rs.gov.defensoria.rhe.entity.jpa.FeriasPend
import br.rs.gov.defensoria.rhe.repository.FeriasPendRepo
import br.rs.gov.defensoria.rhe.utils.FileUtils
import jakarta.inject.Inject
import jakarta.inject.Singleton
import jakarta.transaction.Transactional


@Singleton
open class ImportFeriasPend {

    @Inject
    lateinit var feriasPendRepo: FeriasPendRepo

    @Inject
    lateinit var fileUtils: FileUtils

    @Transactional
    open fun importData(fileName: String): String {
        var message: String
        try {
            val res = fileUtils.parseFileCsv(fileName)
            val dataInsert: MutableList<FeriasPend> = mutableListOf()
            res.forEach {

                        var data = FeriasPend(
                            NUMFUNC = it["NUMFUNC"]?.toInt() ?: 0,
                            NUMVINC = it["NUMVINC"],
                            DTINI = fileUtils.analyzeAndCorrectYearOfDate(it["DTINI"]),
                            DTFIM = fileUtils.analyzeAndCorrectYearOfDate(it["DTFIM"]),
                            TOTALDIAS = it["TOTALDIAS"]?.toInt() ?: 0,
                            DIAS_TIRADOS = it["DIAS_TIRADOS"]?.toInt() ?: 0,
                            DIAS_VENDIDOS = it["DIAS_VENDIDOS"]?.toInt() ?: 0,
                            DIAS_CONVERTIDOS = it["DIAS_CONVERTIDOS"]?.toInt() ?: 0,
                            SALDO = it["SALDO"]?.toInt() ?: 0,
                            dtPrescricao = fileUtils.analyzeAndCorrectYearOfDate(it["DT_PRESCRICAO"])
                        )
                        dataInsert.add(data)

                }
            feriasPendRepo.updateAll(dataInsert)
            message = "[+] Importação concluída: $fileName "
        } catch (e: Exception) {
            message = "[-] Erro na importação: $fileName >> $e"
        }
        return message
    }

}