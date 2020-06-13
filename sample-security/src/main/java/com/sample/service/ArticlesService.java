package com.sample.service;

import com.sample.dto.ArticleTypeStatistics;
import com.sample.mbg.model.Article;

import java.util.List;

public interface ArticlesService {
    List<Article> listAllArticles();

    int createArticle(Article articles);

    int updateArticle(Article articles);

    int deleteArticle(long id);

    List<Article> listArticles(int pageSize, int pageNum);

    Article getArticle(long id);

    List<ArticleTypeStatistics> statisticsArticleType();

}
