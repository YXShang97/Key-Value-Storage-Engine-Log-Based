package com.yuxin.kvlogbased;

import com.yuxin.kvlogbased.api.KVClient;
import com.yuxin.kvlogbased.core.LogBasedKV;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Test {
    public static void main(String[] args) {
        testFileOp();

        String logFileName = "kv.log";
        KVClient kvClient = new LogBasedKV(logFileName);

        for (int index = 0; index < 5; ++index) {
            kvClient.put("k-" + index, "v-" + index);
        }

        for (int index = 0; index < 5; ++index) {
            String value = (String) kvClient.get("k-" + index);
            System.out.println("get [" + "k-" + index + "]" + " value is [" + value + "]");
        }

        kvClient.del("k-" + 3);

        System.out.println("after del 3");

        String value = (String) kvClient.get("k-" + 3);
        System.out.println("get [" + "k-" + 3 + "]" + " value is [" + value + "]");
    }

    private static void testFileOp() {
        System.out.println("testFileOp begin....");
        String s = "Hello world!";
        int i = 897648764;

        try {
            // create a new file with an ObjectOutputStream
            FileOutputStream out = new FileOutputStream("test.txt");
            ObjectOutputStream oout = new ObjectOutputStream(out);

            // write something in the file
            oout.writeObject(s);
            oout.writeObject(i);

            // close the stream
            oout.close();

            // create an ObjectInputStream for the file we created before
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream("test.txt"));

            // read and print what we wrote before
            System.out.println("" + (String) ois.readObject());
            System.out.println("" + ois.readObject());
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        System.out.println("testFileOp end....");
    }
}
