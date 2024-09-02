package br.rs.gov.defensoria.rhe.service

import br.rs.gov.defensoria.rhe.entity.jpa.Dependencia
import br.rs.gov.defensoria.rhe.repository.DependenciaRepo
import br.rs.gov.defensoria.rhe.utils.FileUtils
import jakarta.inject.Inject
import jakarta.inject.Singleton
import jakarta.transaction.Transactional
import java.text.SimpleDateFormat


@Singleton
open class ImportDependencia {

    @Inject
    lateinit var dependenciaRepo: DependenciaRepo

    @Inject
    lateinit var fileUtils: FileUtils

    @Transactional
    open fun importData(fileName: String): String {
        var message: String
        try {
            val res = fileUtils.parseFileCsv(fileName)
            val dataInsert: MutableList<Dependencia> = mutableListOf()
            val  format = SimpleDateFormat("dd/MM/yyyy")
            res.forEach {

                    var data = Dependencia(
                        idFuncional = it["IdFuncional"]?.toInt() ?: 0,
                        nrDependente = it["NrDependente"]?.toInt() ?: 0,
                        dtInicio = if(it["DtInicio"] != "") format.parse(it["DtInicio"]) else null,
                        dtTermino = fileUtils.analyzeAndCorrectYearOfDate(it["DtTermido"]),
                        noTipoDependencia = it["NoTipoDependencia"]

                    )
                    dataInsert.add(data)
                }
            dependenciaRepo.updateAll(dataInsert)
            message = "[+] Importação concluída: $fileName "
        } catch (e: Exception) {
            message = "[-] Erro na importação: $fileName >> $e"
        }
        return message
    }

}