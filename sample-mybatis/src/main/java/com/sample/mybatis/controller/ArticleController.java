package com.sample.mybatis.controller;

import com.sample.mybatis.mbg.model.Article;
import com.sample.mybatis.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/articles")
public class ArticleController {
    @Autowired
    private ArticleService articlesService;

    @GetMapping("/listAll")
    List<Article> listAll() {
        return articlesService.listAllArticles();
    }

    @GetMapping("/list")
    List<Article> list(int pageSize, int pageNum) {
        return articlesService.listArticles(pageSize, pageNum);
    }

    @GetMapping("/{id}")
    Article getArticle(@PathVariable long id) {
        return articlesService.getArticle(id);
    }

    @PostMapping("/add")
    Article insertArticle(Article article) {
        article.setId(null);
        article.setPublishDate(Instant.now().getEpochSecond());
        article.setArticleId(UUID.randomUUID().toString());
        articlesService.createArticle(article);
        return article;
    }

    @PutMapping("/update")
    int updateArticle(Article article) {
        // 拦截，让以下两位属性不能被修改
        article.setArticleId(null);
        article.setPublishDate(null);
        return articlesService.updateArticle(article);
    }

    @DeleteMapping("/delete")
    int deleteArticle(long id) {
        return articlesService.deleteArticle(id);
    }
}
