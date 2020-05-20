package com.sample.mybatis.service;

import com.sample.mybatis.mbg.model.Articles;

import java.util.List;

public interface ArticlesService {
    List<Articles> listAllArticles();

    int createArticle(Articles articles);

    int updateArticle(Articles articles);

    int deleteArticle(long id);

    List<Articles> listArticles(int pageSize, int pageNum);

    Articles getArticle(long id);

}
