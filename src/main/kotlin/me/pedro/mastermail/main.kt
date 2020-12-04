package me.pedro.mastermail

import me.pedro.mastermail.manager.MailerManager
import utils.SendMail
import java.io.File
import java.nio.file.Files
import java.nio.file.Paths

fun main(args: Array<String>) {
    println("Hello World!")
   /* val templateFile = Paths.get("src${File.separator}main${File.separator}resources${File.separator}template.html").toFile()
    val serializedTemplate = templateFile.readText().replace("{name}", "Pedro eugenio")
    SendMail().sendMail("testes@gmail.com", "pedroeugeniocavalcanti@gmail.com","teste",serializedTemplate)
    */

    MailerManager().makeConfigurationFiles()
}