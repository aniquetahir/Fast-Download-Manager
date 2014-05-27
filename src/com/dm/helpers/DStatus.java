package com.dm.helpers;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Hashtable;

/**
 *
 * @author Anique Tahir<aniquetahir@gmail.com>
 */
public class DStatus implements Serializable {
    private Hashtable<Integer,Download> dictionary;
    
    
    private static class Part{
        int startByte;
        int endByte;
        boolean status;
        byte[] data;
    }    
    
    private class Download implements Serializable{
        private int id;
        private String filename;
        private String downloadURL;
        private String referrerURL;
        public ArrayList<Part> parts;
    };
    
    public DStatus(){
        this.dictionary=new Hashtable<Integer,DStatus.Download>();
    }
    
    public static DStatus getConfig(){
        FileInputStream fis=null;
        ObjectInputStream ois=null;
        try{
            fis=new FileInputStream("config.cfg");
            ois = new ObjectInputStream(fis);
            return (DStatus)ois.readObject();
        }catch(Exception e){
            System.out.println("Failed to load configuration");
            return null;
        }
    };
    
    public void close(){
        FileOutputStream fos = null;
        ObjectOutputStream oos = null;
        try {
            fos = new FileOutputStream("config.cfg");
            oos = new ObjectOutputStream(fos);
            oos.writeObject(DStatus.this);
            oos.flush();
            oos.close();
        } catch (Exception ex) {
            System.out.println("Failed to close config");
        }
        
        
    };
    
}
