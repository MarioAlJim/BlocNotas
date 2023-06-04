package dataaccess;

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
                    "jdbc:sqlserver://localhost:1433;" +
                    "databaseName=BlogNotas;" +
                    "user=DBBlockNotas;" +
                    "password=ekMMarw156ndrcS;" +
                    "loginTimeout=30;");
           
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
