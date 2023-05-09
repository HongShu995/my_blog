package com.hongshu.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hongshu.dto.LabelOptionDTO;
import com.hongshu.dto.ResourceDTO;
import com.hongshu.entity.Resource;
import com.hongshu.vo.ConditionVO;
import com.hongshu.vo.ResourceVO;

import java.util.List;

/**
 * 类名
 *
 * @author Hong_Shu995
 * @date 2021-12-16
 */
public interface ResourceService extends IService<Resource>
{
    /**
     * 导入swagger权限
     */
    void importSwagger();

    /**
     * 添加或修改资源
     *
     * @param resourceVO 资源对象
     */
    void saveOrUpdateResource(ResourceVO resourceVO);

    /***
     * 删除资源
     *
     @param resourceId 资源id
     */
    void deleteResource(Integer resourceId);

    /**
     * 查看资源列表
     *
     * @param conditionVO 条件
     * @return 资源列表
     */
    List<ResourceDTO> listResources(ConditionVO conditionVO);

    /**
     * 查看资源选项
     *
     * @return 资源选项
     */
    List<LabelOptionDTO> listResourceOption();
}
