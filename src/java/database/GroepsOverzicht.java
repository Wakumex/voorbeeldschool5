/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
/**
 *
 * @author Ton van Beuningen
 */
public class GroepsOverzicht {
ArrayList lijst;

    public GroepsOverzicht() {
        String selectQuery = "select groepscode from groep order by groepscode";
         Connection con;
        DatabaseConnection dbc;
        ResultSet rs;
        String groep;
        
        con = new Connection();
        if(con.openConnection() == -1) {
            return;
        }
        dbc = con.getConnection();
        if(dbc.sendQuery(selectQuery) == -1) {
            return;
        }
        
        rs = dbc.getResultSet();
        lijst = new ArrayList<String>();
        try {
            rs.beforeFirst();
            while(rs.next()) {
                groep = rs.getString("groepscode");
                lijst.add(groep);
            }
        }
        catch(SQLException se) {
        }
        catch(NullPointerException ne) {
        }
        con.closeConnection(); 
    }
    
    public String getGroep(int index) {
        return (String)lijst.get(index);
    }
    
    public int getNumberOfGroeps() {
        return lijst.size();
    }
}
