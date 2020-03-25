package com.xiaobao.pro_manage_sys.util;


import java.util.HashMap;
import java.util.Map;

/**
 * 数据接口返回对象
 */
public class Result {
    private Map<String,Object> data;//响应数据
    private Map<String,Object> meta;//响应提示信息

    public Result() {
    }


    public Result(Map<String,Object> data,String message, Integer code) {
        Map<String,Object> meta=new HashMap<>();
        meta.put("message",message);
        meta.put("code",code);


        this.meta = meta;
        this.data= data;
    }

    public Map<String,Object> getData() {
        return data;
    }

    public void setData(Map<String,Object> data) {
        this.data=data;
    }

    public Object getMeta() {
        return meta;
    }

    public void setMeta(String message,Integer code) {
        Map<String,Object> meta=new HashMap<>();
        meta.put("message",message);
        meta.put("code",code);

        this.meta = meta;
    }
}
