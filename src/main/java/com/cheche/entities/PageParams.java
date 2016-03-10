package com.cheche.entities;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by user on 2016/3/9.
 */
@Getter
@Setter
public class PageParams {

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
