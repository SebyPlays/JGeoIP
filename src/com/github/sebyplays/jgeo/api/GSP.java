package com.github.sebyplays.jgeo.api;

import com.github.sebyplays.jgeo.utils.io.FileManager;
import lombok.Getter;
import lombok.SneakyThrows;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

public class GSP {

    @Getter private JSONObject jsonObject;

    @SneakyThrows
    public GSP(String jsonUrl){
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new URL(jsonUrl).openConnection().getInputStream()));
        String line;
        StringBuilder stringBuilder = new StringBuilder();
        while ((line = bufferedReader.readLine()) != null){
            stringBuilder.append(line);
        }
        this.jsonObject = new JSONObject(stringBuilder.toString());
        this.jsonObject.put("initial-jgeo-url", jsonUrl);
        new FileManager().saveJson("jgeo-cache_" + UUID.randomUUID() + "_" + new SimpleDateFormat("MM-dd-yyyy_HH-mm-ss").format(new Date()) + ".json", this.jsonObject);
    }

}
