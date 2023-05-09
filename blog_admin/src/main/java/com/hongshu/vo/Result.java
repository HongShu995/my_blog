package com.hongshu.vo;

import com.hongshu.enums.StatusCodeEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Map;

import static com.hongshu.enums.StatusCodeEnum.ERROR;
import static com.hongshu.enums.StatusCodeEnum.SUCCESS;

/**
 * 接口返回类
 *
 * @author Hong_shu995
 * @date 2021/12/03
 */
@Data
@NoArgsConstructor
@ApiModel(description = "返回结果")
public class Result
{
    /**
     * 返回状态
     */
    @ApiModelProperty(name = "flag", value = "返回状态", required = true, dataType = "Boolean")
    private Boolean flag;

    /**
     * 状态码
     */
    @ApiModelProperty(name = "code", value = "状态码", required = true, dataType = "Integer")
    private Integer code;

    /**
     * 返回信息
     */
    @ApiModelProperty(name = "message", value = "返回信息", required = true, dataType = "String")
    private String message;

    /**
     * 返回数据
     */
    @ApiModelProperty(name = "data", value = "返回数据", dataType = "Map<String,Object>")
    private Map<String,Object> data=new HashMap<>();;

    public static Result state(boolean flag,StatusCodeEnum statusCodeEnum)
    {
        Result result = new Result();
        result.setFlag(flag);
        result.setCode(statusCodeEnum.getCode());
        result.setMessage(statusCodeEnum.getDesc());
        return result;
    }

    public static Result ok()
    {
        return state(true,SUCCESS);
    }

    public static Result error()
    {
        return state(false,ERROR);
    }

    public Result flag(boolean flag)
    {
        this.setFlag(flag);
        return this;
    }

    public Result code(Integer code)
    {
        this.setCode(code);
        return this;
    }

    public Result message(String message)
    {
        this.setMessage(message);
        return this;
    }

    public Result codeAndMessage(Integer code,String message)
    {
        this.setCode(code);
        this.setMessage(message);
        return this;
    }

    public Result data(String key,Object data)
    {
        this.data.put(key,data);
        return this;
    }

}
