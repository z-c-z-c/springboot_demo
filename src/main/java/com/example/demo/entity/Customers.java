package com.example.demo.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.springframework.data.relational.core.mapping.Table;

import java.util.Date;

@Data
@TableName("Customers")
public class Customers {
    //主键id

    @TableId(type = IdType.UUID)
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
    @TableField("tel_num")
    public String telNum;

    //创建时间
    @TableField("create_time")
    public Date createTime;

    //修改时间
    @TableField("modify_time")
    public Date modifyTime;
}
