package crypto;

import crypto.hash.MD5;

public class Hash_Main {
    public static void main(String[] args) {
        MD5 md5 = new MD5();
        byte[] arr = new byte[100];

        int newArraySize = ((arr.length / 64) * 64) + 56;
        byte[] newPaddedArr = new byte[newArraySize];
        System.out.println(newArraySize + " bytes");

        // md5.hash(testBytes);
    }

}
