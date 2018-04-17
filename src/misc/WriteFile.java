package misc;

import javax.swing.*;
import java.io.*;

public class WriteFile {

    public static void WriteFile_WithFileChooser(String text) {
        JFileChooser fileChooser = new JFileChooser();
        if (fileChooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            // save to file
            String filepath = file.getAbsolutePath();
            try (Writer writer = new BufferedWriter(new OutputStreamWriter(
                    new FileOutputStream(filepath), "utf-8"))) {
                try {
                    writer.write(text); // versuche in die Datei zu schreiben
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            } catch (UnsupportedEncodingException e1) {
                e1.printStackTrace();
            }
                catch (FileNotFoundException e1) {
                e1.printStackTrace();
            }
                catch (IOException e1) {
                e1.printStackTrace();
            }
        }
    }

}
