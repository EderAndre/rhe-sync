package br.rs.gov.defensoria.rhe.service

import br.rs.gov.defensoria.rhe.entity.jpa.Formacao
import br.rs.gov.defensoria.rhe.repository.FormacaoRepo
import br.rs.gov.defensoria.rhe.utils.FileUtils
import jakarta.inject.Inject
import jakarta.inject.Singleton
import jakarta.transaction.Transactional


@Singleton
open class ImportFormacao {

    @Inject
    lateinit var formacaoRepo: FormacaoRepo

    @Inject
    lateinit var fileUtils: FileUtils

    @Transactional
    open fun importData(fileName: String): String {
        var message: String
        try {
            val res = fileUtils.parseFileCsv(fileName)
            val dataInsert: MutableList<Formacao> = mutableListOf()
            res.forEach {

                        var data = Formacao(
                            NUMFUNC = it["NUMFUNC"]?.toInt() ?: 0,
                            CURSOFORM = it["CURSOFORM"],
                            ESCOLA = it["ESCOLA"],
                            DTINI = fileUtils.analyzeAndCorrectYearOfDate(it["DTINI"]),
                            DTFIM = fileUtils.analyzeAndCorrectYearOfDate(it["DTFIM"]),
                            CARGAHOR = it["CARGAHOR"],
                            PTSCONTA = it["PTSCONTA"],
                            VALIDPROMO = it["VALIDPROMO"],
                            PAGO = it["PAGO"],
                            PONTOLIB = it["PONTOLIB"],
                            BOLSA = it["BOLSA"],
                            DESCRICAO = it["DESCRICAO"],
                            DATA = fileUtils.analyzeAndCorrectYearOfDate(it["DATA"]),
                            OBS = it["OBS"]
                        )
                        dataInsert.add(data)

                }
            formacaoRepo.updateAll(dataInsert)
            message = "[+] Importação concluída: $fileName "
        } catch (e: Exception) {
            message = "[-] Erro na importação: $fileName >> $e"
        }
        return message
    }

}