package me.pedro.mastermail

import utils.SendMail

fun main(args: Array<String>) {
    println("Hello World!")


    SendMail().sendMail("pedroeugeniocavalcanti@gmail.com",
        "pedroeugeniocavalcanti@gmail.com",
    "teste",
    "teste")
}