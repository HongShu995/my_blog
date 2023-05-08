package com.hongshu.util;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import java.util.Objects;

/**
 * projectName: my_blog
 * <p>
 * &#064;author:  WangYiBing
 * time: 2023/5/8 14:19 周一
 * description:
 */
public class PageUtils
{
    private static final ThreadLocal<Page<?>> PAGE_HOLDER = new ThreadLocal<>();

    public static void setCurrentPage(Page<?> page) {
        PAGE_HOLDER.set(page);
    }

    public static Page<?> getPage() {
        Page<?> page = PAGE_HOLDER.get();
        if (Objects.isNull(page)) {
            setCurrentPage(new Page<>());
        }
        return PAGE_HOLDER.get();
    }

    public static Long getCurrent() {
        return getPage().getCurrent();
    }

    public static Long getSize() {
        return getPage().getSize();
    }

    public static Long getLimitCurrent() {
        return (getCurrent() - 1) * getSize();
    }

    public static void remove() {
        PAGE_HOLDER.remove();
    }
}
