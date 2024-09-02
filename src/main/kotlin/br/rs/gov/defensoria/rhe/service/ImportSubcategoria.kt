package br.rs.gov.defensoria.rhe.service

import br.rs.gov.defensoria.rhe.entity.jpa.Subcategoria
import br.rs.gov.defensoria.rhe.repository.SubcategoriaRepo
import br.rs.gov.defensoria.rhe.utils.FileUtils
import jakarta.inject.Inject
import jakarta.inject.Singleton
import jakarta.transaction.Transactional


@Singleton
open class ImportSubcategoria {

    @Inject
    lateinit var subcategoriaRepo: SubcategoriaRepo

    @Inject
    lateinit var fileUtils: FileUtils

    @Transactional
    open fun importData(fileName: String): String {
        var message: String
        try {
            val res = fileUtils.parseFileCsv(fileName)
            val dataInsert: MutableList<Subcategoria> = mutableListOf()
            res.forEach {

                        var data = Subcategoria(
                            CATEGORIA = it["CATEGORIA"],
                            SIGLA = it["NOME_CIDADE"]
                        )
                        dataInsert.add(data)

                }
            if(dataInsert.size > 1) subcategoriaRepo.deleteAll()
            subcategoriaRepo.updateAll(dataInsert)
            message = "[+] Importação concluída: $fileName "
        } catch (e: Exception) {
            message = "[-] Erro na importação: $fileName >> $e"
        }
        return message
    }

}