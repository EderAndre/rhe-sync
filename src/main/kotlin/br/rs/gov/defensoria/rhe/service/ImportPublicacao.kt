package br.rs.gov.defensoria.rhe.service

import br.rs.gov.defensoria.rhe.entity.jpa.Publicacao
import br.rs.gov.defensoria.rhe.repository.PublicacaoRepo
import br.rs.gov.defensoria.rhe.utils.FileUtils
import jakarta.inject.Inject
import jakarta.inject.Singleton
import jakarta.transaction.Transactional


@Singleton
open class ImportPublicacao {

    @Inject
    lateinit var publicacaoRepo: PublicacaoRepo

    @Inject
    lateinit var fileUtils: FileUtils

    @Transactional
    open fun importData(fileName: String): String {
        var message: String
        try {
            val res = fileUtils.parseFileCsv(fileName)
            val dataInsert: MutableList<Publicacao> = mutableListOf()
            res.forEach {

                    var data = Publicacao(
                        NUMFUNC = it["NUMFUNC"]?.toInt() ?: 0,
                        NUMVINC = it["NUMVINC"]?.toInt() ?: 0,
                        TIPOEVENTO = it["TIPOEVENTO"],
                        NUMPUBL = it["NUMPUBL"],
                        DATAPUBL = fileUtils.analyzeAndCorrectYearOfDate(it["DATAPUBL"]),
                        TIPOPUBL = it["TIPOPUBL"],
                        DATADO = fileUtils.analyzeAndCorrectYearOfDate(it["DATADO"]),
                        TIPODO = it["TIPODO"],
                        AUTORIDADE = it["AUTORIDADE"],
                        NUMERO_PROCESSO = it["NUMERO_PROCESSO"],
                        MOTIVO = it["MOTIVO"],
                        OBS = it["OBS"],
                        PUBLICACAO = it["PUBLICACAO"],

                    )
                    dataInsert.add(data)
                }
            publicacaoRepo.updateAll(dataInsert)
            message = "[+] Importação concluída: $fileName "
        } catch (e: Exception) {
            message = "[-] Erro na importação: $fileName >> $e"
        }
        return message
    }

}