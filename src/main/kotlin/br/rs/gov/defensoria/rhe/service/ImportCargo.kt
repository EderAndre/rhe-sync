package br.rs.gov.defensoria.rhe.service

import br.rs.gov.defensoria.rhe.entity.jpa.Cargo
import br.rs.gov.defensoria.rhe.repository.CargoRepo
import br.rs.gov.defensoria.rhe.utils.FileUtils
import jakarta.inject.Inject
import jakarta.inject.Singleton
import jakarta.transaction.Transactional


@Singleton
open class ImportCargo {

    @Inject
    lateinit var cargoRepo: CargoRepo

    @Inject
    lateinit var fileUtils: FileUtils

    @Transactional
    open fun importData(fileName: String): String {
        var message: String
        try {
            val res = fileUtils.parseFileCsv(fileName)
            val dataInsert: MutableList<Cargo> = mutableListOf()
            res.forEach {

                        var data = Cargo(
                            COD_CARGO_FUNCAO = it["COD_CARGO_FUNCAO"]?.toInt() ?: 0,
                            NOME_CARGO_FUNCAO = it["NOME_CARGO_FUNCAO"],
                            TIPO_CARGO = it["TIPO_CARGO"],
                            CARGO_FUNCAO = it["CARGO_FUNCAO"],
                            CATEGORIA = it["CATEGORIA"],
                            SUBCATEGORIA = it["SUBCATEGORIA"],
                            dtExtincao = fileUtils.analyzeAndCorrectYearOfDate(it["DT_EXTINCAO"])
                        )
                        dataInsert.add(data)

                }
            cargoRepo.updateAll(dataInsert)
            message = "[+] Importação concluída: $fileName "
        } catch (e: Exception) {
            message = "[-] Erro na importação: $fileName >> $e"
        }
        return message
    }

}