package mysql;

import misc.LoadDesign;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;

public class QueryForm {
    private JButton buttonQuery;
    private JTextField textFieldQuery;
    private JPanel panelBackground;
    private JTextArea textAreaOutput;
    private JButton logSpeichernButton;

    private MySQLHandler mySQLHandler;

    public QueryForm(String hostname, String port, String username, String password) {
        // Execute Query Button Press
        buttonQuery.addActionListener( (e) -> ExecuteQuery() );
        misc.ConsoleLogger.LogToConsole("QueryButton-Clicker Event hinzugefügt", QueryForm.class);

        // EnterKey-Pressed in TextField
        textFieldQuery.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                super.keyPressed(e);
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_ENTER:
                        ExecuteQuery();
                        break;
                    // Command History
                    case KeyEvent.VK_DOWN: // Arrow down
                        break;
                    case KeyEvent.VK_UP: // Arrow up
                        break;
                }
            }
        });
        misc.ConsoleLogger.LogToConsole("TextField-Key Event hinzugefügt", QueryForm.class);


        // Log speichern
        logSpeichernButton.addActionListener( (e) -> misc.WriteFile.WriteFile_WithFileChooser(textAreaOutput.getText()) );
        misc.ConsoleLogger.LogToConsole("LogSaveButton-Clicker Event hinzugefügt", QueryForm.class);

        // Verbinden
        if (mySQLHandler == null) {
            mySQLHandler = new MySQLHandler(hostname, port, username, password);
            if (mySQLHandler.Conn != null)
                AddToOutputField(">>> ", "VERBUNDEN: " + username + "@" + hostname);
            else {
                AddToOutputField(">>> ", "Verbindung fehlgeschlagen zu: " + username + "@" + hostname);
                textFieldQuery.setEditable(false);
                buttonQuery.setEnabled(false);
            }
            misc.ConsoleLogger.LogToConsole("MySQLHandler-Init abgeschlossen", QueryForm.class);
        }
        misc.ConsoleLogger.LogToConsole("MySQLHandler-Init abgeschlossen", QueryForm.class);

        misc.LoadDesign.SetDesign(LoadDesign.WINDOWS);
        misc.ConsoleLogger.LogToConsole("Design Geladen", QueryForm.class);
    }

    void ExecuteQuery() {
        String query = textFieldQuery.getText().trim();
        String output;
        misc.ConsoleLogger.LogToConsole("Query ausführen: " + query, QueryForm.class);

        try {
            output = mySQLHandler.ExecuteQuery(query);
        } catch (SQLException e) {
            output = e.getMessage();
            misc.ConsoleLogger.LogToConsole(e.getMessage(), SQLException.class);
        }

        textFieldQuery.setText("");
        AddToOutputField(">>> ", query);
        misc.ConsoleLogger.LogToConsole(">>> " + query, QueryForm.class);
        AddToOutputField("", output);
        misc.ConsoleLogger.LogToConsole('\n' + output + '\n', QueryForm.class);
        AddToOutputField("", "");
    }

    void AddToOutputField(String prefix, String s) {
        String output = textAreaOutput.getText();
        output += prefix + s.trim() + '\n';
        textAreaOutput.setText(output);
    }

    /*
     *
     *  MAIN & INSTANTIATE
     *
     */

    public static void main(String[] args) {
        // "192.168.40.130", "3306", "root", "123456789"
        if (args.length != 4)
            misc.JHelper.ShowMessageBox("Benutzung: \t programm.jar (IP) (Port) (Benutzername) (Password)");
        else
            Instantiate(args);
    }

    private static void Instantiate(String[] args) {
        JFrame frame = new JFrame("Query");
        Dimension dim = new Dimension(800, 600);
        QueryForm form = new QueryForm(args[0], args[1], args[2], args[3]);
        frame.setContentPane(form.panelBackground);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        frame.setSize(dim);
        misc.ConsoleLogger.LogToConsole("Frame/Form-Init abgeschlossen", QueryForm.class);
    }
}
