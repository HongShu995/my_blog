package com.hongshu.controller;

import com.hongshu.dto.CategoryBackDTO;
import com.hongshu.dto.CategoryDTO;
import com.hongshu.dto.CategoryOptionDTO;
import com.hongshu.service.CategoryService;
import com.hongshu.vo.CategoryVO;
import com.hongshu.vo.ConditionVO;
import com.hongshu.vo.PageResult;
import com.hongshu.vo.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * 分类控制器
 *
 * @author Hong_Shu995
 * @date 2021-12-16
 */
@Api(tags = "分类模块")
@RestController
public class CategoryController
{
    @Autowired
    private CategoryService categoryService;

    /**
     * 查看分类列表
     *
     * @return {@link Result} 分类列表
     */
    @ApiOperation(value = "查看分类列表")
    @GetMapping("/categories")
    public Result listCategories() {
        PageResult<CategoryDTO> categories = categoryService.listCategories();
        return Result.ok().data("categories",categories);
    }


    /**
     * 查看后台分类列表
     *
     * @param condition 条件
     * @return {@link Result} 后台分类列表
     */
    @ApiOperation(value = "查看后台分类列表")
    @GetMapping("/admin/categories")
    public Result listBackCategories(ConditionVO condition) {
        PageResult<CategoryBackDTO> categoryBackList = categoryService.listBackCategories(condition);
        return Result.ok().data("categoryBackList",categoryBackList);
    }

    /**
     * 搜索文章分类
     *
     * @param condition 条件
     * @return {@link Result<CategoryOptionDTO>} 分类列表
     */
    @ApiOperation(value = "搜索文章分类")
    @GetMapping("/admin/categories/search")
    public Result listCategoriesBySearch(ConditionVO condition) {
        List<CategoryOptionDTO> categoryList = categoryService.listCategoriesBySearch(condition);
        return Result.ok().data("categoryList",categoryList);
    }

    /**
     * 添加或修改分类
     *
     * @param categoryVO 分类信息
     * @return {@link Result<>}
     */
    @ApiOperation(value = "添加或修改分类")
    @PostMapping("/admin/categories")
    public Result saveOrUpdateCategory(@Valid @RequestBody CategoryVO categoryVO) {
        categoryService.saveOrUpdateCategory(categoryVO);
        return Result.ok();
    }

    /**
     * 删除分类
     *
     * @param categoryIdList 分类id列表
     * @return {@link Result}
     */
    @ApiOperation(value = "删除分类")
    @DeleteMapping("/admin/categories")
    public Result deleteCategories(@RequestBody List<Integer> categoryIdList) {
        categoryService.deleteCategory(categoryIdList);
        return Result.ok();
    }
}
