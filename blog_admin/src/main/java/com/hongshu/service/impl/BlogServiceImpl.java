package com.hongshu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hongshu.dao.BlogDao;
import com.hongshu.dao.BlogTagDao;
import com.hongshu.dao.CategoryDao;
import com.hongshu.dao.TagDao;
import com.hongshu.dto.*;
import com.hongshu.entity.Blog;
import com.hongshu.entity.BlogTag;
import com.hongshu.entity.Category;
import com.hongshu.entity.Tag;
import com.hongshu.exception.BlogException;
import com.hongshu.service.BlogService;
import com.hongshu.service.BlogTagService;
import com.hongshu.service.TagService;
import com.hongshu.util.BeanCopyUtils;
import com.hongshu.util.PageUtils;
import com.hongshu.util.UserUtil;
import com.hongshu.vo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static com.hongshu.constant.CommonConstant.FALSE;
import static com.hongshu.enums.BlogStatusEnum.DRAFT;
import static com.hongshu.enums.BlogStatusEnum.PUBLIC;

/**
 * 博客文章服务
 *
 * @author Hong_Shu995
 * @date 2021-12-16
 */
@Service
public class BlogServiceImpl extends ServiceImpl<BlogDao, Blog> implements BlogService
{
    @Autowired
    private BlogDao blogDao;

    @Autowired
    private BlogService blogService;

    @Autowired
    private CategoryDao categoryDao;

    @Autowired
    private TagDao tagDao;

    @Autowired
    private TagService tagService;

    @Autowired
    private BlogTagDao blogTagDao;

    @Autowired
    private BlogTagService blogTagService;

    @Override
    public PageResult<ArchiveDTO> listArchives()
    {
        Page<Blog> page = new Page<>(PageUtils.getCurrent(), PageUtils.getSize());
        // 获取分页数据
        Page<Blog> blogPage = blogDao.selectPage(page, new LambdaQueryWrapper<Blog>()
                .select(Blog::getId, Blog::getBlogTitle, Blog::getCreateTime)
                .orderByDesc(Blog::getCreateTime)
                .eq(Blog::getIsDelete, FALSE)
                .eq(Blog::getStatus, PUBLIC.getStatus()));
        List<ArchiveDTO> archiveDTOList = BeanCopyUtils.copyList(blogPage.getRecords(), ArchiveDTO.class);
        return new PageResult<>(archiveDTOList, (int) blogPage.getTotal());
    }

    @Override
    public PageResult<BlogBackDTO> listBlogBacks(ConditionVO condition)
    {
        // 查询文章总量
        Integer count = blogDao.countBlogBacks(condition);
        if (count == 0) {
            return new PageResult<>();
        }
        // 查询后台文章
        List<BlogBackDTO> blogBackDTOList = blogDao.listBlogBacks(PageUtils.getLimitCurrent(), PageUtils.getSize(), condition);
        // 查询文章点赞量和浏览量
        //Map<Object, Double> viewsCountMap = redisService.zAllScore(ARTICLE_VIEWS_COUNT);
        //Map<String, Object> likeCountMap = redisService.hGetAll(ARTICLE_LIKE_COUNT);
        // 封装点赞量和浏览量
        //blogBackDTOList.forEach(item -> {
        //    Double viewsCount = viewsCountMap.get(item.getId());
        //    if (Objects.nonNull(viewsCount)) {
        //        item.setViewsCount(viewsCount.intValue());
        //    }
        //    item.setLikeCount((Integer) likeCountMap.get(item.getId().toString()));
        //});
        return new PageResult<>(blogBackDTOList, count);
    }

    @Override
    public List<BlogHomeDTO> listBlogs(ConditionVO condition)
    {
        return blogDao.listBlogs(condition.getCurrent(), condition.getSize());
    }

