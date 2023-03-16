package com.yuxin.kvlogbased.core;

import com.yuxin.kvlogbased.MyObjectOutputStream;
import com.yuxin.kvlogbased.api.KVClient;
import com.yuxin.kvlogbased.enums.CommandTypeEnum;
import com.yuxin.kvlogbased.model.CommandRequestModel;

import java.io.*;

public class LogBasedKV implements KVClient {
    private File logFile;

    public LogBasedKV(String fileName) {
        logFile = new File(fileName);
    }

    @Override
    public void put(String key, Object value) {
        ObjectOutputStream oos = null;

        try {
            FileOutputStream fos =new FileOutputStream(logFile, true);

            if (logFile.length() < 1) {
                oos = new ObjectOutputStream(fos);
            } else {
                oos = new MyObjectOutputStream(fos);
            }
            CommandRequestModel commandRequestModel = new CommandRequestModel();
            commandRequestModel.setCommandTypeEnum(CommandTypeEnum.SET);
            commandRequestModel.setKey(key);
            commandRequestModel.setValue(value);

            oos.writeObject(commandRequestModel);
            oos.flush();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("put exception,[" + key + "," + value + "]");
        } finally {
            try {
                if (oos != null) {
                    oos.close();
                }
            } catch (Exception e) {
                System.out.println("objectOutputStream close exception");
            }
        }
    }

    @Override
    public Object get(String key) {
        ObjectInputStream ois = null;
        try {
            FileInputStream fis = new FileInputStream(logFile);
            ois = new ObjectInputStream(fis);
            CommandRequestModel lastUpdateCommand = null;
            CommandRequestModel curCommand = (CommandRequestModel) ois.readObject();
            while (curCommand != null) {
                if (key.equals(curCommand.getKey())) {
                    lastUpdateCommand = curCommand;
                }
                curCommand = (CommandRequestModel) ois.readObject();
            }

            if (lastUpdateCommand == null || lastUpdateCommand.getCommandTypeEnum() == CommandTypeEnum.DEL) {
                return null;
            }
            return lastUpdateCommand.getValue();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("get exception,[" + key + "]");
        } finally {
            try {
                if (ois != null) {
                    ois.close();
                }
            } catch (Exception e) {
                System.out.println("objectInputStream close exception");
            }
        }
        return null;
    }

    @Override
    public void del(String key) {
        ObjectOutputStream oos = null;
        try {
            FileOutputStream fos =new FileOutputStream(logFile, true);

            if (logFile.length() < 1) {
                oos = new ObjectOutputStream(fos);
            } else {
                oos = new MyObjectOutputStream(fos);
            }

            CommandRequestModel commandRequestModel = new CommandRequestModel();
            commandRequestModel.setCommandTypeEnum(CommandTypeEnum.DEL);
            commandRequestModel.setKey(key);

            oos.writeObject(commandRequestModel);
            oos.flush();
        } catch (Exception e) {
            System.out.println("del exception,[" + key + "]");
        } finally {
            try {
                if (oos != null) {
                    oos.close();
                }
            } catch (Exception e) {
                System.out.println("objectOutputStream close exception");
            }
        }
    }
}
