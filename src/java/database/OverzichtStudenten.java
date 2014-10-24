
package database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
/**
 * De klasse <strong>OverzichtStudenten</strong> haalt de gegevens 
 * van de studenten op uit de database en gebruikt de klasse 
 * <strong>Student</strong> om daarin de gegevens per student
 * op te slaan. De klassen <strong>Student</strong> worden in 
 * een <strong>ArrayList</strong> opgeslagen.
 * @author Ton van Beuningen
 * @version 24-09-2008
 */
public class OverzichtStudenten {
    ArrayList lijst;
    
    /**
     * Deze constructor maakt een lege lijst aan waarin
     * de klassen <strong>Student</strong> kunnen worden
     * opgeslagen.
     */
    public OverzichtStudenten() {
        lijst = new ArrayList<Student>();
    }
    
    /**
     * Deze methode haalt alle studenten op uit 
     * de database School. Hij maakt daarbij gebruik
     * van de methode <strong>getData(String selectQuery)
     * </strong>.
     * @param sort 0 = niet gesorteerd 1 = gesorteerd op
     * achternaam, tussenvoegsels en roepnaam
     * @return 0 als alles goed is gegaan, -1 als
     * er een exceptie is opgetreden
     */
    public int getStudentsSorted(int sort) {
        String selectQuery = "select ovnummer from student";
       
        if(sort == 1) {
            selectQuery += " order by achternaam, tussenvoegsels, roepnaam";
        }
        return getData(selectQuery);
    }
    
    /**
     * Deze methode haalt alle studenten op uit 
     * de database School op basis van het zoekargument.
     * Hij maakt daarbij gebruik van de methode 
     * <strong>getData(String selectQuery)</strong>.
     * @param zoekArg Gezocht wordt naar een lettercombinatie in
     * achternaam, tussenvoegsels of roepnaam
     * @return 0 als alles goed is gegaan, -1 als
     * er een exceptie is opgetreden
     */
    public int getStudentsSearched(String zoekArg) {
        String selectQuery = "select ovnummer from student where achternaam like '%"; 
       
        selectQuery += zoekArg + "%' or tussenvoegsels like '%" + zoekArg +
                "%' or roepnaam like '%" + zoekArg + "%'";
        return getData(selectQuery);
    }
    
    /**
     * Deze methode haalt alle studenten op uit 
     * de database School die in een bepaalde groep
     * zitten.
     * Hij maakt daarbij gebruik van de methode 
     * <strong>getData(String selectQuery)</strong>.
     * @param groep Gezocht wordt naar de studenten in
     * een bepaalde groep.
     * @return 0 als alles goed is gegaan, -1 als
     * er een exceptie is opgetreden
     */
    public int getStudentsPerGroup(String groep) {
        String selectQuery = "select student.ovnummer from student, studentgroep where " +
                "student.ovnummer = studentgroep.ovnummer and studentgroep.groepscode = '";
        
        selectQuery += (groep + "' order by achternaam, tussenvoegsels, roepnaam");
        return getData(selectQuery);
    }
    
    /**
     * Deze methode wordt aangeroepen door de
     * methodes getStudentsSorted(int sort),
     * getStudentsSearched(String zoekArg) en 
     * getStudentsPerGroup(String groep) en stuurt
     * de query naar de database, haalt de <strong>
     * ResultSet</strong> op uit de database en stelt
     * aan de hand daarvan de lijst met de klassen
     * <strong>Student</strong> samen. 
     * @param selectQuery De query die naar de database
     * wordt gestuurd.
     * @return 0 als alles goed is gegaan, -1 als
     * er een exceptie is opgetreden
     */
    protected int getData(String selectQuery) {
        Connection con;
        DatabaseConnection dbc;
        ResultSet rs;
        Student student;
        
         con = new Connection();
        if(con.openConnection() == -1) {
            return -1;
        }
        dbc = con.getConnection();
        
        if(dbc.sendQuery(selectQuery) == -1) {
            return -1;
        }
        rs = dbc.getResultSet();
        lijst = new ArrayList<Student>();
        try {
            rs.beforeFirst();
            while(rs.next()) {
                student = new Student(rs.getString("ovnummer"));
                lijst.add(student);
            }
        }
        catch(SQLException se) {
            return -1;
        }
        catch(NullPointerException ne) {
            return -1;
        }
        finally {
            con.closeConnection(); 
        }
        return 0;
    }
    
    /**
     * Deze methode haalt een van de
     * klassen <strong>Student</strong> op
     * uit de lijst op basis van de index.
     * @param index Een integer die de index
     * is van de klasse <strong>Student</strong>
     * uit de lijst die opgehaald moet worden.
     * @return
     */
    public Student getStudent(int index) {
        return (Student)lijst.get(index);
    }
    
    /**
     * Deze methode geeft het aantal klassen
     * <strong>Student</strong> die op dat moment
     * in de lijst staan.
     * @return Het aantal klassen <strong>Student
     * </strong> in de lijst.
     */
    public int getNumberOfStudents() {
        return lijst.size();
    }
}