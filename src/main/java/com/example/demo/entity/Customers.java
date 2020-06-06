package com.example.demo.entity;


import lombok.Data;

import java.util.Date;

@Data
public class Customers {
    //主键id

    public String id;

    //姓名
    public String name;

    //性别
    public String sex;

    //年龄
    public Integer age;

    //地址
    public String address;

    //联系方式
    public String telNum;

    //创建时间
    public Date createTime;

    //修改时间
    public Date modifyTime;
}
