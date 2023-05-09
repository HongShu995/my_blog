package com.hongshu.controller;

import com.hongshu.dto.TagBackDTO;
import com.hongshu.dto.TagDTO;
import com.hongshu.service.TagService;
import com.hongshu.vo.ConditionVO;
import com.hongshu.vo.PageResult;
import com.hongshu.vo.Result;
import com.hongshu.vo.TagVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * 类名
 *
 * @author Hong_Shu995
 * @date 2021-12-16
 */
@Api(tags = "标签模块")
@RestController
public class TagController
{
    @Autowired
    private TagService tagService;


    /**
     * 查询标签列表
     *
     * @return {@link Result} 标签列表
     */
    @ApiOperation(value = "查询标签列表")
    @GetMapping("/tags")
    public Result listTags() {
        PageResult<TagDTO> tags = tagService.listTags();
        return Result.ok().data("tags",tags);
    }

    /**
     * 查询后台标签列表
     *
     * @param condition 条件
     * @return {@link Result} 标签列表
     */
    @ApiOperation(value = "查询后台标签列表")
    @GetMapping("/admin/tags")
    public Result listTagBackDTO(ConditionVO condition) {
        PageResult<TagBackDTO> tagBackList = tagService.listTagBackDTO(condition);
        return Result.ok().data("tagBackList",tagBackList);
    }

    /**
     * 搜索文章标签
     *
     * @param condition 条件
     * @return {@link Result 标签列表
     */
    @ApiOperation(value = "搜索文章标签")
    @GetMapping("/admin/tags/search")
    public Result listTagsBySearch(ConditionVO condition) {
        List<TagDTO> tagDTOS = tagService.listTagsBySearch(condition);
        return Result.ok().data("tagDTOS",tagDTOS);
    }

    /**
     * 添加或修改标签
     *
     * @param tagVO 标签信息
     * @return {@link Result<>}
     */
    @ApiOperation(value = "添加或修改标签")
    @PostMapping("/admin/tags")
    public Result saveOrUpdateTag(@Valid @RequestBody TagVO tagVO) {
        tagService.saveOrUpdateTag(tagVO);
        return Result.ok();
    }

    /**
     * 删除标签
     *
     * @param tagIdList 标签id列表
     * @return {@link Result<>}
     */
    @ApiOperation(value = "删除标签")
    @DeleteMapping("/admin/tags")
    public Result deleteTag(@RequestBody List<Integer> tagIdList) {
        tagService.deleteTag(tagIdList);
        return Result.ok();
    }

}
