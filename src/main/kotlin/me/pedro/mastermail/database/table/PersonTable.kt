package me.pedro.mastermail.database.table

import oracle.sql.TIMESTAMP
import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.sql.*

object PersonTable : Table(name = "SYSTEM.PESSOAS") {
    val id = integer("ID")
    val name = varchar("NOME",20)
    val date = long("DATA")
    val email = varchar("EMAIL",300)
}