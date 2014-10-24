package school;

/**
 * Deze klasse verzorgt de gegevens van de student die in de database
 * school staan.
 * 
 * @author Ton van Beuningen
 * @version 23-09-2008
 */

//import database.*;
import java.util.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import database.DatabaseConnection;
//import java.io.File;
/**
 * De klasse student haalt de gegevens van een student uit de tabel student
 * in de database school op, voegt ze toe of wijzigt ze.
 */
public class Student {
    
    String ovnr, roepnaam, voorletters, tussenvoegsels, achternaam;
    String adres, postcode, woonplaats;
    Date uitgeschreven;
    
    /**
     * Een lege constructor waardoor het mogelijk wordt een 
     * student toe te voegen aan de database school.
     */
    public Student() {
    }
    /**
     * Deze constructor opent met behulp van de klasse <strong>Connection</strong>
     * de verbinding met de database school en vraagt de verbinding op. Vervolgens
     * worden de gegevens van de student met het OV-nummer opgevraagd uit de database.
     * @param ovnr Het OV-nummer van de student, van wie de gegevens uit de database
     * worden opgehaald.
     */    
    public Student(String ovnr) {
        String selectQuery = "select roepnaam, voorletters, tussenvoegsels, achternaam, " +
                                "adres, postcode, woonplaats, uitgeschreven from student where ovnummer = '";
        
        
        this.ovnr = ovnr;
        selectQuery += ovnr + "'";
        getData(selectQuery);
    }
    
