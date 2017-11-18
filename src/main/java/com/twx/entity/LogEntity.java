package com.twx.entity;

import org.hibernate.validator.constraints.Length;

import javax.persistence.*;

/**
 * Created by twx on 2017/11/7.
 */
@Entity
@Table(name = "t_log")
public class LogEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String requestURI;

    private String status;

    private String ip;

    @Length(max=2000)
    private String eReason;

    @Length(max=2000)
    private String data;

    private String requestTime;

    public LogEntity() {
    }

    public LogEntity(String status, String ip, String requestURI, String data, String requestTime) {
        this.status = status;
        this.ip = ip;
        this.data = data;
        this.requestTime=requestTime;
        this.requestURI = requestURI;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRequestURI() {
        return requestURI;
    }

    public void setRequestURI(String requestURI) {
        this.requestURI = requestURI;
    }

    public String getRequestTime() {
        return requestTime;
    }

    public void setRequestTime(String requestTime) {
        this.requestTime = requestTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String geteReason() {
        return eReason;
    }

    public void seteReason(String eReason) {
        this.eReason = eReason;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
