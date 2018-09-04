package crypto.cipher;

import interfaces.Cipherable;

public class Caesar implements Cipherable {

    private static String Alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    public String Encrypt(String data, int rotation) {
        data = data.trim().toUpperCase();

        String newData = "";

        String newAlphabet = Alphabet.substring(rotation);
        newAlphabet += Alphabet.substring(0, rotation);

        for (int i = 0; i < data.length(); i++) {
            if (!Character.isLetter(data.charAt(i))) {
                newData += data.charAt(i);
                continue;
            }
            int index = Alphabet.indexOf(data.charAt(i));
            newData += newAlphabet.charAt(index);
        }

        return newData;
    }

    public String Decrypt(String encryptedData, int rotation) {
        encryptedData = encryptedData.trim().toUpperCase();

        String newData = "";

        String newAlphabet = Alphabet.substring(rotation);
        newAlphabet += Alphabet.substring(0, rotation);

        for (int i = 0; i < encryptedData.length(); i++) {
            if (!Character.isLetter(encryptedData.charAt(i))) {
                newData += encryptedData.charAt(i);
                continue;
            }
            int index = newAlphabet.indexOf(encryptedData.charAt(i));
            newData += Alphabet.charAt(index);
        }

        return newData;
    }

}
