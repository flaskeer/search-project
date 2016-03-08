package com.cheche.repository;

import com.cheche.entities.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Created by user on 2016/3/8.
 */

public interface PostRepository extends ElasticsearchRepository<Post,String>{

    Page<Post> findByTagsName(String name, Pageable pageable);
}
