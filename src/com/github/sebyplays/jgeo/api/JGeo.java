package com.github.sebyplays.jgeo.api;

import com.github.sebyplays.jgeo.utils.dgsp.IPApi2DGSP;
import com.github.sebyplays.jgeo.utils.dgsp.IPApiDGSP;
import com.github.sebyplays.jgeo.utils.sql.SQL;
import lombok.Getter;
import lombok.SneakyThrows;
import org.json.JSONObject;

import java.net.InetAddress;
import java.util.Arrays;

public class JGeo {

    @Getter private IGeoServiceProvider details;
    @Getter private SQL sql = new SQL();
    public JGeo(IGeoServiceProvider iGeoServiceProvider){
        this.details = iGeoServiceProvider;
        getSql().insertResults(this);

    }

    public JSONObject getJsonCache(String fileName){
        return new JSONObject(System.getProperty("user.dir") + "/data/jgeo-cache/" + fileName);
    }

}
