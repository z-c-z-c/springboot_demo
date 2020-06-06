package com.example.demo.datasource;

public enum DataSourceEnum {
    DB1("demo1"),DB2("demo2");

    private String value;

    DataSourceEnum(String value){this.value=value;}

    public String getValue() {
        return value;
    }
}
