package com.cheche.entities;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

import java.util.List;

/**
 * Created by user on 2016/3/8.
 */
@Getter
@Setter
@Document(indexName = "post",type = "post",shards = 1,replicas = 0)
public class Post {

    @Id
    private String id;
    private String title;
    private List<Tag> tags;
}
