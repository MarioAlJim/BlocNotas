package dataaccesss;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DataBaseConnection {
    
    private Connection connection;

    public Connection getConnection() throws SQLException{
        connect();
        return connection;
    }

    private void connect() throws SQLException{
            connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/blognotas", 
                    "DBBlockNotas", "ekMMarw156ndrcS");
    }

    public void closeConection(){
        if(connection!=null){
            try {
                if(!connection.isClosed()){
                    connection.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(DataBaseConnection.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
