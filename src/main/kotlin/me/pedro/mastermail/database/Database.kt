package me.pedro.mastermail.database

import me.pedro.mastermail.database.table.PersonTable
import me.pedro.mastermail.model.Person
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.transaction

class Database {

    fun getPersons(): List<Person> {
        return transaction {
            PersonTable
                .selectAll()
                .map {
                    Person(
                        id = it[PersonTable.id],
                        name = it[PersonTable.name],
                        email = it[PersonTable.email],
                        date = it[PersonTable.date] ,
                    )
                }
        }
    }
}