package br.rs.gov.defensoria.rhe.service

import br.rs.gov.defensoria.rhe.entity.jpa.Trinomio
import br.rs.gov.defensoria.rhe.repository.TrinomioRepo
import br.rs.gov.defensoria.rhe.utils.FileUtils
import jakarta.inject.Inject
import jakarta.inject.Singleton
import jakarta.transaction.Transactional


@Singleton
open class ImportTrinomio {

    @Inject
    lateinit var trinomioRepo: TrinomioRepo

    @Inject
    lateinit var fileUtils: FileUtils

    @Transactional
    open fun importData(fileName: String): String {
        var message: String
        try {
            val res = fileUtils.parseFileCsv(fileName)
            val dataInsert: MutableList<Trinomio> = mutableListOf()
            res.forEach {

                        var data = Trinomio(
                            TIPOVINC = it["TIPOVINC"],
                            CATEGORIA = it["CATEGORIA"],
                            REGIMEJUR = it["REGIMEJUR"]
                        )
                        dataInsert.add(data)

                }
            if(dataInsert.size > 1) trinomioRepo.deleteAll()
            trinomioRepo.updateAll(dataInsert)
            message = "[+] Importação concluída: $fileName "
        } catch (e: Exception) {
            message = "[-] Erro na importação: $fileName >> $e"
        }
        return message
    }

}