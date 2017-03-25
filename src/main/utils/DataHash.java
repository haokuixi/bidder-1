package main.utils;

import org.hashids.Hashids;

public class DataHash {

    private Hashids hashids;
    private String salt = "ziamahmood";
    private int minHashLen = 6;

    public String encode(int data) {
        hashids = new Hashids(salt, minHashLen);
        return hashids.encode(data);
    }

    public int decode(String hash) {
        hashids = new Hashids(salt, minHashLen);
        return (int) hashids.decode(hash)[0];
    }
}
