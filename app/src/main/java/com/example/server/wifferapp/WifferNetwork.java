package com.example.server.wifferapp;

/**
 * Created by server on 2/10/15.
 */
public class WifferNetwork {

    //params.permit(:essid, :bssid, :band, :channel, :security_type, :is_wps, :longitude, :latitude, :first_seen, :last_seen, :user_id)
    private String essid;
    private String bssid;
    private String band;
    private String channel;
    private String security_type;
    private String is_wps;
    private String longitude;
    private String latitude;
    private String first_seen;
    private String last_seen;
    private String user_id;

    public WifferNetwork(String essid, String bssid, String band, String channel, String security_type, String is_wps, String longitude, String latitude, String first_seen, String last_seen, String user_id) {
        this.essid = essid;
        this.bssid = bssid;
        this.band = band;
        this.channel = channel;
        this.security_type = security_type;
        this.is_wps = is_wps;
        this.longitude = longitude;
        this.latitude = latitude;
        this.first_seen = first_seen;
        this.last_seen = last_seen;
        this.user_id = user_id;
    }

    public String getEssid() {
        return essid;
    }

    public void setEssid(String essid) {
        this.essid = essid;
    }

    public String getBssid() {
        return bssid;
    }

    public void setBssid(String bssid) {
        this.bssid = bssid;
    }

    public String getBand() {
        return band;
    }

    public void setBand(String band) {
        this.band = band;
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public String getSecurity_type() {
        return security_type;
    }

    public void setSecurity_type(String security_type) {
        this.security_type = security_type;
    }

    public String getIs_wps() {
        return is_wps;
    }

    public void setIs_wps(String is_wps) {
        this.is_wps = is_wps;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getFirst_seen() {
        return first_seen;
    }

    public void setFirst_seen(String first_seen) {
        this.first_seen = first_seen;
    }

    public String getLast_seen() {
        return last_seen;
    }

    public void setLast_seen(String last_seen) {
        this.last_seen = last_seen;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }
}
