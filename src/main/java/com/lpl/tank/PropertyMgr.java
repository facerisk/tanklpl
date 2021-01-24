package com.lpl.tank;

import java.io.IOException;
import java.util.Properties;

/**
 * @Classname PropertyMgr
 * @Description 配置文件类
 * @Date 2021/1/24 14:07
 * @Created by lplmbp
 */
public class PropertyMgr {
    //私有构造，单例模式
    private PropertyMgr() {
    }

    private static final Properties props = new Properties();

    static{

        try {
            props.load(PropertyMgr.class.getClassLoader().getResourceAsStream("config"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static Object get(String key){
        if(props ==null) {
            return  null;
        }
        return props.get(key);
    }

    //getInt()
    //getString()
    public static void main(String[] args) {
        System.out.println(PropertyMgr.get("initTankCount"));
    }
}
