package com.jsonwitneses.findmyphone.dto;


//import javax.persistence.Column;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.mongodb.lang.NonNull;

public class MetricDto {
    private Long id;

    private Long deviceId;

    private Long timeStamp;

    //временное решение - широта долгота
    @NonNull
    private double longitude;
    @NonNull
    private double latitude;

    private Long cellid;
    private int lac;
    private int rsrp;
    private int rsrq;
    private int sinr;

    private String deviceid;
    private String userid;
    private String imsi;

    public MetricDto() {
    }

    public MetricDto(Long id, Long deviceId, Long timeStamp, double longitude,
                     double latitude, Long cellid, int lac, int rsrp, int rsrq, int sinr,
                     String deviceid, String userid, String imsi) {
        this.id = id;
        this.deviceId = deviceId;
        this.timeStamp = timeStamp;
        this.longitude = longitude;
        this.latitude = latitude;
        this.cellid = cellid;
        this.lac = lac;
        this.rsrp = rsrp;
        this.rsrq = rsrq;
        this.sinr = sinr;
        this.deviceid = deviceid;
        this.userid = userid;
        this.imsi = imsi;
    }

    public MetricDto(Long id, Long deviceId, Long timeStamp, double lambda, double fi) {
        this.id = id;
        this.deviceId = deviceId;
        this.timeStamp = timeStamp;
        this.longitude = lambda;
        this.latitude = fi;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(Long deviceId) {
        this.deviceId = deviceId;
    }

    public Long getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(Long timeStamp) {
        this.timeStamp = timeStamp;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public Long getCellid() {
        return cellid;
    }

    public void setCellid(Long cellid) {
        this.cellid = cellid;
    }

    public int getLac() {
        return lac;
    }

    public void setLac(int lac) {
        this.lac = lac;
    }

    public int getRsrp() {
        return rsrp;
    }

    public void setRsrp(int rsrp) {
        this.rsrp = rsrp;
    }

    public int getRsrq() {
        return rsrq;
    }

    public void setRsrq(int rsrq) {
        this.rsrq = rsrq;
    }

    public int getSinr() {
        return sinr;
    }

    public void setSinr(int sinr) {
        this.sinr = sinr;
    }

    public String getDeviceid() {
        return deviceid;
    }

    public void setDeviceid(String deviceid) {
        this.deviceid = deviceid;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getImsi() {
        return imsi;
    }

    public void setImsi(String imsi) {
        this.imsi = imsi;
    }
}
