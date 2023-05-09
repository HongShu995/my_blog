package com.hongshu.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 分页对象
 *
 * @author Hong_Shu995
 * @date 2021-12-14
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PageResult<T>
{
    /**
     * 分页列表
     */
    @ApiModelProperty(name = "recordList", value = "分页列表", required = true, dataType = "List<T>")
    private List<T> recordList;

    /**
     * Long总数
     */
    @ApiModelProperty(name = "count", value = "Long总数", required = true, dataType = "Long")
    private Long count;

    /**
     * Integer总数
     */
    @ApiModelProperty(name = "smallCount", value = "Integer总数", required = true, dataType = "Integer")
    private Integer smallCount;

    public PageResult(List<T> recordList, Long count)
    {
        this.recordList = recordList;
        this.count = count;
    }

    public PageResult(List<T> recordList, Integer smallCount)
    {
        this.recordList = recordList;
        this.smallCount = smallCount;
    }
}
