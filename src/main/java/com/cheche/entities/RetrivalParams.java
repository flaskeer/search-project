package com.cheche.entities;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * Created by user on 2016/3/9.
 */
@Getter
@Setter
public class RetrivalParams extends PageParams {

    public static final int DEFAULT_INDEX = 0;
    public static final int DEFAULT_MAX_LIMIT = 20;

    /**
     * 用户token
     */
    private String token;

    private String mId;

    private long sId;

    private long cId;

    private long bId;

    private int brandType;

    private List<String> oIds;

    private String name;

    private long chId;



    public RetrivalParams(int page, int limit) {
        super(page, limit);
    }

    @Override
    public int getBeginIndex() {
        int index = super.getBeginIndex();
        return index < 0 ? DEFAULT_INDEX : index;
    }

    @Override
    public int getLimit() {
        int limit = super.getLimit();
        return limit <= 0 || limit > DEFAULT_MAX_LIMIT ? DEFAULT_INDEX : limit;
    }
}
