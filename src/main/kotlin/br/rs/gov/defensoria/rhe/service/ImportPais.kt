package br.rs.gov.defensoria.rhe.service

import br.rs.gov.defensoria.rhe.entity.jpa.Pais
import br.rs.gov.defensoria.rhe.repository.PaisRepo
import br.rs.gov.defensoria.rhe.utils.FileUtils
import jakarta.inject.Inject
import jakarta.inject.Singleton
import jakarta.transaction.Transactional


@Singleton
open class ImportPais {

    @Inject
    lateinit var paisRepo: PaisRepo

    @Inject
    lateinit var fileUtils: FileUtils

    @Transactional
    open fun importData(fileName: String): String {
        var message: String
        try {
            val res = fileUtils.parseFileCsv(fileName)
            val dataInsert: MutableList<Pais> = mutableListOf()
            res.forEach {

                        var data = Pais(
                            idPais = it["ID_PAIS"]?.toInt() ?: 0,
                            NACIONALIDADE = it["NACIONALIDADE"]
                        )
                        dataInsert.add(data)

                }
            paisRepo.updateAll(dataInsert)
            message = "[+] Importação concluída: $fileName "
        } catch (e: Exception) {
            message = "[-] Erro na importação: $fileName >> $e"
        }
        return message
    }

}