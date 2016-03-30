package com.cheche.service.impl;

import com.cheche.service.SearchService;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.suggest.SuggestResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.suggest.Suggest;
import org.elasticsearch.search.suggest.completion.CompletionSuggestionBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.stereotype.Service;

import java.util.Iterator;

/**
 * Created by user on 2016/3/10.
 */
@Service
public class SearchServiceImpl implements SearchService{


    @Autowired
    private Client client;


    @Autowired
    private ElasticsearchTemplate elasticsearchTemplate;

    public void test(String searchText){
        String indexName = "";
        CompletionSuggestionBuilder completionSuggestionBuilder = new CompletionSuggestionBuilder(searchText);
        SuggestResponse suggestResponse = client.prepareSuggest(indexName).setSuggestText("tes").addSuggestion(completionSuggestionBuilder.field("suggest")).execute().actionGet();
        Suggest suggest = suggestResponse.getSuggest();
        Suggest.Suggestion suggestion = suggest.getSuggestion(searchText);
        Iterator  iterator= suggestion.iterator();

    }

    public void test1() {
        SearchResponse resp = client.prepareSearch("test").setQuery(QueryBuilders.queryStringQuery("")).execute().actionGet();
    }

}
