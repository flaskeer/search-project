package com.cheche.service;

import com.cheche.entities.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

/**
 * Created by user on 2016/3/8.
 */
public interface PostService {

    Post save(Post post);
    Post findOne(String id);
    Iterable<Post> findAll();
    Page<Post> findByTagsName(String tagName, PageRequest pageRequest);
}
