package crypto.cipher;

public class Caesar {

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

    public String Encrypt(String data, char key) {
        if (!Character.isLetter(key))
            throw new IllegalArgumentException("Key cannot be any other character than A-Za-z");

        key = Character.toUpperCase(key);
        int index = -1;
        for (int i = 0; i < Alphabet.length(); i++) {
            if (Alphabet.charAt(i) == key) {
                index = i;
                break;
            }
        }

        if (index == -1)
            throw new IllegalArgumentException("Key is not in the alphabet.");

        return Encrypt(data, index);
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

    public String Decrypt(String data, char key) {
        if (!Character.isLetter(key))
            throw new IllegalArgumentException("Key cannot be any other character than A-Za-z");

        key = Character.toUpperCase(key);
        int index = -1;
        for (int i = 0; i < Alphabet.length(); i++) {
            if (Alphabet.charAt(i) == key) {
                index = i;
                break;
            }
        }

        if (index == -1)
            throw new IllegalArgumentException("Key is not in the alphabet.");

        return Decrypt(data, index);
    }


}