    @Override
    public BlogPreviewListDTO listBlogsByCondition(ConditionVO condition)
    {
        // 查询文章
        List<BlogPreviewDTO> blogPreviewDTOList = blogDao.listBlogsByCondition(condition.getCurrent(), condition.getSize(), condition);
        // 搜索条件对应名(标签或分类名)
        String name;
        if (Objects.nonNull(condition.getCategoryId())) {
            name = categoryDao.selectOne(new LambdaQueryWrapper<Category>()
                            .select(Category::getCategoryName)
                            .eq(Category::getId, condition.getCategoryId()))
                    .getCategoryName();
        } else {
            name = tagService.getOne(new LambdaQueryWrapper<Tag>()
                            .select(Tag::getTagName)
                            .eq(Tag::getId, condition.getTagId()))
                    .getTagName();
        }
        return BlogPreviewListDTO.builder()
                .blogPreviewDTOList(blogPreviewDTOList)
                .name(name)
                .build();
    }

    @Override
    public BlogVO getBlogBackById(Integer blogId)
    {
        // 查询文章信息
        Blog blog = blogDao.selectById(blogId);
        // 查询文章分类
        Category category = categoryDao.selectById(blog.getCategoryId());
        String categoryName = null;
        if (Objects.nonNull(category)) {
            categoryName = category.getCategoryName();
        }
        // 查询文章标签
        List<String> tagNameList = tagDao.listTagNameByBlogId(blogId);
        // 封装数据
        BlogVO blogVO = BeanCopyUtils.copyObject(blog, BlogVO.class);
        blogVO.setCategoryName(categoryName);
        blogVO.setTagNameList(tagNameList);
        return blogVO;
    }

    @Override
    public BlogDTO getBlogById(Integer blogId)
    {
        // 查询推荐文章
        //CompletableFuture<List<BlogRecommendDTO>> recommendArticleList = CompletableFuture
        //        .supplyAsync(() -> blogDao.listRecommendBlogs(blogId));
        // 查询最新文章
        //CompletableFuture<List<BlogRecommendDTO>> newestArticleList = CompletableFuture
        //        .supplyAsync(() -> {
        //            List<Blog> articleList = blogDao.selectList(new LambdaQueryWrapper<Blog>()
        //                    .select(Blog::getId, Blog::getBlogTitle, Blog::getBlogCover, Blog::getCreateTime)
        //                    .eq(Blog::getIsDelete, FALSE)
        //                    .eq(Blog::getStatus, PUBLIC.getStatus())
        //                    .orderByDesc(Blog::getId)
        //                    .last("limit 5"));
        //            return BeanCopyUtils.copyList(articleList, BlogRecommendDTO.class);
        //        });
        // 查询id对应文章
        BlogDTO blog = blogDao.getBlogById(blogId);
        if (Objects.isNull(blog)) {
            throw new BlogException("文章不存在");
        }
        // 更新文章浏览量
        //updateArticleViewsCount(blogId);
        // 查询上一篇下一篇文章
        //Blog lastBlog = blogDao.selectOne(new LambdaQueryWrapper<Blog>()
        //        .select(Blog::getId, Blog::getBlogTitle, Blog::getBlogCover)
        //        .eq(Blog::getIsDelete, FALSE)
        //        .eq(Blog::getStatus, PUBLIC.getStatus())
        //        .lt(Blog::getId, blogId)
        //        .orderByDesc(Blog::getId)
        //        .last("limit 1"));
        //Blog nextBlog = blogDao.selectOne(new LambdaQueryWrapper<Blog>()
        //        .select(Blog::getId, Blog::getBlogTitle, Blog::getBlogCover)
        //        .eq(Blog::getIsDelete, FALSE)
        //        .eq(Blog::getStatus, PUBLIC.getStatus())
        //        .gt(Blog::getId, blogId)
        //        .orderByAsc(Blog::getId)
        //        .last("limit 1"));
        //blog.setLastBlog(BeanCopyUtils.copyObject(lastBlog, BlogPaginationDTO.class));
        //blog.setNextBlog(BeanCopyUtils.copyObject(nextBlog, BlogPaginationDTO.class));
        // 封装点赞量和浏览量
        //Double score = redisService.zScore(ARTICLE_VIEWS_COUNT, articleId);
        //if (Objects.nonNull(score)) {
        //    blog.setViewsCount(score.intValue());
        //}
        //blog.setLikeCount((Integer) redisService.hGet(ARTICLE_LIKE_COUNT, articleId.toString()));
        // 封装文章信息
        //try {
        //    blog.setRecommendArticleList(recommendArticleList.get());
        //    blog.setNewestArticleList(newestArticleList.get());
        //} catch (Exception e) {
        //    e.printStackTrace();
        //}
        return blog;
    }

