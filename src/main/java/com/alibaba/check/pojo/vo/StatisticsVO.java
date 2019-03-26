package com.alibaba.check.pojo.vo;

import java.io.Serializable;


/**
 * 统计数据vo
 */
public class StatisticsVO implements Serializable{

    private static final long serialVersionUID = -1421913129403695807L;

    private String wNumber;    //工号
    private  Integer year;     //接收前端查询参数 年份
    private  Integer month;     //接收前端查询参数 月份
    private  String uname;             //姓名
    private  String workNumber;        // 工号
    private  Integer attencDays;       // 当月出勤天数
    private  Integer sciktimes;        // 当月事假总次数
    private  Integer  affairtimes;     // 当月病假总次数
    private  Integer latetimes;        //当月迟到次数
    private  Integer leaveearlytimes; //当月早退次数Z
    private  Integer minerstimes;     //当月矿工次数


    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getwNumber() { return wNumber; }

    public void setwNumber(String wNumber) { this.wNumber = wNumber; }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Integer getMonth() {
        return month;
    }

    public void setMonth(Integer month) {
        this.month = month;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getWorkNumber() {
        return workNumber;
    }

    public void setWorkNumber(String workNumber) {
        this.workNumber = workNumber;
    }

    public Integer getAttencDays() {
        return attencDays;
    }

    public void setAttencDays(Integer attencDays) {
        this.attencDays = attencDays;
    }

    public Integer getSciktimes() {
        return sciktimes;
    }

    public void setSciktimes(Integer sciktimes) {
        this.sciktimes = sciktimes;
    }

    public Integer getAffairtimes() {
        return affairtimes;
    }

    public void setAffairtimes(Integer affairtimes) {
        this.affairtimes = affairtimes;
    }

    public Integer getLatetimes() {
        return latetimes;
    }

    public void setLatetimes(Integer latetimes) {
        this.latetimes = latetimes;
    }


    public Integer getLeaveearlytimes() {
        return leaveearlytimes;
    }

    public void setLeaveearlytimes(Integer leaveearlytimes) {
        this.leaveearlytimes = leaveearlytimes;
    }

    public Integer getMinerstimes() {
        return minerstimes;
    }

    public void setMinerstimes(Integer minerstimes) {
        this.minerstimes = minerstimes;
    }


    @Override
    public String toString() {
        return "StatisticsVO{" +
                "wNumber=" + wNumber +
                ", year=" + year +
                ", month=" + month +
                ", uname='" + uname + '\'' +
                ", workNumber='" + workNumber + '\'' +
                ", attencDays=" + attencDays +
                ", sciktimes=" + sciktimes +
                ", affairtimes=" + affairtimes +
                ", latetimes=" + latetimes +
                ", leaveearlytimes=" + leaveearlytimes +
                ", minerstimes=" + minerstimes +
                '}';
    }
}
