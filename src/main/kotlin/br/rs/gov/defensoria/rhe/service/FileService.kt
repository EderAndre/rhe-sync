package br.rs.gov.defensoria.rhe.service


import br.rs.gov.defensoria.rhe.utils.FileUtils
import br.rs.gov.defensoria.rhe.utils.Type
import br.rs.gov.defensoria.rhe.utils.TypesDefinition
import io.micronaut.context.annotation.Value
import jakarta.inject.Singleton
import java.io.File

@Singleton
class FilesService(
    @Value("\${worker.jobs.sync.normalized-dir}") private var normalDir: String,
    @Value("\${worker.jobs.sync.normalized-waiting-dir}") private var normalWaitingDir: String
) {
    private val type = TypesDefinition()

    fun detectType(fileName: String): Type? {
        return when {
            Regex(".*${type.AFASTAMENTO.expressionFromDetection}.*").containsMatchIn(fileName) -> type.AFASTAMENTO
            Regex(".*${type.DIRETORES_REGIONAIS.expressionFromDetection}.*").containsMatchIn(fileName) -> type.DIRETORES_REGIONAIS
            Regex(".*${type.CARGOS.expressionFromDetection}.*").containsMatchIn(fileName) -> type.CARGOS
            Regex(".*${type.DADOS_PUBLICACAO.expressionFromDetection}.*").containsMatchIn(fileName) -> type.DADOS_PUBLICACAO
            Regex(".*${type.EVENTO.expressionFromDetection}.*").containsMatchIn(fileName) -> type.EVENTO
            Regex(".*${type.FUNCIONAL.expressionFromDetection}.*").containsMatchIn(fileName) -> type.FUNCIONAL
            Regex(".*${type.MUNICIPIOS.expressionFromDetection}.*").containsMatchIn(fileName) -> type.MUNICIPIOS
            Regex(".*${type.PAIS.expressionFromDetection}.*").containsMatchIn(fileName) -> type.PAIS
            Regex(".*${type.SETOR.expressionFromDetection}.*").containsMatchIn(fileName) -> type.SETOR
            Regex(".*${type.SETOR_HISTORICO.expressionFromDetection}.*").containsMatchIn(fileName) -> type.SETOR_HISTORICO
            Regex(".*${type.SUBCATEGORIA.expressionFromDetection}.*").containsMatchIn(fileName) -> type.SUBCATEGORIA
            Regex(".*${type.TIPOEVENTOS.expressionFromDetection}.*").containsMatchIn(fileName) -> type.TIPOEVENTOS
            Regex(".*${type.TRINOMIOS.expressionFromDetection}.*").containsMatchIn(fileName) -> type.TRINOMIOS
            Regex(".*${type.VINCULO.expressionFromDetection}.*").containsMatchIn(fileName) -> type.VINCULO
            Regex(".*${type.FERIAS.expressionFromDetection}.*").containsMatchIn(fileName) -> type.FERIAS
            Regex(".*${type.FERIAS_PENDENTES.expressionFromDetection}.*").containsMatchIn(fileName) -> type.FERIAS_PENDENTES
            Regex(".*${type.LICENCA_PREMIO.expressionFromDetection}.*").containsMatchIn(fileName) -> type.LICENCA_PREMIO
            Regex(".*${type.LICENCA_PREMIO_PENDENTE.expressionFromDetection}.*").containsMatchIn(fileName) -> type.LICENCA_PREMIO_PENDENTE
            Regex(".*${type.FERIAS_FULL.expressionFromDetection}.*").containsMatchIn(fileName) -> type.FERIAS_FULL
            Regex(".*${type.CAPACITACAO.expressionFromDetection}.*").containsMatchIn(fileName) -> type.CAPACITACAO
            Regex(".*${type.EVENT_DELETED.expressionFromDetection}.*").containsMatchIn(fileName) -> type.EVENT_DELETED
            Regex(".*${type.FORMACOES.expressionFromDetection}.*").containsMatchIn(fileName) -> type.FORMACOES
            Regex(".*${type.DEPENDENTES.expressionFromDetection}.*").containsMatchIn(fileName) -> type.DEPENDENTES
            Regex(".*${type.DEPENDENCIAS.expressionFromDetection}.*").containsMatchIn(fileName) -> type.DEPENDENCIAS
            else -> null
        }
    }

    fun generateFileNormalized(fileOrigin: String, fileDestiny: String, typeSelected: Type) {
        if (typeSelected.extension == "TXT") {
            generateCsvFromTxtNormalized(fileOrigin, fileDestiny, typeSelected)
        } else if (typeSelected.extension == "CSV") {
            generateCsvFromCsvNormalized(fileOrigin, fileDestiny)
        }
    }

    private fun generateCsvFromTxtNormalized(fileOrigin: String, fileDestiny: String, typeSelected: Type): Boolean {
        var updatedFileDestiny = fileDestiny.replace(".TXT", "_NORMALIZED.CSV")
        val res = FileUtils().normalizeFileTxt(
            fileOrigin,
            typeSelected.columnLimits!!.toList(),
            typeSelected.columnLabels!!.toList()
        )
        copyNormalized(updatedFileDestiny, res)
        return FileUtils().createFile(updatedFileDestiny, res).isNotEmpty()
    }

    private fun generateCsvFromCsvNormalized(fileOrigin: String, fileDestiny: String): Boolean {
        val updatedFileDestiny = fileDestiny.replace(".CSV", "_NORMALIZED.CSV")
        val res = FileUtils().normalizeFileCsv(fileOrigin)
        copyNormalized(updatedFileDestiny, res)
        return FileUtils().createFile(updatedFileDestiny, res).isNotEmpty()
    }

    private fun copyNormalized(normalizedFileDestiny: String, content: String) {
        val updatedFileDestiny =
            normalizedFileDestiny.replace(
                "${File.separator}${normalDir}",
                "${File.separator}${normalWaitingDir}"
            )
        FileUtils().createFile(updatedFileDestiny, content)
    }
}

