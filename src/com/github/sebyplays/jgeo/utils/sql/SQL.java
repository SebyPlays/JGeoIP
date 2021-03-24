package com.github.sebyplays.jgeo.utils.sql;

import com.github.sebyplays.jgeo.api.JGeo;
import lombok.SneakyThrows;

import java.io.File;
import java.net.InetAddress;
import java.sql.*;
import java.util.UUID;

public class SQL {

    public Connection connection = null;
    public File sqlFile = new File(System.getProperty("user.dir") + "/data/sql/sql.db");


    public SQL(){
        if(!new File(sqlFile.getParent()).exists()){
            new File(sqlFile.getParent()).mkdirs();
        }
        connect();
        execU("CREATE TABLE IF NOT EXISTS jgeo_db_results_record(uuid VARCHAR(255), " +
                "ip VARCHAR(255), continent VARCHAR(255), country VARCHAR(255), region VARCHAR(255), city VARCHAR(255), district VARCHAR(255), " +
                "postal VARCHAR(255), latitude VARCHAR(255), longitude VARCHAR(255), timezone VARCHAR(255), isp VARCHAR(255), " +
                "organization VARCHAR(255), autonomoussystem VARCHAR(255), asname VARCHAR(255), reverse VARCHAR(255), cellular VARCHAR(255), proxy VARCHAR(255), hosting VARCHAR(255), provider VARCHAR(255))");
    }

    public boolean connect(){
        try { connection = DriverManager.getConnection("jdbc:sqlite:" + System.getProperty("user.dir") + "/data/sql/sql.db"); }
        catch (SQLException throwables) { throwables.printStackTrace();
            return false; }
        finally { return true; }
    }

    public boolean isConnected(){
        return (connection != null);
    }

    @SneakyThrows
    public void disconnect(){
        if(isConnected()){
            connection.close();
        }
    }

    public void insertResults(JGeo geo){
        execU("INSERT INTO `jgeo_db_results_record` (`uuid`, `ip`, `continent`, `country`, `region`, `city`, `district`, `postal`, `latitude`, `longitude`, `timezone`, `isp`" +
                ", `organization`, `autonomoussystem`, `asname`, `reverse`, `cellular`, `proxy`, `hosting`, `provider`) VALUES ('" + UUID.randomUUID() + "', '" + geo.getDetails().getInetAddress() + "', '" + geo.getDetails().getContinent() + "', " +
                "'" + geo.getDetails().getCountry() + "', '" + geo.getDetails().getRegion() + "', '" + geo.getDetails().getCity() + "', '" + geo.getDetails().getDistrict() + "" +
                "', '" + geo.getDetails().getPostalCode() + "', '" + geo.getDetails().getLatitude() + "', '" + geo.getDetails().getLongitude() + "', '" + geo.getDetails().getTimezone() + "'" +
                ", '" + geo.getDetails().getISP() + "', '" + geo.getDetails().getOrganization() + "', '" + geo.getDetails().getAS() + "', '" + geo.getDetails().getASName()+ "', " +
                "'" + geo.getDetails().getReverse() + "', '" + geo.getDetails().isCellular() + "', '" + geo.getDetails().isProxy() + "', '" + geo.getDetails().isHosting() + "', '" + geo.getDetails().getProviderURL() + "')");
    }

    public String[] getEntry(UUID UUID){
        return new String[]{"", ""};
    }

    @SneakyThrows
    public UUID getUUID(int id){
        return UUID.fromString((String) getValue("rowid", "jgeo_db_results_record", "rowid", String.valueOf(id), "uuid"));
    }

    @SneakyThrows
    public InetAddress getInetAddress(UUID uuid){
        return InetAddress.getByName((String) getValue("ip", "jgeo_db_results_record", "uuid", uuid.toString(), "ip"));
    }

    public String getContinent(UUID uuid){
        return (String) getValue("continent", "jgeo_db_results_record", "uuid", uuid.toString(), "continent");
    }

    public String getCountry(UUID uuid){
        return (String) getValue("country", "jgeo_db_results_record", "uuid", uuid.toString(), "country");
    }

    public String getRegion(UUID uuid){
        return (String) getValue("region", "jgeo_db_results_record", "uuid", uuid.toString(), "region");
    }

    public String getCity(UUID uuid){
        return (String) getValue("city", "jgeo_db_results_record", "uuid", uuid.toString(), "city");
    }

    public String getDistrict(UUID uuid){
        return (String) getValue("district", "jgeo_db_results_record", "uuid", uuid.toString(), "district");
    }

    public String getPostal(UUID uuid){
        return (String) getValue("postal", "jgeo_db_results_record", "uuid", uuid.toString(), "postal");
    }

    public float getLatitude(UUID uuid){
        return (float) getValue("latitude", "jgeo_db_results_record", "uuid", uuid.toString(), "latitude");
    }

    public float getLongitude(UUID uuid){
        return (float) getValue("longitude", "jgeo_db_results_record", "uuid", uuid.toString(), "longitude");
    }

    public String getTimezone(UUID uuid){
        return (String) getValue("timezone", "jgeo_db_results_record", "uuid", uuid.toString(), "timezone");
    }

    public String getISP(UUID uuid){
        return (String) getValue("isp", "jgeo_db_results_record", "uuid", uuid.toString(), "isp");
    }

    public String getReverse(UUID uuid){
        return (String) getValue("reverse", "jgeo_db_results_record", "uuid", uuid.toString(), "reverse");
    }

    public boolean isCellular(UUID uuid){
        return (boolean) getValue("cellular", "jgeo_db_results_record", "uuid", uuid.toString(), "cellular");
    }

    public boolean isProxy(UUID uuid){
        return (boolean) getValue("proxy", "jgeo_db_results_record", "uuid", uuid.toString(), "proxy");
    }

    public boolean isHosting(UUID uuid){
        return (boolean) getValue("hosting", "jgeo_db_results_record", "uuid", uuid.toString(), "hosting");
    }

    @SneakyThrows
    public Object getValue(String select, String table, String where, String whereValue, String columnLabel){
        ResultSet resultSet = execQ("SELECT `" + select + "` FROM `" + table + "` WHERE `" + where + "`='" + whereValue + "'");
        resultSet.next();
        return resultSet.getObject(columnLabel);
    }

    @SneakyThrows
    public void execU(String query){
        Statement statement = connection.createStatement();
        statement.executeUpdate(query);
    }

    @SneakyThrows
    public ResultSet execQ(String query){
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(query);
        return resultSet;
    }


}
