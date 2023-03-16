package com.yuxin.kvlogbased;

import com.yuxin.kvlogbased.api.KVClient;
import com.yuxin.kvlogbased.core.LogBasedKV;

public class Test {
    public static void main(String[] args) {
        String logFileName = "kv.log";
        KVClient kvClient = new LogBasedKV(logFileName);

//        for (int index = 0; index < 5; ++index) {
//            kvClient.put("k-" + index, "v-" + index);
//        }
//
//        for (int index = 0; index < 5; ++index) {
//            String value = (String) kvClient.get("k-" + index);
//            System.out.println("get [" + "k-" + index + " value is [" + value + "]");
//        }

        kvClient.del("k-" + 2);

        System.out.println("after del 3");

        String value = (String) kvClient.get("k-" + 3);
        System.out.println("get [" + "k-" + 3 + " value is [" + value + "]");
    }
}
