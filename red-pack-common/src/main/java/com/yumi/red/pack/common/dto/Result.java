package com.yumi.red.pack.common.dto;

import lombok.Data;

@Data
public class Result {
    private Integer code;
    private String msg;
    private Object data;


    public static Result success(Object data) {
        Result result = new Result();
        result.setCode(ResultCodeEnum.SUCCESS.getCode());
        result.setMsg(ResultCodeEnum.SUCCESS.getDesc());
        result.setData(data);
        return result;
    }


    public static Result fromCode(int code, Object data) {
        Result result = new Result();
        ResultCodeEnum resultCodeEnum = ResultCodeEnum.fromCode(code);
        result.setCode(resultCodeEnum.getCode());
        result.setMsg(resultCodeEnum.getDesc());
        result.setData(data);
        return result;
    }
}
