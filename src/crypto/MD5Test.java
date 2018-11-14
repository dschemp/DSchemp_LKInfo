package crypto;

import com.sun.org.apache.xerces.internal.impl.dv.util.HexBin;
import crypto.hash.MD5;
import org.junit.jupiter.api.TestInstance;

import static org.junit.jupiter.api.Assertions.*;

@org.junit.jupiter.api.TestInstance(TestInstance.Lifecycle.PER_CLASS)
class MD5Test {

    /* Global variables */
    private MD5 md5;

    //region Setup / Init
    @org.junit.jupiter.api.BeforeAll
    public void setUp() {
        md5 = new MD5();
    }
    //endregion

    /*
     * Test Cases provided by official documentation
     */
    @org.junit.jupiter.api.Test
    void md5_emptystring() {
        byte[] arr = "".getBytes();
        byte[] expected = HexBin.decode("d41d8cd98f00b204e9800998ecf8427e");
        byte[] actual = md5.hash(arr);
        assertArrayEquals(expected, actual);
    }

    @org.junit.jupiter.api.Test
    void md5_a() {
        byte[] arr = "a".getBytes();
        byte[] expected = HexBin.decode("0cc175b9c0f1b6a831c399e269772661");
        byte[] actual = md5.hash(arr);
        assertArrayEquals(expected, actual);
    }

    @org.junit.jupiter.api.Test
    void md5_abc() {
        byte[] arr = "abc".getBytes();
        byte[] expected = HexBin.decode("900150983cd24fb0d6963f7d28e17f72");
        byte[] actual = md5.hash(arr);
        assertArrayEquals(expected, actual);
    }

    @org.junit.jupiter.api.Test
    void md5_messagedigest() {
        byte[] arr = "message digest".getBytes();
        byte[] expected = HexBin.decode("f96b697d7cb7938d525a2f31aaf161d0");
        byte[] actual = md5.hash(arr);
        assertArrayEquals(expected, actual);
    }

    @org.junit.jupiter.api.Test
    void md5_alphabet() {
        byte[] arr = "abcdefghijklmnopqrstuvwxyz".getBytes();
        byte[] expected = HexBin.decode("c3fcd3d76192e4007dfb496cca67e13b");
        byte[] actual = md5.hash(arr);
        assertArrayEquals(expected, actual);
    }

    @org.junit.jupiter.api.Test
    void md5_alphabetUpperLowerCaseAndNumbers() {
        byte[] arr = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789".getBytes();
        byte[] expected = HexBin.decode("d174ab98d277d9f5a5611c2c9f419d9f");
        byte[] actual = md5.hash(arr);
        assertArrayEquals(expected, actual);
    }

    @org.junit.jupiter.api.Test
    void md5_eightTimesNumbersFrom1to0() {
        byte[] arr = "12345678901234567890123456789012345678901234567890123456789012345678901234567890".getBytes();
        byte[] expected = HexBin.decode("57edf4a22be3c955ac49da2e2107b67a");
        byte[] actual = md5.hash(arr);
        assertArrayEquals(expected, actual);
    }
}
