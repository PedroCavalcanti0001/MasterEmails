package me.pedro.mastermail.manager

import com.google.gson.JsonObject
import com.google.gson.JsonParser
import org.apache.commons.io.FileUtils
import java.io.File
import java.nio.file.Paths


class FileManager {

    lateinit var configuration: JsonObject
    lateinit var templateHtml: File
    lateinit var currentLog: JsonObject

    fun init() {
        val rootPath = Paths.get("").toFile().absolutePath
        val rootFolder = File(rootPath)
        val filePath = rootFolder.absolutePath + File.separator
        templateHtml = File(filePath + "template.html")
        val configurationFile = File(filePath + "configuration.json")
        val currentLogFile = File(filePath + "currentLog.json")
        val inputStreamHtmlTemplate = FileManager::class.java.classLoader.getResourceAsStream("template.html")
        val inputStreamConfiguration = FileManager::class.java.classLoader.getResourceAsStream("configuration.json")
        val inputStreamCurrentLog = FileManager::class.java.classLoader.getResourceAsStream("currentLog.json")
        if (!templateHtml.exists()) {
            FileUtils.copyInputStreamToFile(inputStreamHtmlTemplate, templateHtml)
        }
        if (!configurationFile.exists()) {
            FileUtils.copyInputStreamToFile(inputStreamConfiguration, configurationFile)
        }
        if (!currentLogFile.exists()) {
            FileUtils.copyInputStreamToFile(inputStreamCurrentLog, currentLogFile)
        }
        configuration = JsonParser().parse(configurationFile.readText()).asJsonObject
        currentLog = JsonParser().parse(currentLogFile.readText()).asJsonObject

    }
}