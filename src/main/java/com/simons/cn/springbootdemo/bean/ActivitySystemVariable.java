package com.simons.cn.springbootdemo.bean;


import java.sql.Timestamp;
import java.util.Date;

public class ActivitySystemVariable {
    private Integer id;

    private String name;

    private String svKey;

    private String value;

    private String memo;

    private String typeCode;

    private Byte cataLog;

    private Timestamp createTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSvKey() {
        return svKey;
    }

    public void setSvKey(String svKey) {
        this.svKey = svKey;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public String getTypeCode() {
        return typeCode;
    }

    public void setTypeCode(String typeCode) {
        this.typeCode = typeCode;
    }

    public Byte getCataLog() {
        return cataLog;
    }

    public void setCataLog(Byte cataLog) {
        this.cataLog = cataLog;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "ActivitySystemVariable{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", svKey='" + svKey + '\'' +
                ", value='" + value + '\'' +
                ", memo='" + memo + '\'' +
                ", typeCode='" + typeCode + '\'' +
                ", cataLog=" + cataLog +
                ", createTime=" + createTime +
                '}';
    }
}