    @Override
    public void saveBlogLike(Integer blogId)
    {

    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void saveOrUpdateBlog(BlogVO blogVO)
    {
        // 保存文章分类
        Category category = saveBlogCategory(blogVO);
        // 保存或修改文章
        Blog blog = BeanCopyUtils.copyObject(blogVO, Blog.class);
        if (Objects.nonNull(category)) {
            blog.setCategoryId(category.getId());
        }
        blog.setUserId(UserUtil.getLoginUser().getUserInfoId());
        blogService.saveOrUpdate(blog);
        // 保存文章标签
        saveBlogTag(blogVO, blog.getId());
    }

    @Override
    public void updateBlogTop(BlogTopVO blogTopVO)
    {
        Blog blog = Blog.builder()
                .id(blogTopVO.getId())
                .isTop(blogTopVO.getIsTop())
                .build();
        blogDao.updateById(blog);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void updateBlogDelete(DeleteVO deleteVO)
    {
        // 修改文章逻辑删除状态
        List<Blog> articleList = deleteVO.getIdList().stream()
                .map(id -> Blog.builder()
                        .id(id)
                        .isTop(FALSE)
                        .isDelete(deleteVO.getIsDelete())
                        .build())
                .collect(Collectors.toList());
        blogService.updateBatchById(articleList);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void deleteBlogs(List<Integer> blogIdList)
    {
        // 删除文章标签关联
        blogTagDao.delete(new LambdaQueryWrapper<BlogTag>()
                .in(BlogTag::getBlogId, blogIdList));
        // 删除文章
        blogDao.deleteBatchIds(blogIdList);
    }

    /**
     * 保存文章分类
     *
     * @param blogVO 文章信息
     * @return {@link Category} 文章分类
     */
    private Category saveBlogCategory(BlogVO blogVO) {
        // 判断分类是否存在
        Category category = categoryDao.selectOne(new LambdaQueryWrapper<Category>()
                .eq(Category::getCategoryName, blogVO.getCategoryName()));
        if (Objects.isNull(category) && !blogVO.getStatus().equals(DRAFT.getStatus())) {
            category = Category.builder()
                    .categoryName(blogVO.getCategoryName())
                    .build();
            categoryDao.insert(category);
        }
        return category;
    }

    /**
     * 保存文章标签
     *
     * @param blogVO 文章信息
     */
    private void saveBlogTag(BlogVO blogVO, Integer blogId) {
        // 编辑文章则删除文章所有标签
        if (Objects.nonNull(blogVO.getId())) {
            blogTagDao.delete(new LambdaQueryWrapper<BlogTag>()
                    .eq(BlogTag::getBlogId, blogVO.getId()));
        }
        // 添加文章标签
        List<String> tagNameList = blogVO.getTagNameList();
        if (CollectionUtils.isNotEmpty(tagNameList)) {
            // 查询已存在的标签
            List<Tag> existTagList = tagService.list(new LambdaQueryWrapper<Tag>()
                    .in(Tag::getTagName, tagNameList));
            List<String> existTagNameList = existTagList.stream()
                    .map(Tag::getTagName)
                    .collect(Collectors.toList());
            List<Integer> existTagIdList = existTagList.stream()
                    .map(Tag::getId)
                    .collect(Collectors.toList());
            // 对比新增不存在的标签
            tagNameList.removeAll(existTagNameList);
            if (CollectionUtils.isNotEmpty(tagNameList)) {
                List<Tag> tagList = tagNameList.stream().map(item -> Tag.builder()
                                .tagName(item)
                                .build())
                        .collect(Collectors.toList());
                tagService.saveBatch(tagList);
                List<Integer> tagIdList = tagList.stream()
                        .map(Tag::getId)
                        .collect(Collectors.toList());
                existTagIdList.addAll(tagIdList);
            }
            // 提取标签id绑定文章
            List<BlogTag> blogTagList = existTagIdList.stream().map(item -> BlogTag.builder()
                            .blogId(blogId)
                            .tagId(item)
                            .build())
                    .collect(Collectors.toList());
            blogTagService.saveBatch(blogTagList);
        }
    }
}
