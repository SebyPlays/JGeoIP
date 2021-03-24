package com.github.sebyplays.jgeo.utils.dgsp;

import com.github.sebyplays.jgeo.api.GSP;
import com.github.sebyplays.jgeo.api.IGeoServiceProvider;
import lombok.Getter;
import lombok.SneakyThrows;

import java.net.InetAddress;

public class IPApi2DGSP extends GSP implements IGeoServiceProvider {

    @Getter private InetAddress inetAddress;

    @SneakyThrows
    public IPApi2DGSP(String inetAddress) {
        super("http://ip-api.com/json/" + inetAddress + "?fields=23805942");
        this.inetAddress = InetAddress.getByName(inetAddress);
    }

    @SneakyThrows
    @Override
    public InetAddress getInetAddress() {
        return this.inetAddress;
    }

    @Override
    public String getContinent() {
        return (String) getJsonObject().get("continentCode");
    }

    @Override
    public String getCountry() {
        return getJsonObject().getString("countryCode");
    }

    @Override
    public String getRegion() {
        return getJsonObject().getString("region");
    }

    @Override
    public String getCity() {
        return getJsonObject().getString("city");
    }

    @Override
    public String getDistrict() {
        return getJsonObject().getString("district");
    }

    @Override
    public String getPostalCode() {
        return getJsonObject().getString("zip");
    }

    @Override
    public float getLatitude() {
        return getJsonObject().getFloat("lat");
    }

    @Override
    public float getLongitude() {
        return getJsonObject().getFloat("lon");
    }

    @Override
    public String getTimezone() {
        return getJsonObject().getString("timezone");
    }

    @Override
    public String getISP() {
        return getJsonObject().getString("isp");
    }

    @Override
    public String getOrganization() {
        return getJsonObject().getString("org");
    }

    @Override
    public String getAS() {
        return getJsonObject().getString("as");
    }

    @Override
    public String getASName() {
        return getJsonObject().getString("asname");
    }

    @Override
    public String getReverse() {
        return getJsonObject().getString("reverse");
    }

    @Override
    public boolean isCellular() {
        return getJsonObject().getBoolean("mobile");
    }

    @Override
    public boolean isProxy() {
        return getJsonObject().getBoolean("proxy");
    }

    @Override
    public boolean isHosting() {
        return getJsonObject().getBoolean("hosting");
    }

    @Override
    public String getProviderURL() {
        return getJsonObject().getString("initial-jgeo-url");
    }

    public Object[] getDataArray(){
        return new Object[]{getInetAddress(), getContinent(), getCountry(), getRegion(), getCity(), getDistrict(), getPostalCode(), getLatitude(), getLongitude(), getTimezone(),
                getISP(), getOrganization(), getAS(), getASName(), getReverse(), isCellular(), isProxy(), isHosting(), getProviderURL()};
    }
}
