package me.pedro.mastermail

import me.pedro.mastermail.manager.FileManager
import me.pedro.mastermail.manager.MailerManager
import org.slf4j.Logger
import utils.SendMail
import utils.SimpleAuth


private lateinit var sendMail: SendMail
private lateinit var fileManager: FileManager
private lateinit var mailerManager: MailerManager


fun main() {
    println("INICIANDO MASTERMAILER!")
    fileManager = FileManager()
    fileManager.init()

    val server = fileManager.configuration.getAsJsonObject("email").get("server").asString
    val port = fileManager.configuration.getAsJsonObject("email").get("port").asString
    val protocol = fileManager.configuration.getAsJsonObject("email").get("protocol").asString
    val starttls = fileManager.configuration.getAsJsonObject("email").get("starttls").asBoolean
    val email = fileManager.configuration.getAsJsonObject("email").get("email").asString
    val password = fileManager.configuration.getAsJsonObject("email").get("password").asString
    sendMail = SendMail(server, port, protocol, starttls)
    val simpleAuth = SimpleAuth(email, password)

    sendMail.send("pedroeugeniocavalcanti@gmail.com", "teste", fileManager.templateHtml.readText(), simpleAuth)


}