package crypto.cipher;

public class Vigenere {

    public String Encrypt(String data, String key) {
        Caesar caesar = new Caesar();
        data = data.toUpperCase();
        key = key.toUpperCase();

        String encryptedData = "";

        String paddedKey = key;
        while (paddedKey.length() < data.length())
            paddedKey += key;

        int paddedKeyIndex = 0;
        for (int i = 0; i < data.length(); i++) {
            if (!Character.isLetter(data.charAt(i))) {
                encryptedData += data.charAt(i);
                continue;
            }

            encryptedData += caesar.Encrypt(Character.toString(data.charAt(i)), paddedKey.charAt(paddedKeyIndex));
            paddedKeyIndex++;
        }

        return encryptedData;
    }

    public String Decrypt(String encryptedData, String key) {
        Caesar caesar = new Caesar();
        encryptedData = encryptedData.toUpperCase();
        key = key.toUpperCase();

        String decryptedData = "";

        String paddedKey = key;
        while (paddedKey.length() < encryptedData.length())
            paddedKey += key;

        int paddedKeyIndex = 0;
        for (int i = 0; i < encryptedData.length(); i++) {
            if (!Character.isLetter(encryptedData.charAt(i))) {
                decryptedData += encryptedData.charAt(i);
                continue;
            }

            decryptedData += caesar.Decrypt(Character.toString(encryptedData.charAt(i)), paddedKey.charAt(paddedKeyIndex));
            paddedKeyIndex++;
        }

        return decryptedData;
    }

}
