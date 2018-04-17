package misc;

import javax.swing.*;

public class JHelper {

    public static void ShowMessageBox(String msg) {
        JOptionPane.showMessageDialog(null, msg);
        misc.ConsoleLogger.LogToConsole(msg, JHelper.class);
    }

}
