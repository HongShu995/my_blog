package com.hongshu.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hongshu.dao.PageDao;
import com.hongshu.entity.Page;
import com.hongshu.service.PageService;
import com.hongshu.util.BeanCopyUtils;
import com.hongshu.vo.PageVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 类名
 *
 * @author Hong_Shu995
 * @date 2021-12-21
 */
@Service
public class PageServiceImpl extends ServiceImpl<PageDao, Page> implements PageService
{
    @Autowired
    private PageDao pageDao;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void saveOrUpdatePage(PageVO pageVO)
    {
        Page page = BeanCopyUtils.copyObject(pageVO, Page.class);
        this.saveOrUpdate(page);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void deletePage(Integer pageId)
    {
        pageDao.deleteById(pageId);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public List<PageVO> listPages()
    {
        return BeanCopyUtils.copyList(pageDao.selectList(null), PageVO.class);
    }
}