    /**
     * Deze methode wordt in deze klasse gebruikt om verbinding met de database
     * <strong>school</strong> te maken en gegevens uit de tabel <strong>student</strong>
     * te halen.
     * @return -1 als het maken van de verbinding mislukt, 0 als de verbinding
     * tot stand is gekomen
     */
    protected int getData(String query) {
        Connection con;
        ResultSet rs;
        DatabaseConnection dbc;
         
        
        con = new Connection();
        if(con.openConnection() == -1) {
            return -1;
        }
        dbc = con.getConnection();
        if(dbc.sendQuery(query) == -1) {
            return -1;
        }
        
        rs = dbc.getResultSet();
        try {
            rs.first();
            this.roepnaam = rs.getString("roepnaam");
            this.voorletters = rs.getString("voorletters");
            this.tussenvoegsels = rs.getString("tussenvoegsels");
            this.achternaam = rs.getString("achternaam");
            this.adres = rs.getString("adres");
            this.postcode = rs.getString("postcode");
            this.woonplaats =rs.getString("woonplaats");
            this.uitgeschreven = rs.getDate("uitgeschreven");
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
     * Deze methode wordt in deze klasse gebruikt om verbinding met de database
     * <strong>school</strong> te maken en gegevens naar de tabel <strong>student</strong>
      * te sturen.
     * @return -1 als het maken van de verbinding mislukt, 0 als de verbinding
     * tot stand is gekomen
     */
    protected int setData(String query) {
        Connection con;
        DatabaseConnection dbc;
            
        con = new Connection();
        if(con.openConnection() == -1) {
            return -1;
        }
        dbc = con.getConnection();
        if(dbc.sendQuery(query) == -1) {
            return -1;
        }
        con.closeConnection();
        return 0;
    }
    
    /**
     * Deze methode voegt een foto toe aan de gegevens van een student.
     * @param ovnr Het ov-nummer van de student op de foto
     * @param foto De naam van het bestand met de foto van de student
     */
/*    public int setFotoToevoegen(String ovnr, String foto) {
        String updateQuery = "update student set foto = '";
        
        File file = new File(foto);
        return 0;
    }*/
    /**
     * Deze methode wijzigt de gegevens van een student in de tabel <strong>student
     * </strong> in de database <strong>school</strong>.
     * @return 0 als alles goed is gegaan, -1 als er een fout is opgetreden.
     */
    public int wijzigen() {
        String updateQuery = "update student set roepnaam = '" + this.roepnaam +
                "', voorletters = '" + this.voorletters +
                "', tussenvoegsels = '" + this.tussenvoegsels +
                "', achternaam = '" + this.achternaam +
                "', adres = '" + this.adres +
                "', postcode = '" + this.postcode +
                "', woonplaats = '" + this.woonplaats +
                "' where ovnummer = '" + this.ovnr + "'";
        
        return setData(updateQuery);
    }
    
    /**
     * Deze methode voegt de gegevens van een student aan de tabel <strong>student
     * </strong> in de database <strong>school</strong>toe.
     * @return 0 als alles goed is gegaan, -1 als er een fout is opgetreden.
     */
    public int toevoegen() {
        String insertQuery = "insert into student (ovnummer, roepnaam, voorletters, " +
                "tussenvoegsels, achternaam, adres, postcode, woonplaats) values ('" +
                this.ovnr + "', '" + this.roepnaam + "', '" + this.voorletters + "', '" + 
                this.tussenvoegsels + "', '" + this.achternaam + "', '" + this.adres + "', '" + 
                this.postcode + "', '" + this.woonplaats + "')";
        
        return setData(insertQuery);
    }
    
    /**
     * Deze methode schrijft een student uit door in de tabel <strong>student
     * </strong> in de database <strong>school</strong> een uitschrijfdatum aan 
     * de gegevens van een student toe te voegen of door de uitschrijfdatum te
     * wijzigen.
     * @param datum Een <strong>string</strong> waarin de datum ligt opgegeslagen 
     * in de vorm van <strong>dd-mm-jjjj</strong>
     * @return 0 als alles goed is gegaan, -1 als er een fout is opgetreden.
     */
    public int uitschrijven(String datum) {
        String updateQuery = "update student set uitgeschreven = str_to_date('";
        
        updateQuery += (datum + "', '%d-%m-%Y') where ovnummer = '" + this.ovnr +"'");
        return setData(updateQuery);
    }
    /**
     * Deze methode plaatst een student in een groep of wijzigt de groep van
     * de student. 
     * @param gekozen De groep waarin de student geplaatst wordt.
     * @return 0 als het groeperen is gelukt en -1 als het groeperen niet is
     * gelukt.
     */
    public int groeperen(String gekozen) {
        String query, groep;
        
        groep = getGroep();
        if(groep == null || groep.length() == 0) {
            query = "insert into studentgroep (ovnummer, groepscode) values('" + 
                    this.ovnr + "', '" + gekozen + "')";
        }
        else {
            query = "update studentgroep set groepscode = '" + gekozen + 
                    "' where ovnummer = '" + this.ovnr + "'";
        }
        return setData(query);
    }
    /**
     * Deze methode vraagt in de tabel <strong>studentgroep</strong> in de database
     * <strong>school</strong> op in welke groep een student zit.
     * @return Een String met de groepscode van de groep of een lege string als er
     * geen groep is gevonden.
     */
    public String getGroep() {
        String selectQuery = "select groepscode from studentgroep where ovnummer = '";
        ResultSet rs;
        Connection con;
        DatabaseConnection dbc;
        String groep = "";
        
        selectQuery += (this.ovnr + "'");
        con = new Connection();
        if(con.openConnection() == -1) {
            return "";
        }
        dbc = con.getConnection();
        if(dbc.sendQuery(selectQuery) == -1) {
            return "";
        }
        
        rs = dbc.getResultSet();
        try {
            rs.first();
            groep = rs.getString("groepscode");
        }
        catch(SQLException se) {
            return "";
        }
        catch(NullPointerException ne) {
            return "";
        }
        finally {
            con.closeConnection();
        }
        return groep;
        
    }
    
    /**
     * De achternaam van de student
     * @return De achternaam van de student
     */
    public String getAchternaam() {
        return achternaam;
    }
    
    /**
     * Het adres van de student
     * @return Het adres van de student
     */
    public String getAdres() {
        return adres;
    }

    /**
     * Het OV-nummer van de student
     * @return Het OV-nummer van de student
     */
    public String getOvnr() {
        return ovnr;
    }

    /**
     * De postcode van de student
     * @return De postcode van de student
     */
    public String getPostcode() {
        return postcode;
    }
    
    /**
     * De roepnaam van de student
     * @return De roepnaam van de student
     */
    public String getRoepnaam() {
        return roepnaam;
    }
    
    /**
     * De tussenvoegsels van de student
     * @return De tussenvoegsels van de student
     */
    public String getTussenvoegsels() {
        return tussenvoegsels;
    }
    
    /**
     * De datum waarop de student is uitgeschreven
     * @return De datum van uitschrijving opgeslagen in een <strong>String</strong>
     * in de vorm van <strong>dd-mm-jjjj</strong>.
     */
    public String getUitgeschreven() {
        String datum;
        SimpleDateFormat sdf;
        
        if(uitgeschreven != null) {
            sdf = new SimpleDateFormat("dd-MM-yyyy");
            datum = sdf.format(uitgeschreven);
            return datum;
        }
        else {
            return "";
        }
    }
    
    /**
     * De datum waarop de student is uitgeschreven
     * @return De datum van uitschrijving opgeslagen in een <strong>Date</strong>.
     */
    public Date getUitschrijfdatum() {
        return this.uitgeschreven;
    }
    
    /**
     * De voorletters van de student
     * @return De voorletters van de student
     */
    public String getVoorletters() {
        return voorletters;
    }
    
    /**
     * De woonplaats van de student
     * @return De woonplaats van de student
     */
    public String getWoonplaats() {
        return woonplaats;
    }
    
    /**
     * Deze methode maakt de naam van een student op en geeft deze terug aan
     * de oproepende code.
     * @return De volledige naam van de student
     */
    public String getNaam() {
        if(this.tussenvoegsels == null || this.tussenvoegsels.length() == 0) {
            return this.roepnaam + " " + this.achternaam;
        }
        else {
            return this.roepnaam + " "  + this.tussenvoegsels + " " +  this.achternaam;
        }
    }

    /**
     * Deze methode (setter) zet de eigenschap achternaam van de klasse.
     * @param achternaam String met daarin de achternaam van de student.
     */
    public void setAchternaam(String achternaam) {
        this.achternaam = achternaam;
    }

    /**
     * Deze methode (setter) zet de eigenschap adres van de klasse.
     * @param adres String met daarin de adres van de student.
     */
    public void setAdres(String adres) {
        this.adres = adres;
    }

    /**
     * Deze methode (setter) zet de eigenschap ovnr van de klasse.
     * @param ovnr String met daarin de OV-nummer van de student.
     */
    public void setOvnr(String ovnr) {
        this.ovnr = ovnr;
    }

    /**
     * Deze methode (setter) zet de eigenschap postcode van de klasse.
     * @param postcode String met daarin de postcode van de student.
     */
    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    /**
     * Deze methode (setter) zet de eigenschap roepnaam van de klasse.
     * @param roepnaam String met daarin de roepnaam van de student.
     */
    public void setRoepnaam(String roepnaam) {
        this.roepnaam = roepnaam;
    }

    /**
     * Deze methode (setter) zet de eigenschap tussenvoegsels van de klasse.
     * @param tussenvoegsels String met daarin de tussenvoegsels van de student.
     */
    public void setTussenvoegsels(String tussenvoegsels) {
        this.tussenvoegsels = tussenvoegsels;
    }

    /**
     * Deze methode (setter) zet de eigenschap uitgeschreven van de klasse.
     * @param uitgeschreven Date met daarin de datum van uitschrijving van de student.
     */
    public void setUitgeschreven(Date uitgeschreven) {
        this.uitgeschreven = uitgeschreven;
    }
    
    /**
     * Deze methode (setter) zet de eigenschap voorletters van de klasse.
     * @param voorletters String met daarin de voorletters van de student.
     */
    public void setVoorletters(String voorletters) {
        this.voorletters = voorletters;
    }

    /**
     * Deze methode (setter) zet de eigenschap woonplaats van de klasse.
     * @param woonplaats String met daarin de woonplaats van de student.
     */
    public void setWoonplaats(String woonplaats) {
        this.woonplaats = woonplaats;
    }
}
