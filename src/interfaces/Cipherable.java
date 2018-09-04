package interfaces;

public interface Cipherable {

    public String Encrypt(String data, int num);
    public String Decrypt(String encryptedText, int num);

}
