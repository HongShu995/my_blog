package com.hongshu.controller;

import com.hongshu.dto.BlogHomeInfoDTO;
import com.hongshu.enums.FilePathEnum;
import com.hongshu.service.BlogInfoService;
import com.hongshu.strategy.context.UploadStrategyContext;
import com.hongshu.vo.BlogInfoVO;
import com.hongshu.vo.Result;
import com.hongshu.vo.WebsiteConfigVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;

/**
 * 博客信息控制器
 *
 * @author Hong_Shu995
 * @date 2021-12-13
 */
@Api(tags = "博客信息模块")
@RestController
public class BlogInfoController
{
    @Autowired
    private BlogInfoService blogInfoService;

    @Autowired
    private UploadStrategyContext uploadStrategyContext;

    /**
     * 查看博客信息
     *
     * @return {@link Result} 博客信息
     */
    @ApiOperation(value = "查看博客信息")
    @GetMapping("/")
    public Result getBlogHomeInfo()
    {
        BlogHomeInfoDTO blogHomeInfo = blogInfoService.getBlogHomeInfo();
        return Result.ok().data("blogHomeInfo",blogHomeInfo);
    }

    /**
     * 查看后台信息
     *
     * @return {@link Result}
     */
    @ApiOperation(value = "查看后台信息")
    @GetMapping("/admin/blogBackInfo")
    public Result getBlogBackInfo()
    {
        return Result.ok().data("backInfo",blogInfoService.getBlogBackInfo());
    }

    /**
     * 获取网站配置
     *
     * @return {@link Result} 网站配置
     */
    @ApiOperation(value = "获取网站配置")
    @GetMapping("/admin/website/config")
    public Result getWebsiteConfig()
    {
        WebsiteConfigVO websiteConfig = blogInfoService.getWebsiteConfig();
        return Result.ok().data("WebsiteConfig",websiteConfig);
    }

    /**
     * 更新网站配置
     *
     * @param websiteConfigVO 网站配置信息
     * @return {@link Result}
     */
    @ApiOperation(value = "更新网站配置")
    @PutMapping("/admin/website/config")
    public Result updateWebsiteConfig(@Valid @RequestBody WebsiteConfigVO websiteConfigVO)
    {
        blogInfoService.updateWebsiteConfig(websiteConfigVO);
        return Result.ok();
    }

    /**
     * 查看关于我信息
     *
     * @return {@link Result<String>} 关于我信息
     */
    @ApiOperation(value = "查看关于我信息")
    @GetMapping("/about")
    public Result getAbout()
    {
        String about = blogInfoService.getAbout();
        return Result.ok().data("about",about);
    }

    /**
     * 获取后台登录登录页面
     *
     * @return {@link Result<String>} 登录页面地址
     */
    @ApiOperation(value = "获取后台登录登录页面")
    @GetMapping("/adminCover")
    public Result getAdminCover()
    {
        return Result.ok().data("path",blogInfoService.getAdminCover());
    }

    /**
     * 修改关于我信息
     *
     * @param blogInfoVO 博客信息
     * @return {@link Result}
     */
    @ApiOperation(value = "修改关于我信息")
    @PutMapping("/admin/about")
    public Result updateAbout(@Valid @RequestBody BlogInfoVO blogInfoVO)
    {
        blogInfoService.updateAbout(blogInfoVO);
        return Result.ok();
    }

    /**
     * 上传博客配置图片
     *
     * @param file 文件
     * @return {@link Result<String>} 博客配置图片
     */
    @ApiOperation(value = "上传博客配置图片")
    @ApiImplicitParam(name = "file", value = "图片", required = true, dataType = "MultipartFile")
    @PostMapping("/admin/config/images")
    public Result savePhotoAlbumCover(MultipartFile file)
    {
        String path = uploadStrategyContext.executeUploadStrategy(file, FilePathEnum.CONFIG.getPath());
        return Result.ok().data("path",path);
    }
}
