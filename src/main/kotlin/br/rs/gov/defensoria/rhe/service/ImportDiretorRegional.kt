package br.rs.gov.defensoria.rhe.service

import br.rs.gov.defensoria.rhe.entity.jpa.DiretorRegional
import br.rs.gov.defensoria.rhe.repository.DiretorRegionalRepo
import br.rs.gov.defensoria.rhe.utils.FileUtils
import jakarta.inject.Inject
import jakarta.inject.Singleton
import jakarta.transaction.Transactional


@Singleton
open class ImportDiretorRegional {

    @Inject
    lateinit var diretorRegionalRepo: DiretorRegionalRepo

    @Inject
    lateinit var fileUtils: FileUtils

    @Transactional
    open fun importData(fileName: String): String {
        var message: String
        try {
            val res = fileUtils.parseFileCsv(fileName)
            val dataInsert: MutableList<DiretorRegional> = mutableListOf()
            res.forEach {

                    var data = DiretorRegional(
                        NUMFUNCTIT = it["NUMFUNCTITULAR"]?.toInt() ?: 0,
                        NUMVINCTIT = it["NUMVINCTITULAR"],
                        DTINITIT = fileUtils.analyzeAndCorrectYearOfDate(it["DTINITIT"]),
                        DTFIMTIT = fileUtils.analyzeAndCorrectYearOfDate(it["DTFIMTIT"]),
                        MUNICIPIO = it["MUNICIPIO"],
                        NUMFUNCSUBST = it["NUMFUNCSUBST"]?.toIntOrNull(),
                        NUMVINCSUBST = it["NUMVINCSUBST"],
                        DTINISUBST = fileUtils.analyzeAndCorrectYearOfDate(it["DTINISUBST"]),
                        DTFIMSUBST = fileUtils.analyzeAndCorrectYearOfDate(it["DTFIMSUBST"]),
                        IDENTIFICADOR = it["IDENTIFICADOR"]

                    )
                    dataInsert.add(data)
                }
            diretorRegionalRepo.updateAll(dataInsert)
            message = "[+] Importação concluída: $fileName "
        } catch (e: Exception) {
            message = "[-] Erro na importação: $fileName >> $e"
        }
        return message
    }

}