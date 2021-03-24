package com.github.sebyplays.jgeo.utils.dgsp;

import com.github.sebyplays.jgeo.api.GSP;
import com.github.sebyplays.jgeo.api.IGeoServiceProvider;
import lombok.Getter;
import lombok.SneakyThrows;

import java.net.InetAddress;

public class IPInfoDGSP extends GSP implements IGeoServiceProvider {

    @Getter private InetAddress inetAddress;

    @SneakyThrows
    public IPInfoDGSP(String inetAddress) {
        super("https://ipinfo.io/" + inetAddress + "?token=6129a101150bd3");
        this.inetAddress = InetAddress.getByName(inetAddress);
    }

    @Override
    public InetAddress getInetAddress() {
        return inetAddress;
    }

    @Override
    public String getContinent() {
        return null;
    }

    @Override
    public String getCountry() {
        return getJsonObject().getString("country");
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
        return null;
    }

    @Override
    public String getPostalCode() {
        return getJsonObject().getString("postal");
    }

    @Override
    public float getLatitude() {
        return Float.parseFloat(getJsonObject().getString("loc").split(",")[0]);
    }

    @Override
    public float getLongitude() {
        return Float.parseFloat(getJsonObject().getString("loc").split(",")[1]);
    }

    @Override
    public String getTimezone() {
        return getJsonObject().getString("timezone");
    }

    @Override
    public String getISP() {
        return null;
    }

    @Override
    public String getOrganization() {
        return getJsonObject().getString("org");
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
        return getJsonObject().getString("hostname");
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
