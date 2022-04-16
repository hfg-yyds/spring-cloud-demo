package com.hfg.config;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

/**
 * Create with Intellij IDEA.
 * Description：统一返回结果的类
 * User:Zero
 * Date:2021/3/23
 * Time:16:51
 */
//lombok插件的注解
@Data
public class RResult {
    @ApiModelProperty(value = "是否成功")
    private Boolean success;

    @ApiModelProperty(value = "返回码")
    private Integer code;

    @ApiModelProperty(value = "返回消息")
    private String message;

    /**
     * 参考使用泛型 而不是使用<String,Object>
     * private
     */
    @ApiModelProperty(value = "返回数据")
    private Map<String, Object> data = new HashMap<String, Object>();

    //私有化构造方法
    private RResult(){

    }

    //链式编程   R.ok().success()....

    public static RResult ok(){
        RResult rResult = new RResult();
        rResult.setSuccess(true);
        rResult.setCode(ResultCode.SUCCESS);
        rResult.setMessage("成功");
        return rResult;
    }

    public static RResult error(){
        RResult rResult = new RResult();
        rResult.setSuccess(false);
        rResult.setCode(ResultCode.ERROR);
        rResult.setMessage("失败");
        return rResult;
    }

    //当前类的对象
    public RResult success(Boolean success){
        this.setSuccess(success);
        return this;
    }

    public RResult message(String message){
        this.setMessage(message);
        return this;
    }

    public RResult code(Integer code){
        this.setCode(code);
        return this;
    }

    public RResult data(String key, Object value){
        this.data.put(key, value);
        return this;
    }

    public RResult data(Map<String, Object> map){
        this.setData(map);
        return this;
    }

}

