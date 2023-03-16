package com.yuxin.kvlogbased.model;

import com.yuxin.kvlogbased.enums.CommandTypeEnum;

import java.io.Serializable;

public class CommandRequestModel<T> implements Serializable {

    private CommandTypeEnum commandTypeEnum;
    private String key;
    private T value;

    public void setCommandTypeEnum(CommandTypeEnum commandTypeEnum) {
        this.commandTypeEnum = commandTypeEnum;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public void setValue(T value) {
        this.value = value;
    }

    public CommandTypeEnum getCommandTypeEnum() {
        return commandTypeEnum;
    }

    public String getKey() {
        return key;
    }

    public T getValue() {
        return value;
    }
}
