package br.rs.gov.defensoria.rhe.service

import br.rs.gov.defensoria.rhe.entity.jpa.Funcional
import br.rs.gov.defensoria.rhe.repository.FuncionalRepo
import br.rs.gov.defensoria.rhe.utils.FileUtils
import jakarta.inject.Inject
import jakarta.inject.Singleton
import jakarta.transaction.Transactional
import java.time.LocalDate
import java.util.*

@Singleton
open class ImportFuncional {

    @Inject
    lateinit var funcionalRepo: FuncionalRepo

    @Inject
    lateinit var fileUtils: FileUtils

    @Transactional
    open fun importData(fileName: String): String {
        var message: String
        try {
            val res = fileUtils.parseFileCsv(fileName)
            val dataInsert: MutableList<Funcional> = mutableListOf()
            res?.forEach {
                val dtnasc = it["DTNASC"] as String
                var data = Funcional(
                        numfunc = it["NUMFUNC"]?.toString()?.toInt() ?: 0,
                        numvinc = it["NUMVINC"]?.toString()?.toInt() ?: 0,
                        CPF = it["CPF"] as String,
                        NOME = it["NOME"] as String,
                        NUMRG = it["NUMRG"] as String,
                        UFRG = it["UFRG"] as String,
                        SEXO = it["SEXO"] as String,
                        DTNASC = LocalDate.parse(analyzeAndCorrectDateOfBirth(dtnasc)),
                        PAI = it["PAI"] as String,
                        MAE = it["MAE"] as String,
                        ESTCIVIL = it["ESTCIVIL"] as String,
                        ESCOLARIDADE = it["ESCOLARIDADE"] as String,
                        SETOR = it["SETOR"] as String,
                        SETOR_LOTACAO = it["SETOR_LOTACAO"] as String,
                        NOMELOGENDER = it["NOMELOGENDER"] as String,
                        TIPOLOGENDER = it["TIPOLOGENDER"] as String,
                        NUMENDER = it["NUMENDER"] as String,
                        BAIRROENDER = it["BAIRROENDER"] as String,
                        CEPENDER = it["CEPENDER"] as String,
                        COMPLENDER = it["COMPLENDER"] as String,
                        UFENDER = it["UFENDER"] as String,
                        CIDADEENDER = it["CIDADEENDER"] as String,
                        TELEFONE = it["TELEFONE"] as String,
                        NUMTEL_CELULAR = it["NUMTEL_CELULAR"] as String,
                        NACIONALIDADE = it["NACIONALIDADE"] as String,
                        DTVAC = it["DTVAC"] as String,
                        DTAPOSENT = it["DTAPOSENT"] as String,
                        TIPOVINC = it["TIPOVINC"] as String,
                        REGIMEJUR = it["REGIMEJUR"] as String,
                        DTEXERC = it["DTEXERC"] as String,
                        DTPOSSE = it["DTPOSSE"] as String,
                        DTNOM = it["DTNOM"] as String,
                        MATRICULA = it["MATRICULA"] as String,
                        email = it["E_MAIL"] as String,
                        BANCO = it["BANCO"] as String,
                        AGENCIA = it["AGENCIA"] as String,
                        CONTA = it["CONTA"] as String,
                        PISPASEP = it["PISPASEP"] as String,
                        COD_CIDNASC = it["COD_CIDNASC"] as String,
                        COD_CIDENDER = it["COD_CIDENDER"] as String,
                        DTINI_CESSAO = it["DTINI_CESSAO"] as String,
                        DTAFAST = it["DTAFAST"] as String,
                        SIT_RHE = it["SIT_RHE"] as String,
                        gsanguineo = it["G_SANGUINEO"] as String,
                        RACA = it["RACA"] as String,
                        DEFICIENTE = it["DEFICIENTE"] as String,
                        TIPODEFIC = it["TIPODEFIC"] as String,
                        OBSERV = it["OBSERV"] as String,
                        ORGAORG = it["ORGAORG"] as String,
                        EXPEDRG = it["EXPEDRG"] as String,
                        NUMTITEL = it["NUMTITEL"] as String,
                        ZONATITEL = it["ZONATITEL"] as String,
                        SECTITEL = it["SECTITEL"] as String,
                        UFTITEL = it["UFTITEL"] as String,
                        NUMDOCMILI = it["NUMDOCMILI"] as String,
                        DOCMILITAR = it["DOCMILITAR"] as String,
                        SERDOCMILI = it["SERDOCMILI"] as String,
                        CATMILI = it["CATMILI"] as String,
                        UFDOCMILI = it["UFDOCMILI"] as String,
                        FORCA = it["FORCA"] as String,
                        CNH = it["CNH"] as String,
                        CATCNH = it["CATCNH"] as String,
                        ORGEXPCNH = it["ORGEXPCNH"] as String,
                        DATAEXPCNH = it["DATAEXPCNH"] as String,
                        UFCNH = it["UFCNH"] as String,
                        VALIDCNH = it["VALIDCNH"] as String,
                        IDENTPROF = it["IDENTPROF"] as String,
                        TIPOIDPROF = it["TIPOIDPROF"] as String,
                        ufIdentProf = it["UF_IDENTPROF"] as String,
                        DTEXPIDENTPROF = it["DTEXPIDENTPROF"] as String
                )
                dataInsert.add(data)
            }
            funcionalRepo.updateAll(dataInsert)
            message = "[+] Importação concluída: $fileName "
        } catch (e: Exception) {
            message = "[-] Erro na importação: $fileName >> $e"
        }
        return message
    }
    private fun analyzeAndCorrectDateOfBirth(date: String, currentYear: String = (Calendar.getInstance().get(Calendar.YEAR) % 100).toString()): String {
        var tmp = date.split("/")
        val yearCorrect: String= if (tmp[2].toInt() > currentYear.toInt()) "19${tmp[2]}" else "20${tmp[2]}"
        return arrayOf(yearCorrect,tmp[1],tmp[0]).joinToString("-")
    }

}