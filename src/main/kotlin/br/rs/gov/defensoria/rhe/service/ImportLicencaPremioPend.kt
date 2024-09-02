package br.rs.gov.defensoria.rhe.service

import br.rs.gov.defensoria.rhe.entity.jpa.LicencaPremioPend
import br.rs.gov.defensoria.rhe.repository.LicencaPremioPendRepo
import br.rs.gov.defensoria.rhe.utils.FileUtils
import jakarta.inject.Inject
import jakarta.inject.Singleton
import jakarta.transaction.Transactional


@Singleton
open class ImportLicencaPremioPend {

    @Inject
    lateinit var licencaPremioPendRepo: LicencaPremioPendRepo

    @Inject
    lateinit var fileUtils: FileUtils

    @Transactional
    open fun importData(fileName: String): String {
        var message: String
        try {
            val res = fileUtils.parseFileCsv(fileName)
            val dataInsert: MutableList<LicencaPremioPend> = mutableListOf()
            res.forEach {

                        val data = LicencaPremioPend(
                            NUMFUNC = it["NUMFUNC"]?.toInt() ?: 0,
                            NUMVINC = it["NUMVINC"]?.toInt() ?: 0,
                            DTINI = fileUtils.analyzeAndCorrectYearOfDate(it["DTINI"]),
                            DTFIM = fileUtils.analyzeAndCorrectYearOfDate(it["DTFIM"]),
                            TOTALDIAS = it["DIAS_DIREITO"]?.toIntOrNull(),
                            DIAS_TIRADOS = it["GOZO"]?.toIntOrNull(),
                            DIAS_VENDIDOS = it["VENDA"]?.toIntOrNull() ,
                            CONT_DOBRO = it["CONT_DOBRO"]?.toIntOrNull()  ,
                            SALDO = it["HAVER"]?.toIntOrNull()
                        )
                        dataInsert.add(data)

                }
            licencaPremioPendRepo.saveAll(dataInsert)
            licencaPremioPendRepo.flush()
            message = "[+] Importação concluída: $fileName "
        } catch (e: Exception) {
            message = "[-] Erro na importação: $fileName >> $e"
        }
        return message
    }

}