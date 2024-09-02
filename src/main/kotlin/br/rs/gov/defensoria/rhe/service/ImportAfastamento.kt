package br.rs.gov.defensoria.rhe.service

import br.rs.gov.defensoria.rhe.entity.jpa.Afastamento
import br.rs.gov.defensoria.rhe.repository.AfastamentoRepo
import br.rs.gov.defensoria.rhe.utils.FileUtils
import jakarta.inject.Inject
import jakarta.inject.Singleton
import jakarta.transaction.Transactional
import java.text.SimpleDateFormat
import java.time.ZoneId


@Singleton
open class ImportAfastamento {

    @Inject
    lateinit var afastamentoRepo: AfastamentoRepo

    @Inject
    lateinit var fileUtils: FileUtils

    @Transactional
    open fun importData(fileName: String): String {
        var message: String
        try {
            val res = fileUtils.parseFileCsv(fileName)
            val dataInsert: MutableList<Afastamento> = mutableListOf()
            val  format = SimpleDateFormat("dd/MM/yyyy")
            res.forEach {
                if(it["OPERACAO"]=="E"){
                    val registry=afastamentoRepo.findById(it["CHAVE"]?.toInt())
                    if(registry.isPresent) afastamentoRepo.delete(registry.get())
                }
                else {

                    var data = Afastamento(
                        CHAVE = it["CHAVE"]?.toInt() ?: 0,
                        NUMFUNC = it["NUMFUNC"]?.toInt() ?: 0,
                        NUMVINC = it["NUMVINC"],
                        DTINI = if (it["DTINI"] != "") format.parse(it["DTINI"]).toInstant()
                            .atZone(ZoneId.systemDefault()).toLocalDate() else null,
                        DTFIM = if (it["DTFIM"] != "") format.parse(it["DTFIM"]).toInstant()
                            .atZone(ZoneId.systemDefault()).toLocalDate() else null,
                        MOTIVO = it["MOTIVO"],
                        DTPREVFIM = if (it["DTPREVFIM"] != "") format.parse(it["DTPREVFIM"]).toInstant()
                            .atZone(ZoneId.systemDefault()).toLocalDate() else null,
                    )
                    dataInsert.add(data)
                }
            }
            afastamentoRepo.updateAll(dataInsert)
            message = "[+] Importação concluída: $fileName "
        } catch (e: Exception) {
            message = "[-] Erro na importação: $fileName >> $e"
        }
        return message
    }

}