package misc;

import javax.swing.*;

public class LoadDesign {

    public static final String WINDOWS = "com.sun.java.swing.plaf.windows.WindowsLookAndFeel";
    public static final String GTK     = "com.sun.java.swing.plaf.gtk.GTKLookAndFeel";
    public static final String MOTIF   = "com.sun.java.swing.plaf.motif.MotifLookAndFeel";
    public static final String METAL   = UIManager.getCrossPlatformLookAndFeelClassName();
    public static final String SYSTEM  = UIManager.getSystemLookAndFeelClassName();

    public static void SetDesign(String classPath) {
        try {
            UIManager.setLookAndFeel(classPath);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }
    }

}
