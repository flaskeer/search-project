package com.hao.entities;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by user on 2016/3/9.
 */
@Getter
@Setter
public class PageParams {

    private static final PageParams DEFAULT_PAGE_PARAMS = new PageParams(1,20);

    /**
     * 查询的页码
     */
    private int page;

    /**
     * 请求的每页数量
     */
    private int limit;

    public PageParams(int page, int limit) {
        this.page = page;
        this.limit = limit;
    }

    public static PageParams createDefault() {
        return DEFAULT_PAGE_PARAMS;
    }

    /**
     * 获得起始页码
     * @return
     */
    public int getBeginIndex() {
        return (getPage() - 1) * limit;
    }

    public int getToIndex() {
        return this.getBeginIndex() + limit;
    }


}
