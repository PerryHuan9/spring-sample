package com.sample.swagger.service;

import com.sample.swagger.mbg.model.Article;

import java.util.List;

public interface ArticlesService {
    List<Article> listAllArticles();

    int createArticle(Article articles);

    int updateArticle(Article articles);

    int deleteArticle(long id);

    List<Article> listArticles(int pageSize, int pageNum);

    Article getArticle(long id);

}
