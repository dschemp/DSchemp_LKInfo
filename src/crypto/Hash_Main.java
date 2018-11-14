package crypto;

import crypto.hash.MD5;

public class Hash_Main {
    public static void main(String[] args) {
        MD5 md5 = new MD5();
        byte[] arr = new byte[100];
        md5.hash(arr);

        // md5.hash(testBytes);
    }

}
