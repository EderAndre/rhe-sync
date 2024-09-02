package br.rs.gov.defensoria.rhe.service

import br.rs.gov.defensoria.rhe.entity.jpa.Vinculo
import br.rs.gov.defensoria.rhe.repository.VinculoRepo
import br.rs.gov.defensoria.rhe.utils.FileUtils
import jakarta.inject.Inject
import jakarta.inject.Singleton
import jakarta.transaction.Transactional

@Singleton
open class ImportVinculo {

    @Inject
    lateinit var vinculoRepo: VinculoRepo

    @Inject
    lateinit var fileUtils: FileUtils

    @Transactional
    open fun importData(fileName: String): String {
        var message: String
        try {
            val res = fileUtils.parseFileCsv(fileName)
            val dataInsert: MutableList<Vinculo> = mutableListOf()
            res.forEach {
                val data = Vinculo(
                    numfunc = it["NUMFUNC"]?.toString()?.toInt() ?: 0,
                    numvinc = it["NUMVINC"]?.toString()?.toInt() ?: 0,
                    DTVAC = fileUtils.analyzeAndCorrectYearOfDate(it["DTVAC"]),
                    DTAPOSENT = fileUtils.analyzeAndCorrectYearOfDate(it["DTAPOSENT"]),
                    TIPOVINC = it["TIPOVINC"],
                    REGIMEJUR = it["REGIMEJUR"],
                    DTEXERC = fileUtils.analyzeAndCorrectYearOfDate(it["DTEXERC"]),
                    DTPOSSE = fileUtils.analyzeAndCorrectYearOfDate(it["DTPOSSE"]),
                    DTNOM = fileUtils.analyzeAndCorrectYearOfDate(it["DTNOM"]),
                    DTESTABILIDADE = fileUtils.analyzeAndCorrectYearOfDate(it["DTESTABILIDADE"]),
                    MATRICULA = it["MATRICULA"],
                    BANCO = it["BANCO"],
                    AGENCIA = it["AGENCIA"],
                    CONTA = it["CONTA"],
                    MATRICULA1 = it["MATRICULA1"],
                    CLASSIFCONC = it["CLASSIFCONC"],
                    REGPREV = it["REGPREV"],
                    LIMITELC = it["LIMITELC"],
                    EMAILCORP = it["EMAILCORP"]
                )
                dataInsert.add(data)
            }
            vinculoRepo.updateAll(dataInsert)
            message = "[+] Importação concluída: $fileName "
        } catch (e: Exception) {
            message = "[-] Erro na importação: $fileName >> $e"
        }
        return message
    }


}