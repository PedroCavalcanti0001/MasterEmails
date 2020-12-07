package me.pedro.mastermail

import me.pedro.mastermail.database.Connection
import me.pedro.mastermail.database.table.PersonTable
import me.pedro.mastermail.manager.FileManager
import me.pedro.mastermail.manager.MailerManager
import me.pedro.mastermail.model.Person
import me.pedro.mastermail.model.dateLocalAsDateTime
import org.jetbrains.exposed.sql.Expression
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.transaction
import utils.SendMail

lateinit var sendMail: SendMail
lateinit var fileManager: FileManager
lateinit var mailerManager: MailerManager
lateinit var database: me.pedro.mastermail.database.Database


fun main() {
    println("INICIANDO MASTERMAILER!")
    fileManager = FileManager()
    fileManager.init()
    database = me.pedro.mastermail.database.Database()
    //database.getPersons()

    Connection.connect()
    database.getPersons().forEach {
        println(it)
        println(it.dateLocalAsDateTime())

    }


    /*




    val server = fileManager.configuration.getAsJsonObject("email").get("server").asString
    val port = fileManager.configuration.getAsJsonObject("email").get("port").asString
    val protocol = fileManager.configuration.getAsJsonObject("email").get("protocol").asString
    val starttls = fileManager.configuration.getAsJsonObject("email").get("starttls").asBoolean
    val email = fileManager.configuration.getAsJsonObject("email").get("email").asString
    val password = fileManager.configuration.getAsJsonObject("email").get("password").asString


    if(email.isEmpty() || password.isEmpty()){
        println("AVISO: Um arquivo de configuração 'configuration.json' foi gerado, coloque as propriedades do seu email nele!")
        exitProcess(-1)
    }

    sendMail = SendMail(server, port, protocol, starttls)
    val simpleAuth = SimpleAuth(email, password)

    val to = "pedroeugeniocavalcanti@gmail.com"

    val teste = sendMail.send(to, "teste", fileManager.templateHtml.readText(), simpleAuth)

    teste.thenAccept {
        println("")
        println("Email enviado com sucesso para $to")
    }
     */


}