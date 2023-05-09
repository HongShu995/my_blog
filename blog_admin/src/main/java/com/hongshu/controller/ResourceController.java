package com.hongshu.controller;

import com.hongshu.dto.LabelOptionDTO;
import com.hongshu.dto.ResourceDTO;
import com.hongshu.service.ResourceService;
import com.hongshu.vo.ConditionVO;
import com.hongshu.vo.ResourceVO;
import com.hongshu.vo.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * 资源控制器
 *
 * @author Hong_Shu995
 * @date 2021-12-16
 */
@Api(tags = "资源模块")
@RestController
public class ResourceController
{
    @Autowired
    private ResourceService resourceService;

    /**
     * 导入swagger接口
     *
     * @return {@link Result} 操作成功
     */
    @ApiOperation(value = "导入swagger接口")
    @GetMapping("/admin/resources/import/swagger")
    public Result importSwagger() {
        resourceService.importSwagger();
        return Result.ok();
    }

    /**
     * 查看资源列表
     *
     * @return {@link Result} 资源列表
     */
    @ApiOperation("查看资源列表")
    @GetMapping("/admin/resources")
    public Result listResources(ConditionVO conditionVO) {
        List<ResourceDTO> resources = resourceService.listResources(conditionVO);
        return Result.ok().data("resources",resources);
    }

    /**
     * 删除资源
     *
     * @param resourceId 资源id
     * @return {@link Result}
     */
    @ApiOperation("删除资源")
    @DeleteMapping("/admin/resources/{resourceId}")
    public Result deleteResource(@PathVariable("resourceId") Integer resourceId) {
        resourceService.deleteResource(resourceId);
        return Result.ok();
    }

    /**
     * 新增或修改资源
     *
     * @param resourceVO 资源信息
     * @return {@link Result<>}
     */
    @ApiOperation("新增或修改资源")
    @PostMapping("/admin/resources")
    public Result saveOrUpdateResource(@RequestBody @Valid ResourceVO resourceVO) {
        resourceService.saveOrUpdateResource(resourceVO);
        return Result.ok();
    }

    /**
     * 查看角色资源选项
     *
     * @return {@link Result<LabelOptionDTO>} 角色资源选项
     */
    @ApiOperation("查看角色资源选项")
    @GetMapping("/admin/role/resources")
    public Result listResourceOption() {
        List<LabelOptionDTO> labelRoleOption = resourceService.listResourceOption();
        return Result.ok().data("labelRoleOption",labelRoleOption);
    }
}
