package br.rs.gov.defensoria.rhe.service

import br.rs.gov.defensoria.rhe.entity.jpa.Capacitacao
import br.rs.gov.defensoria.rhe.repository.CapacitacaoRepo
import br.rs.gov.defensoria.rhe.utils.FileUtils
import jakarta.inject.Inject
import jakarta.inject.Singleton
import jakarta.transaction.Transactional
import java.text.SimpleDateFormat
import java.time.ZoneId


@Singleton
open class ImportCapacitacao {

    @Inject
    lateinit var capacitacaoRepo: CapacitacaoRepo

    @Inject
    lateinit var fileUtils: FileUtils

    @Transactional
    open fun importData(fileName: String): String {
        var message: String
        try {
            val res = fileUtils.parseFileCsv(fileName)
            val dataInsert: MutableList<Capacitacao> = mutableListOf()
            val  format = SimpleDateFormat("dd/MM/yyyy")
            res.forEach {

                    var data = Capacitacao(
                        NUMFUNC = it["NUMFUNC"]?.toInt() ?: 0,
                        EVENTO = it["EVENTO"]?.toInt() ?: 0,
                        NOME = it["NOME"],
                        DATA = if (it["DATA"] != "") format.parse(it["DATA"]).toInstant()
                            .atZone(ZoneId.systemDefault()).toLocalDate() else null,
                        CARGAHORARIA = it["CARGAHORARIA"],
                        ENTIDADE = it["ENTIDADE"],
                        OBS = it["OBS"]
                    )
                    dataInsert.add(data)
                }
            capacitacaoRepo.updateAll(dataInsert)
            message = "[+] Importação concluída: $fileName "
        } catch (e: Exception) {
            message = "[-] Erro na importação: $fileName >> $e"
        }
        return message
    }

}