package crypto.cipher;

import interfaces.Cipherable;

public class Scytale implements Cipherable {

    @Override
    public String Encrypt(String data, int num) {
        String trimedData = "";
        for (char c : data.toCharArray()) {
            if (Character.isLetter(c))
                trimedData += c; // Wenn das Zeichen ein Buchstabe ist, dann fuege es hinzu
        }

        // Berechne die Hoehe des Char-Arrays
        // Die Hoehe ist Anzahl an Buchstaben im getrimmten String durch die Breite,
        // also der "Durchmesser" des Holzklotzes, + 1, falls die Anzahl an Buchstaben
        // nur fuer eine Zeile reicht.
        int arrayHeight = (int) Math.ceil(trimedData.length() / num + 1);
        char[][] cArray = new char[num][arrayHeight];

        for (int i = 0; i < trimedData.length(); i++) {
            int x = i % num; // Die x-Position wickelt sich immer nach (num) mal
            int y = i / num; // Die y-Position ist die Anzahl an "x-Umwicklungen"

            cArray[x][y] = trimedData.charAt(i);
        }

        String newData = ""; // Hier wird der Output hineingeschrieben
        int dataIndex = 0; // Iterator fuer den Data-String, wird fuer Sonder- und Leerzeichen benoetigt
        // Jedes Feld des Char-Arrays wird durchgegangen
        for (int i = 0; i < num * arrayHeight; i++) {
            // Ist das Ende des Data-Strings erreicht, koennen keine weiteren Buchstaben eingefuegt werden
            if (dataIndex >= data.length()) {
                break;
            }

            // Wenn an der Stelle etwas anderes als ein Buchstabe steht, dann fuege dieses Zeichen einfach ein
            if (!Character.isLetter(data.charAt(dataIndex))) {
                newData += data.charAt(dataIndex++);
                i--; // Dekrementiere i, da auf den Buchstaben nicht zugeriffen wurde
                continue;
            }

            int x = i % arrayHeight; // Die x-Position ist praktisch nach unten gerichtet und wickelt sich um die Hoehe
            int y = i / arrayHeight; // Die y-Position ist die Anzahl an "x-Umwicklungen"

            char c = cArray[y][x];
            if (c == 0) {
                continue;
            }
            dataIndex++;
            newData += c;
        }

        return newData;
    }

    @Override
    public String Decrypt(String encryptedText, int num) {
        return null;
    }

}
