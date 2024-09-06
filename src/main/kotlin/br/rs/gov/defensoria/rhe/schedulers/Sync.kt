package br.rs.gov.defensoria.rhe.schedulers

import br.rs.gov.defensoria.rhe.service.MatchImporter
import io.micronaut.context.annotation.Value
import io.micronaut.scheduling.annotation.Scheduled
import jakarta.inject.Inject
import jakarta.inject.Singleton
import java.io.File
import java.io.PrintWriter
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter


@Singleton
class Sync(
    @Value("\${worker.jobs.sync.local-dir}") private var localDir: String,
    @Value("\${worker.jobs.sync.normalized-dir}") private var normalDir: String,
    @Value("\${worker.jobs.sync.normalized-waiting-dir}") private var normalWaitingDir: String,
    @Value("\${worker.jobs.sync.log-dir}") private var logDir: String
) {

    @Inject
    private lateinit var matchImporter: MatchImporter

    @Scheduled(fixedDelay = "5s")
    fun importaArquivos() {
        println(
            "============================= INICIANDO IMPORTAÇÃO DE ARQUIVOS  ====================================="
        )
        val normalizedFiles: File = File("${localDir}${normalWaitingDir}")

        normalizedFiles.listFiles().firstOrNull {
            val date: String = LocalDateTime.now().format(DateTimeFormatter.ofPattern("YYYYMMdd_HHmmss"))
            println("[+] Trying: ${it.name}")
            val dispatch =
                matchImporter.dispatchFile("${localDir}${normalDir}${it.name}")
            PrintWriter("${localDir}${logDir}_${date}_${it.nameWithoutExtension}_.log").use { writer ->
                writer.print(dispatch.toString())
            }
            it.delete()
            return
        }
    }
}