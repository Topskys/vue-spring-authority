package com.manong.utils;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
public class Result {
    private Boolean success;//是否成功
    private Integer code;//状态码
    private String message;//返回消息
    private Map<String,Object> data = new HashMap<String, Object>();//返回数据

    private Result(){

    }

    /**
     * 成功
     * @return
     */
    public static Result ok(){
        Result result = new Result();
        result.setSuccess(true);
        result.setCode(ResultCode.SUCCESS);
        result.setMessage("执行成功");
        return result;
    }

    /**
     * 失败
     * @return
     */
    public static Result error(){
        Result result = new Result();
        result.setSuccess(false);
        result.setCode(ResultCode.ERROR);
        result.setMessage("执行失败");
        return result;
    }

    /**
     * 设置是否成功
     * @param success
     * @return
     */
    public Result success(Boolean success){
        this.setSuccess(success);
        return this;
    }

    /**
     * 设置状态码
     * @param code
     * @return
     */
    public Result code(Integer code){
        this.setCode(code);
        return this;
    }

    /**
     * 设置返回消息
     * @param message
     * @return
     */
    public Result message(String message){
        this.setMessage(message);
        return this;
    }

    /**
     * 设置返回数据
     * @param key
     * @param value
     * @return
     */
    public Result data(String key,Object value){
        this.data.put(key,value);
        return this;
    }

    /**
     * 设置返回数据
     * @param map
     * @return
     */
    public Result data(Map<String,Object> map){
        this.setData(map);
        return this;
    }


}
