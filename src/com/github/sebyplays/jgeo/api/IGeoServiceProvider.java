package com.github.sebyplays.jgeo.api;

import java.net.InetAddress;

public interface IGeoServiceProvider {

    InetAddress getInetAddress();
    String getContinent();
    String getCountry();
    String getRegion();
    String getCity();
    String getDistrict();
    String getPostalCode();
    float getLatitude();
    float getLongitude();
    String getTimezone();
    String getISP();
    String getOrganization();
    String getAS();
    String getASName();
    String getReverse();
    boolean isCellular();
    boolean isProxy();
    boolean isHosting();
    String getProviderURL();
    Object[] getDataArray();
}
