package br.rs.gov.defensoria.rhe.service

import br.rs.gov.defensoria.rhe.entity.jpa.TipoEvento
import br.rs.gov.defensoria.rhe.repository.TipoEventoRepo
import br.rs.gov.defensoria.rhe.utils.FileUtils
import jakarta.inject.Inject
import jakarta.inject.Singleton
import jakarta.transaction.Transactional


@Singleton
open class ImportTipoEvento {

    @Inject
    lateinit var tipoEventoRepo: TipoEventoRepo

    @Inject
    lateinit var fileUtils: FileUtils

    @Transactional
    open fun importData(fileName: String): String {
        var message: String
        try {
            val res = fileUtils.parseFileCsv(fileName)
            val dataInsert: MutableList<TipoEvento> = mutableListOf()
            res.forEach {

                        var data = TipoEvento(
                            TIPOEVENTO = it["TIPOEVENTO"],
                            NOME = it["NOME"],
                            NATUREZA = it["NATUREZA"],
                            NATUREZA_PRINCIPAL = it["NATUREZA_PRINCIPAL"],
                            TIPO_CARGO = it["TIPO_CARGO"]                        )
                        dataInsert.add(data)

                }
            if(dataInsert.size > 1) tipoEventoRepo.deleteAll()
            tipoEventoRepo.updateAll(dataInsert)
            message = "[+] Importação concluída: $fileName "
        } catch (e: Exception) {
            message = "[-] Erro na importação: $fileName >> $e"
        }
        return message
    }

}