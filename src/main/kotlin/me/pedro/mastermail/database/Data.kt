package me.pedro.mastermail.database

import me.pedro.mastermail.model.Person
import java.lang.Exception

class Data {


    /*
    Statement stmt=con.createStatement();

//step4 execute query
    ResultSet rs=stmt.executeQuery("select * from emp");
    while(rs.next())
    System.out.println(rs.getInt(1)+"  "+rs.getString(2)+"  "+rs.getString(3));

//step5 close the connection object
    con.close();

}catch(Exception e){ System.out.println(e);}

}
     */

    fun getPersons():List<Pair<Person,Int>>{
        val con = Connection.open()
        val list = arrayListOf<Pair<Person,Int>>()
        if(con != null) {
            try {
                val stmt = con.createStatement()
                val rs = stmt.executeQuery("select * from emp")
                while(rs.next()){
                    //TO DO - pegar as propriedades do usuário aqui e pá
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
        return list
    }
}