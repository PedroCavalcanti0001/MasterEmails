package me.pedro.mastermail.database

import me.pedro.mastermail.fileManager
import org.jetbrains.exposed.sql.Database

class Connection {

    companion object{
        fun connect(): Database {
            val obj = fileManager.configuration.getAsJsonObject("database")
            val host = obj.get("host").asString
            val port = obj.get("port").asString
            val user = obj.get("user").asString
            val sid = obj.get("sid").asString
            val password = obj.get("password").asString
            return Database.connect("jdbc:oracle:thin:@//$host:$port/$sid", driver = "oracle.jdbc.OracleDriver",
                user = user, password = password)
        }
    }
}