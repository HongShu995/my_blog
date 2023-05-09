package com.hongshu.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hongshu.dao.BlogTagDao;
import com.hongshu.entity.BlogTag;
import com.hongshu.service.BlogTagService;
import org.springframework.stereotype.Service;

/**
 * 类名
 *
 * @author Hong_Shu995
 * @date 2021-12-16
 */
@Service
public class BlogTagServiceImpl extends ServiceImpl<BlogTagDao, BlogTag> implements BlogTagService
{
}
