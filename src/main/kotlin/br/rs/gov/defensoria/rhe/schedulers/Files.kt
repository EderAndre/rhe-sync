package br.rs.gov.defensoria.rhe.schedulers

import br.rs.gov.defensoria.rhe.service.FilesService
import io.micronaut.context.annotation.Value
import io.micronaut.scheduling.annotation.Scheduled
import jakarta.inject.Inject
import jakarta.inject.Singleton
import java.io.File
import java.time.LocalDateTime
import java.time.ZoneOffset
import java.time.format.DateTimeFormatter


@Singleton
class Files(
    @Value("\${worker.jobs.sync.local-files-dir}") private var localDir: String,
    @Value("\${worker.jobs.sync.sended-dir}") private var sendedDir: String,
    @Value("\${worker.jobs.sync.rsync-dir}") private var rsyncDir: String,
    @Value("\${worker.jobs.sync.waiting-dir}") private var waitingDir: String,
    @Value("\${worker.jobs.sync.normalized-dir}") private var normalDir: String,
    @Value("\${worker.jobs.sync.aborted-dir}") private var abortDir: String,

) {
    @Inject
    private lateinit var filesSv: FilesService

    @Scheduled(cron = "0 1/1 * * * *")
    fun syncFiles() {
        println(
            "**************************INICIANDO DETECÇÃO DE ARQUIVOS NOVOS****************************************"
        )
        val syncFiles: File = File("${localDir}${rsyncDir}")
        val sendedFiles: List<String> = File("${localDir}${sendedDir}").listFiles().map { it.name }
        val date: String = LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE)
        syncFiles.listFiles().forEach {
            val yesterday = LocalDateTime.now().minusDays(1).dayOfMonth
            val year = LocalDateTime.now().minusDays(1).year
            val month = LocalDateTime.now().minusDays(1).month
            if (
                (it.lastModified() > LocalDateTime.of(year, month, yesterday, 23, 0)
                    .toEpochSecond(ZoneOffset.of(ZoneOffset.UTC.id)) * 1000)
                &&
                it.name !in sendedFiles
            ) {
                it.copyTo(File("${localDir}${File.separator}${sendedDir}${it.name}"))
                it.copyTo(File("${localDir}${File.separator}${waitingDir}${it.name}"))
            }
        }
    }

    @Scheduled(fixedDelay = "2s")
    fun normalizaArquivos() {
        println(
            ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>   INICIANDO NORMALIZAÇÃO DE ARQUIVOS    <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<"
        )
        val waitingFiles: File = File("${localDir}${waitingDir}")
        val date: String = LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE)
        waitingFiles.listFiles().forEach {
            println("[+] Trying: ${it.name}")
            val type = filesSv.detectType(it.name)

            if (type != null) {
                filesSv.generateFileNormalized(
                    "${localDir}${sendedDir}${it.name}",
                    "${localDir}${normalDir}${it.name}",
                    type
                )
                it.delete()
                println(it.name)

            } else {
                it.copyTo(
                    File(
                        "${localDir}${abortDir}_${
                            LocalDateTime.now().format(DateTimeFormatter.ofPattern("YYYYMMdd_HHmmss"))
                        }_${it.name}"
                    )
                )
                it.delete()
            }
        }
    }
}