package com.example.vueblog.common.lang;
import lombok.Data;
import java.io.Serializable;

@Data
public class Result implements Serializable {
    private int code; //200是正常 400表示异常
    private String msg;
    private Object data;//返回数据

    //定义静态的方法，可以直接调用
    //成功：这个是成功只返回数据的情况
    // 这个也可以用多个构造函数来实现，这个思想用的较多
    public static Result succ( Object data){

        return succ(200,"操作成功",data);
    }
    //成功
    public static Result succ(int code,String msg,Object data){
        Result r = new Result();
        r.setCode(code);
        r.setMsg(msg);
        r.setData(data);
        return r;
    }

    //失败
    public static Result fail(String msg){
        return fail(400,msg,null);
    }
    //失败
    public static Result fail(String msg,Object data){
        return fail(400,msg,data);
    }
    //失败
    public static Result fail(int code,String msg,Object data){
        Result r = new Result();
        r.setCode(code);
        r.setMsg(msg);
        r.setData(data);
        return r;
    }




}
