package com.hongshu.controller;


import com.hongshu.dto.*;
import com.hongshu.enums.FilePathEnum;
import com.hongshu.service.BlogService;
import com.hongshu.strategy.context.UploadStrategyContext;
import com.hongshu.vo.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.List;

/**
 * 博客文章控制器
 *
 * @author Hong_Shu995
 * @date 2021-12-16
 */
@Api(tags = "文章模块")
@RestController
public class BlogController
{
    @Autowired
    private BlogService blogService;

    @Autowired
    private UploadStrategyContext uploadStrategyContext;

    /**
     * 查看首页文章
     *
     * @return {@link Result} 首页文章列表
     */
    @ApiOperation(value = "查看首页文章")
    @GetMapping("/blogs")
    public Result listArticles(ConditionVO conditionVO) {
        List<BlogHomeDTO> blogHomeList = blogService.listBlogs(conditionVO);
        return Result.ok().data("blogHomeList",blogHomeList);
    }

    /**
     * 根据id查看文章
     *
     * @param blogId 文章id
     * @return {@link Result} 文章信息
     */
    @ApiOperation("根据id查看文章")
    @GetMapping("/blogs/{blogId}")
    public Result getArticleById(@PathVariable("blogId") Integer blogId) {
        BlogDTO blog = blogService.getBlogById(blogId);
        return Result.ok().data("blog",blog);
    }

    /**
     * 查看后台文章
     *
     * @param conditionVO 条件
     * @return {@link Result} 后台文章列表
     */
    @ApiOperation(value = "查看后台文章")
    @GetMapping("/admin/blogs")
    public Result listBlogBacks(ConditionVO conditionVO) {
        PageResult<BlogBackDTO> blogBackList = blogService.listBlogBacks(conditionVO);
        return Result.ok().data("blogBackList",blogBackList);
    }

    /**
     * 根据id查看后台文章
     *
     * @param blogId 文章id
     * @return {@link Result} 后台文章
     */
    @ApiOperation(value = "根据id查看后台文章")
    @ApiImplicitParam(name = "blogId", value = "博客id", required = true, dataType = "Integer")
    @GetMapping("/admin/blogs/{blogId}")
    public Result getBlogBackById(@PathVariable("blogId") Integer blogId) {
        BlogVO blogBack = blogService.getBlogBackById(blogId);
        return Result.ok().data("blogBack",blogBack);
    }

    /**
     * 添加或修改文章
     *
     * @param blogVO 文章信息
     * @return {@link Result}
     */
    @ApiOperation(value = "添加或修改文章")
    @PostMapping("/admin/blogs")
    public Result saveOrUpdateBlog(@Valid @RequestBody BlogVO blogVO) {
        blogService.saveOrUpdateBlog(blogVO);
        return Result.ok();
    }

    /**
     * 修改文章置顶状态
     *
     * @param blogTopVO 文章置顶信息
     * @return {@link Result<>}
     */
    @ApiOperation(value = "修改文章置顶")
    @PutMapping("/admin/articles/top")
    public Result updateArticleTop(@Valid @RequestBody BlogTopVO blogTopVO) {
        blogService.updateBlogTop(blogTopVO);
        return Result.ok();
    }

    /**
     * 删除文章
     *
     * @param blogIdList 文章id列表
     * @return {@link Result}
     */
    @ApiOperation(value = "物理删除文章")
    @DeleteMapping("/admin/blogs")
    public Result deleteArticles(@RequestBody List<Integer> blogIdList) {
        blogService.deleteBlogs(blogIdList);
        return Result.ok();
    }

    /**
     * 恢复或删除文章
     *
     * @param deleteVO 逻辑删除信息
     * @return {@link Result}
     */
    @ApiOperation(value = "恢复或删除文章")
    @PutMapping("/admin/blogs")
    public Result updateArticleDelete(@Valid @RequestBody DeleteVO deleteVO) {
        blogService.updateBlogDelete(deleteVO);
        return Result.ok();
    }

    /**
     * 根据条件查询文章
     *
     * @param condition 条件
     * @return {@link Result} 文章列表
     */
    @ApiOperation(value = "根据条件查询文章")
    @GetMapping("/blogs/condition")
    public Result listBlogsByCondition(ConditionVO condition) {
        BlogPreviewListDTO blogPreviewList = blogService.listBlogsByCondition(condition);
        return Result.ok().data("blogPreviewList",blogPreviewList);
    }

    /**
     * 查看文章归档
     *
     * @return {@link Result} 文章归档列表
     */
    @ApiOperation(value = "查看文章归档")
    @GetMapping("/blogs/archives")
    public Result listArchives() {
        PageResult<ArchiveDTO> archiveList = blogService.listArchives();
        return Result.ok().data("archiveList",archiveList);
    }

    /**
     * 上传文章图片
     *
     * @param file 文件
     * @return {@link Result} 文章图片地址
     */
    @ApiOperation(value = "上传文章图片")
    @ApiImplicitParam(name = "file", value = "文章图片", required = true, dataType = "MultipartFile")
    @PostMapping("/admin/blogs/images")
    public Result saveBlogImages(MultipartFile file) {
        String path = uploadStrategyContext.executeUploadStrategy(file, FilePathEnum.ARTICLE.getPath());
        return Result.ok().data("path",path);
    }
}
