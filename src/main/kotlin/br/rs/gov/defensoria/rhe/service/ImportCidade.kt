package br.rs.gov.defensoria.rhe.service

import br.rs.gov.defensoria.rhe.entity.jpa.Cidade
import br.rs.gov.defensoria.rhe.repository.CidadeRepo
import br.rs.gov.defensoria.rhe.utils.FileUtils
import jakarta.inject.Inject
import jakarta.inject.Singleton
import jakarta.transaction.Transactional


@Singleton
open class ImportCidade {

    @Inject
    lateinit var cidadeRepo: CidadeRepo

    @Inject
    lateinit var fileUtils: FileUtils

    @Transactional
    open fun importData(fileName: String): String {
        var message: String
        try {
            val res = fileUtils.parseFileCsv(fileName)
            val dataInsert: MutableList<Cidade> = mutableListOf()
            res.forEach {

                        var data = Cidade(
                            idCidade = it["ID_CIDADE"]?.toInt() ?: 0,
                            NOME_CIDADE = it["NOME_CIDADE"]
                        )
                        dataInsert.add(data)

                }
            cidadeRepo.updateAll(dataInsert)
            message = "[+] Importação concluída: $fileName "
        } catch (e: Exception) {
            message = "[-] Erro na importação: $fileName >> $e"
        }
        return message
    }

}