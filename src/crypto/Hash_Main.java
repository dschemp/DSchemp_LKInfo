package crypto;

import crypto.hash.MD5;

public class Hash_Main {
    public static void main(String[] args) {
        MD5 md5 = new MD5();
        byte[] arr = new byte[248];
        // md5.hash(arr);
        int length = arr.length;

        while (length % 64 != 56) {
            length++;
        }

        System.out.println(length);
        // md5.hash(testBytes);
    }

}
