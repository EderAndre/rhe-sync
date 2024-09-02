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
import java.util.concurrent.TimeUnit
import java.util.logging.Logger


@Singleton
class Sync(
    @Value("\${worker.jobs.sync.local-dir}") private var localDir: String
) {

    @Inject
    private lateinit var matchImporter: MatchImporter

    private var logger: Logger = Logger.getLogger("rhe-sync")


     @Scheduled(cron = "0 1/1 * * * *")
    fun importaArquivos() {
        val normalizedFiles: File = File("${localDir}${File.separator}normalized")

        normalizedFiles.listFiles().firstOrNull {
            val date: String = LocalDateTime.now().format(DateTimeFormatter.ofPattern("YYYYMMdd_HHmmss"))

           // logger.addHandler(FileHandler())
            val dispatch = matchImporter.dispatchFile("${localDir}${File.separator}normalized" + File.separator + it.name)
            PrintWriter("${localDir}${File.separator}processed${File.separator}log${File.separator}_${date}_${it.nameWithoutExtension}_.log").use { writer ->
                writer.print(dispatch.toString())
            }
          //  logger.info(dispatch.toString())
         //   FileUtils().copyFile("${localDir}${File.separator}normalized${File.separator}${it.name}","${localDir}${File.separator}processed${File.separator}_${date}_${it.name}" )
            val old = File("${localDir}${File.separator}normalized" + File.separator + it.name)
            println("pode ser executado :${old.canExecute()}")
            println("pode ser escrito :${old.canWrite()}")
            println("pode ser lido :${old.canRead()}")
            TimeUnit.SECONDS.sleep(5L)
                    it.renameTo(File("${localDir}${File.separator}processed${File.separator}_${date}_${it.name}"))
            println("tentou apagar :${old.delete()}")
                    return


              // it.delete()

        }

    }
}