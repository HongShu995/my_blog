package com.hongshu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hongshu.dao.BlogDao;
import com.hongshu.dao.CategoryDao;
import com.hongshu.dto.CategoryBackDTO;
import com.hongshu.dto.CategoryDTO;
import com.hongshu.dto.CategoryOptionDTO;
import com.hongshu.entity.Blog;
import com.hongshu.entity.Category;
import com.hongshu.exception.BlogException;
import com.hongshu.service.CategoryService;
import com.hongshu.util.BeanCopyUtils;
import com.hongshu.util.PageUtils;
import com.hongshu.vo.CategoryVO;
import com.hongshu.vo.ConditionVO;
import com.hongshu.vo.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

/**
 * 类名
 *
 * @author Hong_Shu995
 * @date 2021-12-16
 */
@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryDao, Category> implements CategoryService
{
    @Autowired
    private CategoryDao categoryDao;

    @Autowired
    private BlogDao blogDao;

    @Override
    public PageResult<CategoryDTO> listCategories()
    {
        return new PageResult<>(categoryDao.listCategoryDTO(), categoryDao.selectCount(null));
    }

    @Override
    public PageResult<CategoryBackDTO> listBackCategories(ConditionVO condition)
    {
        // 查询分类数量
        Long count = categoryDao.selectCount(new LambdaQueryWrapper<Category>()
                .like(StringUtils.isNotBlank(condition.getKeywords()), Category::getCategoryName, condition.getKeywords()));
        if (count == 0)
        {
            return new PageResult<>();
        }
        // 分页查询分类列表
        List<CategoryBackDTO> categoryList = categoryDao.listCategoryBackDTO(PageUtils.getLimitCurrent(), PageUtils.getSize(), condition);
        return new PageResult<>(categoryList, count);
    }

    @Override
    public List<CategoryOptionDTO> listCategoriesBySearch(ConditionVO condition)
    {
        // 搜索分类
        List<Category> categoryList = categoryDao.selectList(new LambdaQueryWrapper<Category>()
                .like(StringUtils.isNotBlank(condition.getKeywords()), Category::getCategoryName, condition.getKeywords())
                .orderByDesc(Category::getId));
        return BeanCopyUtils.copyList(categoryList, CategoryOptionDTO.class);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void deleteCategory(List<Integer> categoryIdList)
    {
        // 查询分类id下是否有文章
        Long count = blogDao.selectCount(new LambdaQueryWrapper<Blog>()
                .in(Blog::getCategoryId, categoryIdList));
        if (count > 0) {
            throw new BlogException("删除失败，该分类下存在文章");
        }
        categoryDao.deleteBatchIds(categoryIdList);
    }

    @Override
    public void saveOrUpdateCategory(CategoryVO categoryVO)
    {
        // 判断分类名重复
        Category existCategory = categoryDao.selectOne(new LambdaQueryWrapper<Category>()
                .select(Category::getId)
                .eq(Category::getCategoryName, categoryVO.getCategoryName()));
        if (Objects.nonNull(existCategory) && !existCategory.getId().equals(categoryVO.getId())) {
            throw new BlogException("分类名已存在");
        }
        Category category = Category.builder()
                .id(categoryVO.getId())
                .categoryName(categoryVO.getCategoryName())
                .build();
        this.saveOrUpdate(category);
    }
}
