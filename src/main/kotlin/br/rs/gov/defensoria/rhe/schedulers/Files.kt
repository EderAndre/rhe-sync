package br.rs.gov.defensoria.rhe.schedulers

import br.rs.gov.defensoria.rhe.service.FilesService
import io.micronaut.context.annotation.Value
import jakarta.inject.Inject
import jakarta.inject.Singleton
import java.io.File
import java.time.LocalDateTime
import java.time.ZoneOffset
import java.time.format.DateTimeFormatter


@Singleton
class Files(
    @Value("\${worker.jobs.sync.local-dir}") private var localDir: String
) {
    @Inject
    private lateinit var filesSv: FilesService

   // @Scheduled(cron = "0 1/1 * * * *")
    fun syncFiles() {
        println(
            "**************************INICIANDO DETECÇÃO DE ARQUIVOS NOVOS****************************************"
        )
        val syncFiles: File = File("${localDir}${File.separator}rsync")
        val sendedFiles: List<String> = File("${localDir}${File.separator}sended").listFiles().map { it.name }
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
                println(
                    "${it.lastModified()} +++++++++${
                        LocalDateTime.of(year, month, yesterday, 23, 0).toInstant(ZoneOffset.UTC).epochSecond * 1000
                    }"
                )
                it.copyTo(File("${localDir}${File.separator}sended${File.separator}${it.name}"))
                it.copyTo(File("${localDir}${File.separator}waiting${File.separator}${it.name}"))
            }
        }
    }

    // @Scheduled(cron = "0 1/1 * * * *")
    fun normalizaArquivos() {
        val waitingFiles: File = File("${localDir}${File.separator}waiting")
        val date: String = LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE)
        waitingFiles.listFiles().forEach {

            val type = filesSv.detectType(it.name)

            if (type != null) {
                filesSv.generateFileNormalized(
                    "${localDir}${File.separator}waiting" + File.separator + it.name,
                    "${localDir}${File.separator}normalized" + File.separator + it.name,
                    type
                )
                it.delete()
                println(it.name)

            } else {
                it.copyTo(
                    File(
                        "${localDir}${File.separator}aborted${File.separator}_${
                            LocalDateTime.now().format(DateTimeFormatter.ofPattern("YYYYMMdd_HHmmss"))
                        }_${it.name}"
                    )
                )
                it.delete()
            }
        }
    }
}