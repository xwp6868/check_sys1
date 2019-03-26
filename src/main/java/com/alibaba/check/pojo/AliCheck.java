package com.alibaba.check.pojo;



import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AliCheck implements Serializable {

    private Integer checkId; //主键

    private String wbNumber;//外包工号

    private String workNumber;//工号

    //@JSONField(format = "yyyy-MM-dd HH:mm:ss")
    //@JsonFormat(pattern = "HH:mm:ss")
    //@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date upTime;//上班时间

   // @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date downTime;//下班时间

    private Date workTime;//出勤时间

    private String workState;//工作状态   0 正常  1 病假  2 事假  3迟到 4 早退  5矿工

    private Integer userId;

    public Integer getCheckId() {
        return checkId;
    }

    public void setCheckId(Integer checkId) {
        this.checkId = checkId;
    }

    public String getWbNumber() {
        return wbNumber;
    }

    public void setWbNumber(String wbNumber) {
        this.wbNumber = wbNumber == null ? null : wbNumber.trim();
    }

   // @JsonFormat(timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
    public Date getUpTime() {
        return upTime;
    }

    public void setUpTime(Date upTime) {
        this.upTime = upTime;
    }

    //@JsonFormat(timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
    public Date getDownTime() {
        return downTime;
    }

    public void setDownTime(Date downTime) {
        this.downTime = downTime;
    }


    public Date getWorkTime() { return workTime; }

    public void setWorkTime(Date workTime) { this.workTime = workTime; }

    public String getWorkState() {
        return workState;
    }

    public String setWorkState(String workState) {
        this.workState = workState == null ? null : workState.trim();
        return workState;
    }
    public String getWorkNumber() {
        return workNumber;
    }

    public void setWorkNumber(String workNumber) {
        this.workNumber = workNumber;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String formateTime(Date date) {
        SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd HH:mm");
        return df.format(date);
    }
    public String formate(Date date) {
        SimpleDateFormat df=new SimpleDateFormat("HH:mm");
        return df.format(date);
    }

    @Override
    public String toString() {
        return "AliCheck{" +
                "checkId=" + checkId +
                ", wbNumber='" + wbNumber + '\'' +
                ", workNumber='" + workNumber + '\'' +
                ", upTime=" + upTime +
                ", downTime=" + downTime +
                ", workTime=" + workTime +
                ", workState='" + workState + '\'' +
                ", userId=" + userId +
                '}';
    }
}