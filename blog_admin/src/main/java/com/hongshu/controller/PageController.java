package com.hongshu.controller;

import com.hongshu.service.PageService;
import com.hongshu.vo.PageVO;
import com.hongshu.vo.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * 页面控制器
 *
 * @author Hong_Shu995
 * @date 2021-12-28
 */
@Api(tags = "页面模块")
@RestController
public class PageController
{
    @Autowired
    private PageService pageService;

    /**
     * 获取页面列表
     *
     * @return {@link Result<PageVO>}
     */
    @ApiOperation(value = "获取页面列表")
    @GetMapping("/admin/pages")
    public Result listPages() {
        List<PageVO> pageList = pageService.listPages();
        return Result.ok().data("pageList",pageList);
    }

    /**
     * 删除页面
     *
     * @param pageId 页面id
     * @return {@link Result <>}
     */
    @ApiOperation(value = "删除页面")
    @ApiImplicitParam(name = "pageId", value = "页面id", required = true, dataType = "Integer")
    @DeleteMapping("/admin/pages/{pageId}")
    public Result deletePage(@PathVariable("pageId") Integer pageId)
    {
        pageService.deletePage(pageId);
        return Result.ok();
    }

    /**
     * 保存或更新页面
     *
     * @param pageVO 页面信息
     * @return {@link Result<>}
     */
    @ApiOperation(value = "保存或更新页面")
    @PostMapping("/admin/pages")
    public Result saveOrUpdatePage(@Valid @RequestBody PageVO pageVO)
    {
        pageService.saveOrUpdatePage(pageVO);
        return Result.ok();
    }

}
