package com.yuxin.kvlogbased.api;

public interface KVClient<T> {
    void put(String key, T value);

    T get(String key);

    void del(String key);
}
