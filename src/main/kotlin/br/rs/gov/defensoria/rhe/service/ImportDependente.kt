package br.rs.gov.defensoria.rhe.service

import br.rs.gov.defensoria.rhe.entity.jpa.Dependente
import br.rs.gov.defensoria.rhe.repository.DependenteRepo
import br.rs.gov.defensoria.rhe.utils.FileUtils
import jakarta.inject.Inject
import jakarta.inject.Singleton
import jakarta.transaction.Transactional
import java.text.SimpleDateFormat


@Singleton
open class ImportDependente {

    @Inject
    lateinit var dependenteRepo: DependenteRepo

    @Inject
    lateinit var fileUtils: FileUtils

    @Transactional
    open fun importData(fileName: String): String {
        var message: String
        try {
            val res = fileUtils.parseFileCsv(fileName)
            val dataInsert: MutableList<Dependente> = mutableListOf()
            val  format = SimpleDateFormat("dd/MM/yyyy")
            res.forEach {

                    var data = Dependente(
                        idFuncional = it["IdFuncional"]?.toInt() ?: 0,
                        nrDependente = it["NrDependente"]?.toInt() ?: 0,
                        idFuncionalDependente = it["IdFuncionalDependente"],
                        nrPens = it["NrPens"],
                        nrcpf = it["NrCPF"],
                        noDependente = it["NoDependente"],
                        noParentesco = it["NoParentesco"],
                        dtNascimento = fileUtils.analyzeAndCorrectYearOfDate(it["DtNascimento"]),
                        dtInicio = fileUtils.analyzeAndCorrectYearOfDate(it["DtInicio"]),
                        dtTermino = fileUtils.analyzeAndCorrectYearOfDate(it["DtTermino"]),
                        noEstadoCivil = it["NoEstadoCivil"],
                        fgEstudante = it["FgEstudante"],
                        fgUniversitario = it["FgUniversitario"],
                        fgInvalido = it["FgInvalido"],
                        fgExcepcional = it["FgExcepcional"],
                        noTipoInvalidez = it["NoTipoInvalidez"],
                        noTipoExcepcional = it["NoTipoExcepcional"],
                        nrRg = it["NrRg"],
                        tpRg = it["TpRg"],
                        deOrgaoRg = it["DeOrgaoRg"],
                        cdUfRg = it["CdUfRg"],
                        dtExpedicaoRg = it["DtExpedicaoRg"],
                        tpDocCertnc = it["TpDocCertNC"],
                        nrCertnc = it["NrCertNC"],
                        nrLivroCertnc = it["NrLivroCertNC"],
                        nrFolhaCertnc = it["NrFolhaCertNC"],
                        nrCartorioCertnc = it["NrCartorioCertNC"],
                        cdUfCertnc = it["CdUfCertNC"],
                        cdMunicipioCertnc = it["CdMunicipioCertNC"],
                        nrMatriculaCertnc = it["NrMatriculaCertNC"],
                        tpDocCertod = it["TpDocCertOD"],
                        dtCertod = it["DtCertOD"],
                        nrCertod = it["NrCertOD"],
                        nrLivroCertod = it["NrLivroCertOD"],
                        nrFolhaCertod = it["NrFolhaCertOD"],
                        nrCartoriioCertod = it["NrCartoriioCertOD"],
                        cdUfCertod = it["CdUfCertOD"],
                        cdMunicipioCertod = it["CdMunicipioCertOD"],
                        ndMatriculaCertod = it["NdMatriculaCertOD"],
                        regraAlimentado = it["RegraAlimentado"]


                    )
                    dataInsert.add(data)
                }
            dependenteRepo.updateAll(dataInsert)
            message = "[+] Importação concluída: $fileName "
        } catch (e: Exception) {
            message = "[-] Erro na importação: $fileName >> $e"
        }
        return message
    }

}