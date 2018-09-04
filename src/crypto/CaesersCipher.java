package crypto;

public class CaesersCipher {

    private static String Alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    public static String Encrypt(String data, int rotation) {
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

    public static String Decrypt(String encryptedData, int rotation) {
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
