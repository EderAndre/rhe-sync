package br.rs.gov.defensoria.rhe.service

import br.rs.gov.defensoria.rhe.entity.jpa.SetorHistorico
import br.rs.gov.defensoria.rhe.repository.SetorHistoricoRepo
import br.rs.gov.defensoria.rhe.utils.FileUtils
import jakarta.inject.Inject
import jakarta.inject.Singleton
import jakarta.transaction.Transactional


@Singleton
open class ImportSetorHistorico {

    @Inject
    lateinit var setorHistoricoRepo: SetorHistoricoRepo

    @Inject
    lateinit var fileUtils: FileUtils

    @Transactional
    open fun importData(fileName: String): String {
        var message: String
        try {
            val res = fileUtils.parseFileCsv(fileName)
            val dataInsert: MutableList<SetorHistorico> = mutableListOf()
            res.forEach {

                        var data = SetorHistorico(
                            emp_codigo = it["EMP_CODIGO"],
                            setor = it["SETOR"],
                            PAISETOR = it["PAISETOR"],
                            NOMESETOR = it["NOMESETOR"],
                            NOMESETORLONGO = it["NOMESETORLONGO"],
                            DATAINI = fileUtils.analyzeAndCorrectYearOfDate(it["DATAINI"]),
                            SETOR_CORP = it["SETOR_CORP"],
                            HLOCAL = it["HLOCAL"],
                            TIPOSETOR = it["TIPOSETOR"],
                            TIPOLOG = it["TIPOLOG"],
                            NOMELOG = it["NOMELOG"],
                            NUMEROLOG = it["NUMEROLOG"],
                            COMPLEMENTO = it["COMPLEMENTO"],
                            BAIRRO = it["BAIRRO"],
                            CEP = it["CEP"],
                            CIDADE = it["CIDADE"],
                            UF = it["UF"],
                            FONE = it["FONE"],
                            FAX = it["FAX"],
                            ORGANIZACAO = it["ORGANIZACAO"],
                            SIGLA = it["SIGLA"],
                            DATAFIM = fileUtils.analyzeAndCorrectYearOfDate(it["DATAFIM"]),
                            EXTINTO = it["EXTINTO"]=="S",
                            EMAIL = it["EMAIL"],
                        )
                        dataInsert.add(data)

                }
            setorHistoricoRepo.updateAll(dataInsert)
            message = "[+] Importação concluída: $fileName "
        } catch (e: Exception) {
            message = "[-] Erro na importação: $fileName >> $e"
        }
        return message
    }

}