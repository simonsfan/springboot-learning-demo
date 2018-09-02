package com.simons.cn.springbootdemo.bean;

import java.util.Date;

public class MovieInvalid {
    private Long id;

    private String name;

    private Date createTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public MovieInvalid(String name, Date createTime) {
        this.name = name;
        this.createTime = createTime;
    }
}