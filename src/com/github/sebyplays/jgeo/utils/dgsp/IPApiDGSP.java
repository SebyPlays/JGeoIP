package com.github.sebyplays.jgeo.utils.dgsp;

import com.github.sebyplays.jgeo.api.GSP;
import com.github.sebyplays.jgeo.api.IGeoServiceProvider;
import lombok.SneakyThrows;

import java.net.InetAddress;

public class IPApiDGSP extends GSP implements IGeoServiceProvider {

    private InetAddress inetAddress;


    @SneakyThrows
    public IPApiDGSP(String inetAddress){
        super("https://ipapi.co/" + inetAddress + "/json");
        this.inetAddress = InetAddress.getByName(inetAddress);
    }

    @Override
    public InetAddress getInetAddress() {
        return inetAddress;
    }

    @Override
    public String getContinent() {
        return getJsonObject().get("continent_code").toString();
    }

    @Override
    public String getCountry() {
        return getJsonObject().get("country").toString();
    }

    @Override
    public String getRegion() {
        return getJsonObject().get("region_code").toString();
    }

    @Override
    public String getCity() {
        return getJsonObject().get("city").toString();
    }

    @Override
    public String getDistrict() {
        return null;
    }

    @Override
    public String getPostalCode() {
        return getJsonObject().get("postal").toString();
    }

    @Override
    public float getLatitude() {
        return getJsonObject().getFloat("latitude");
    }

    @Override
    public float getLongitude() {
        return getJsonObject().getFloat("longitude");
    }

    @Override
    public String getTimezone() {
        return getJsonObject().get("timezone").toString();
    }

    @Override
    public String getISP() {
        return null;
    }

    @Override
    public String getOrganization() {
        return getJsonObject().get("org").toString();
    }

    @Override
    public String getAS() {
        return null;
    }

    @Override
    public String getASName() {
        return null;
    }

    @Override
    public String getReverse() {
        return null;
    }

    @Override
    public boolean isCellular() {
        return false;
    }

    @Override
    public boolean isProxy() {
        return false;
    }

    @Override
    public boolean isHosting() {
        return false;
    }

    @Override
    public String getProviderURL() {
        return getJsonObject().getString("initial-jgeo-url");
    }

    @Override
    public Object[] getDataArray() {
        return new Object[]{getInetAddress(), getContinent(), getCountry(), getRegion(), getCity(), getDistrict(), getPostalCode(), getLatitude(), getLongitude(), getTimezone(),
                getISP(), getOrganization(), getAS(), getASName(), getReverse(), isCellular(), isProxy(), isHosting(), getProviderURL()};
    }

}
