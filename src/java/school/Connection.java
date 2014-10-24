
package school;

/**
 * Deze klasse verzorgt de verbinding met de MySQL database School.
 * @author Ton van Beuningen
 * @version 23-09-2008
 */
import database.DatabaseConnection;

public class Connection {
    String vendor, host, user, password, databaseName;
    DatabaseConnection dbc;
    
    /**
     * Deze constructor geeft de waarde aan de eigenschappen van de klasse
     * en legt vervolgens de verbinding met de database school.
     */
    public Connection() {
        this.vendor = "MySQL";
    	this.host = "localhost";
    	this.user = "root";
    	this.password = "";
    	this.databaseName = "school";
        this.dbc = new DatabaseConnection(this.vendor, this.host, this.databaseName, this.user, this.password);
    }
    
    /**
     * Deze methode opent de verbinding en geeft -1 terug als de 
     * verbinding niet geopend kan worden
     * @return -1 als de verbinding niet geopend kan worden, elke andere 
     * waarde betekent dat de verbinding geopend kan worden
     */
    public int openConnection() {
    	return this.dbc.setDatabaseConnection();
    }
    
    /**
     * Deze methode geeft de verbinding terug. 
     * @return de verbinding met de database school.
     */
    public DatabaseConnection getConnection() {
    	return this.dbc;
    }
    
    /**
     * Deze methode sluit de verbinding met de database school.
     * @return -1 indien het sluitenb mislukt, elke andere waarde
     * betekent dat de verbinding gesloten is.
     */
    public int closeConnection() {
        return dbc.closeDatabaseConnection();
    }
}

