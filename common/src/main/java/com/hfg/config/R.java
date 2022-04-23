package com.hfg.config;

import cn.hutool.json.JSONUtil;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Data
@Slf4j
public class R<T> {

    private String code;

    private String message;

    private T data;

    //构造方法私有化 别人不能使用该类new方法
    private R(ResultCode resultCode){
        code = resultCode.getCode();
        message = resultCode.getMessage();
    }

    //静态方法
    public static <V>  R<V> ok(){
        return new R<>(ResultCode.SUCCESS);
    }

    //静态方法
    public static <V>  R<V> ok(V theData){
        final R<V> ok = ok();
        ok.data = theData;
        return ok;
    }

    public static R<?> error(){
        return new R<>(ResultCode.DEFAULT_ERROR);
    }

    public static R<?> error(ResultCode resultCode){
        return new R<>(resultCode);
    }

    public static R<?> error(Throwable e){
        final R<?> result = error();
        result.message = e.getMessage();
        log.error("异常信息:"+JSONUtil.toJsonStr(e));
        return result;
    }


    public static R<?> run(Runnable runnable){
        try{
            runnable.run();
            return ok();
        }catch (Throwable e){
            log.error("异常信息:"+JSONUtil.toJsonStr(e));
            return error(e);
        }
    }

    public static <V>  R<V> run(RunnableAndGetResult<V> runnable){
        try{
            return ok(runnable.run());
        }catch (Throwable e){
            log.error("异常信息:"+JSONUtil.toJsonStr(e));
            return (R<V>)error(e);
        }
    }

    @FunctionalInterface
    public interface RunnableAndGetResult<P>{
        P run();
    }

}


