package me.pedro.mastermail.database

import java.sql.Connection
import java.sql.DriverManager

class Connection {

    companion object {
        fun open(): Connection? {
            try {
                Class.forName("oracle.jdbc.driver.OracleDriver");
                val con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "oracle")
                return con
            } catch (e: Exception) {
                e.printStackTrace()
            }
            return null
        }
    }

}