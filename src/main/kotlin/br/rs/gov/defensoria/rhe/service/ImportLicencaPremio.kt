package br.rs.gov.defensoria.rhe.service

import br.rs.gov.defensoria.rhe.entity.jpa.LicencaPremio
import br.rs.gov.defensoria.rhe.repository.LicencaPremioRepo
import br.rs.gov.defensoria.rhe.utils.FileUtils
import jakarta.inject.Inject
import jakarta.inject.Singleton
import jakarta.transaction.Transactional


@Singleton
open class ImportLicencaPremio {

    @Inject
    lateinit var licencaPremioRepo: LicencaPremioRepo

    @Inject
    lateinit var fileUtils: FileUtils

    @Transactional
    open fun importData(fileName: String): String {
        var message: String
        try {
            val res = fileUtils.parseFileCsv(fileName)
            val dataInsert: MutableList<LicencaPremio> = mutableListOf()
            res.forEach {

                        var data = LicencaPremio(
                            NUMFUNC = it["NUMFUNC"]?.toInt() ?: 0,
                            NUMVINC = it["NUMVINC"]?.toInt() ?: 0,
                            DTINI = fileUtils.analyzeAndCorrectYearOfDate(it["DTINI"]),
                            DTPREVFIM = fileUtils.analyzeAndCorrectYearOfDate(it["DTPREVTERMINO"]),
                            DTFIM = fileUtils.analyzeAndCorrectYearOfDate(it["DTTERMINO"]),
                            DTINIPA = fileUtils.analyzeAndCorrectYearOfDate(it["DTINICIOPA"]),
                            DTFIMPA = fileUtils.analyzeAndCorrectYearOfDate(it["DTFIMPA"]),
                            ADIANTASAL = it["ADIANTASAL"],
                            OBS = it["OBS"],
                        )
                        dataInsert.add(data)

                }
            licencaPremioRepo.updateAll(dataInsert)
            message = "[+] Importação concluída: $fileName "
        } catch (e: Exception) {
            message = "[-] Erro na importação: $fileName >> $e"
        }
        return message
        return message
    }

}