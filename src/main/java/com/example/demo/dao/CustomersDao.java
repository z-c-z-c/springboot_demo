package com.example.demo.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo.entity.Customers;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CustomersDao extends BaseMapper<Customers> {

}
