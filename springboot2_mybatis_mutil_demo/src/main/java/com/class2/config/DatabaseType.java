package com.class2.config;

/**
 * 列出数据源类型
 * 创建数据源类型的枚举DatabaseType该枚举类主要用来区分读写
 */
public enum DatabaseType {
    master("writer"),slave("read");

    DatabaseType(String name) {
        this.name = name;
    }

    private  String name ;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "DatabaseType{" +
                "name='" + name + '\'' +
                '}';
    }
}
