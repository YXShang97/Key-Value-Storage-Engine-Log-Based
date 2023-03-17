package com.yuxin.kvlogbased;

import com.yuxin.kvlogbased.api.KVClient;
import com.yuxin.kvlogbased.core.LogBasedKV;
import org.junit.Test;

import static org.junit.Assert.*;

public class TestKVLogBased {
    String logFileName = "kv.log";
    KVClient kvClient = new LogBasedKV(logFileName);

    @Test
    public void testPutGet() {
        kvClient.put("k-1", 100);
        kvClient.put("k-1", 'a');
        int[] arr = {1, 2, 3};
        kvClient.put("k-1", arr);
        assertArrayEquals((int[]) kvClient.get("k-1"), arr);
    }

    @Test
    public void testDelGet() {
        kvClient.put("k-2", 200);
        kvClient.put("k-3", 300);
        kvClient.del("k-3");
        assertNull(kvClient.get("k-3"));
    }
}
