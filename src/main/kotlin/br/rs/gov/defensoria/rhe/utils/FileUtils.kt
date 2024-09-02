package br.rs.gov.defensoria.rhe.utils


import jakarta.inject.Singleton
import org.apache.commons.csv.CSVFormat
import org.apache.commons.csv.CSVParser
import java.io.File
import java.io.FileReader
import java.io.IOException
import java.io.PrintWriter
import java.nio.charset.Charset
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.ZoneId


@Singleton
class FileUtils {

    fun parseFileCsv(uri: String): List<Map<String, String>> {
        val file = FileReader(uri)
        val data = CSVParser(file, CSVFormat.DEFAULT.withDelimiter(';').withFirstRecordAsHeader())
        return data.map { it.toMap() }
    }

    fun normalizeFileTxt(uri: String, limitColumns: List<Int>, headerLine: List<String>): String {
        val file = FileReader(uri,Charset.forName("windows-1252"))
        val list = mutableListOf<String>()
        list.add(headerLine.joinToString(";"))
        file.forEachLine { line ->
            var modifiedLine = line.replace("\"", "-").replace("////", "-")
            val temp = mutableListOf<String>()
            var inicio = 0
            for (it in limitColumns) {
                temp.add("\"${modifiedLine.substring(inicio, it).trim()}\"")
                inicio = it
            }
            list.add(temp.joinToString(";"))
        }
        return list.joinToString("\n")
    }

    fun normalizeFileCsv(uri: String): String {
        val file = FileReader(uri,Charset.forName("windows-1252"))
        val list = mutableListOf<String>()
        file.forEachLine { line ->
            val temp = mutableListOf<String>()
            temp.add(line.replace("\"", ""))
            list.add(temp.joinToString(";"))
        }
        return list.joinToString("\n")
    }

    fun createFile(filename: String, content: String): String {
        return try {
            PrintWriter(filename).use { writer ->
                writer.print(content)
            }
            "$filename criado com sucesso."
        } catch (e: IOException) {
            "$filename não pode ser criado: $e"
        }
    }

    fun copyFile(uriOrigin: String, uriDestiny: String): String {

        return try {

           val text = File(uriOrigin).readText()
            File(uriDestiny).writeText(text)
            "$uriOrigin - cópia bem sucedida"
        } catch (e: IOException) {
            "Ocorreu um erro ao copiar: $e"
        }
    }

    fun createDirectory(uriToNewDirectory: String): Boolean {
        val folder = File(uriToNewDirectory)
        return if (!folder.exists()) {
            folder.mkdirs()
        } else {
            println("A pasta já existe")
            false
        }
    }

    fun createDirectoryToday(uriToNewDirectory: String): Boolean {
        val format = SimpleDateFormat("yyyy-MM-dd")
        val today = format.format(java.util.Date())
        return createDirectory("$uriToNewDirectory${File.separator}$today")
    }

    fun analyzeAndCorrectYearOfDate(dateParseable: String?): LocalDate? {
        if(dateParseable == "") return null
        val format2Y = SimpleDateFormat("dd/MM/yy")
        val format4Y = SimpleDateFormat("dd/MM/yyyy")
        var correctDate: java.util.Date? = null

        try {
            correctDate = dateParseable?.let { format4Y.parse(it) }
            if (correctDate == null) {
                correctDate = dateParseable?.let { format2Y.parse(it) }
            }
        } catch (pe: Exception) {
            println("Data Inválida$pe")
        }
        return correctDate?.toInstant()?.atZone(ZoneId.systemDefault())?.toLocalDate()
    }

}

