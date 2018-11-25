package crypto;

import com.sun.org.apache.xerces.internal.impl.dv.util.HexBin;
import crypto.hash.MD5;

public class Hash_Main {
    public static void main(String[] args) {
        byte[] arr = "".getBytes();
        MD5 md5 = new MD5();
        byte[] hash = md5.hash(arr);
        String s = HexBin.encode(hash);
        System.out.println(s);
        // md5.hash(testBytes);
    }
}
