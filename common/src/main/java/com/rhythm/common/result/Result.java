package com.rhythm.common.result;

import com.rhythm.common.Enum.ResultCode;
import lombok.Data;

import java.util.List;

@Data
public class Result<T> {

    private Boolean success;

    private Integer code;

    private String message;

    private List<T> data;

    private long total;

    public static Result ok() {
        Result result = new Result();
        result.setCode(ResultCode.SUCCESS.getCode());
        return result;
    }

    public static Result error() {
        Result result = new Result();
        result.setCode(ResultCode.ERROR.getCode());
        return result;
    }
}


