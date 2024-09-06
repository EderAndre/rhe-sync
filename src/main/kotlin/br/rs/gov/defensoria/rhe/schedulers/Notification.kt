package br.rs.gov.defensoria.rhe.schedulers

import io.micronaut.context.annotation.Value
import io.micronaut.email.Email
import io.micronaut.email.EmailSender
import io.micronaut.scheduling.annotation.Scheduled
import jakarta.inject.Singleton
import jakarta.mail.Message
import java.io.File
import java.io.PrintWriter
import java.time.LocalDateTime
import java.time.ZoneOffset
import java.time.format.DateTimeFormatter


@Singleton
class Notification(
    @Value("\${worker.jobs.sync.local-dir}") private var localDir: String,
    @Value("\${worker.jobs.sync.notification-dir}") private var notificationDir: String,
    @Value("\${worker.jobs.sync.log-dir}") private var logDir: String,
    @Value("\${worker.jobs.notification.email-from}") private var emailFrom: String,
    @Value("\${worker.jobs.notification.email-to}") private var emailTo: String,
    @Value("\${worker.jobs.notification.email-subject}") private var emailSubject: String,
    @Value("\${worker.jobs.notification.email-var-status}") private var varStatus: String,
    private val emailSender: EmailSender<Message, *>
) {
    @Scheduled(cron = "0 1/1 * * * *")
    fun submitNotification() {
        println("**************************INICIANDO Formatação de email****************************************")
        val date: String = LocalDateTime.now().format(DateTimeFormatter.ofPattern("YYYYMMdd_HHmmss"))
        val yesterday = LocalDateTime.now().minusDays(1).dayOfMonth
        val year = LocalDateTime.now().minusDays(1).year
        val month = LocalDateTime.now().minusDays(1).month
        val logFiles: List<String> = File("${localDir}${logDir}").listFiles().filter {
            it.lastModified() > (LocalDateTime.of(year, month, yesterday, 23, 0)
                .toEpochSecond(ZoneOffset.of(ZoneOffset.UTC.id)) * 1000)
        }.map { it.absolutePath }
        var status = "SUCESS"
        val listStatus = mutableListOf<String>()
        var text = ""
        logFiles.forEach {
            val logText = File(it).readText()
            listStatus.add(logText.substring(0, 3))
            text = "${text}\n$logText}"

        }

        PrintWriter("${localDir}${notificationDir}_${date}_notification_.txt").use { writer ->
            writer.print(text)
        }
        if (listStatus.contains("[-]")) status = "ERROR"
        if (listStatus.contains("[-]") && listStatus.contains("[+]")) status = "PARTIAL"
        val email = Email.builder()
            .from(emailFrom)
            .to(emailTo)
            .subject(emailSubject.replace(varStatus, status))
            .body(text)
        emailSender.send(email)
    }
}