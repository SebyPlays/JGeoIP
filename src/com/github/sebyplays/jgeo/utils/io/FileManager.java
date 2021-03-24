package com.github.sebyplays.jgeo.utils.io;

import lombok.SneakyThrows;
import org.json.JSONObject;

import java.io.File;
import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FileManager {

    public FileManager(){

    }

    @SneakyThrows
    public void saveJson(String filename, JSONObject jsonObject){
        if(!new File(System.getProperty("user.dir") + "/data/jgeo-cache/").exists()){
            new File(System.getProperty("user.dir") + "/data/jgeo-cache/").mkdirs();
        }
        FileWriter fileWriter = new FileWriter(System.getProperty("user.dir") + "/data/jgeo-cache/" + filename);
        fileWriter.write("//JSON CACHE FILE OF THE https://github.com/SebyPlays/JGeo/ LIBRARY\n//CREATED ON "
                + new SimpleDateFormat("MM-dd-yyyy-HH:mm:ss").format(new Date()) + "\n\n" + jsonObject.get("initial-jgeo-url").toString() + "\n" + jsonObject.toString());
        fileWriter.close();
    }


}
