package com.hongshu.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * 归档文章
 *
 * @author Hong_Shu995
 * @date 2021-12-16
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ArchiveDTO
{

    /**
     * id
     */
    private Integer id;

    /**
     * 标题
     */
    private String blogTitle;

    /**
     * 发表时间
     */
    private Date createTime;

}
