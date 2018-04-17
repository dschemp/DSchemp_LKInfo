package mysql;

import java.sql.*;
import java.util.ArrayList;
import java.util.Properties;

public class MySQLHandler {

    private String Hostname, Port, Username, Password;
    public Connection Conn;

    /**
     * @param hostname IP-Adresse (ex.: "127.0.0.1")
     * @param port Port (ex.: "3306")
     * @param username Username (ex.: "root")
     * @param password Passwort (ex.: "123456")
     */
    public MySQLHandler(String hostname, String port, String username, String password) {
        this.Hostname = hostname;
        this.Port     = port;
        this.Username = username;
        this.Password = password;

        try {
            Conn = getConnection();
        } catch (SQLException e) {
            Conn = null;
            misc.JHelper.ShowMessageBox("Konnte nicht mit Datenbank verbinden!\n\n" + e.getMessage());
        }
    }

    public Connection getConnection() throws SQLException {
        Properties connectionProps = new Properties();
        connectionProps.put("user", Username);
        connectionProps.put("password", Password);

        misc.ConsoleLogger.LogToConsole("Connecting to database", MySQLHandler.class);
        return DriverManager.getConnection("jdbc:" + "mysql" + "://" + Hostname + ":" + Port + "/sample_data?useSSL=false", connectionProps);
    }

    /**
     * @param query Query, der ausgeführt werden soll
     * @return Output-Text mit Daten
     */
    public String ExecuteQuery(String query) throws SQLException {
        ArrayList<String> outputLines = new ArrayList<>();
        try (Statement stmt = Conn.createStatement();
             ResultSet rs = stmt.executeQuery( query )
        ) {
            while ( rs.next() ) {
                String line = "";
                int numColumns = rs.getMetaData().getColumnCount();
                for ( int i = 1 ; i <= numColumns ; i++ ) {
                    // Column numbers start at 1.
                    // Also there are many methods on the result set to return
                    //  the column as a particular type. Refer to the Sun documentation
                    //  for the list of valid conversions.
                    line += rs.getObject(i);
                    if (i != numColumns)
                        line += " | ";
                }
                outputLines.add(line.trim());
            }
        } catch (SQLException e) {
            throw new SQLException("!!! Query konnte nicht gelöst werden !!!");
        }

        // Output Text erstellen
        String output = "";
        for (String s : outputLines)
            output += s + '\n';
        return output.trim();
    }

}
