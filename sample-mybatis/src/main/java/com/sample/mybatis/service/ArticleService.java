package com.sample.mybatis.service;

import com.sample.mybatis.mbg.model.Article;

import java.util.List;

public interface ArticleService {
    List<Article> listAllArticles();

    int createArticle(Article articles);

    int updateArticle(Article articles);

    int deleteArticle(long id);

    List<Article> listArticles(int pageSize, int pageNum);

    Article getArticle(long id);

}
