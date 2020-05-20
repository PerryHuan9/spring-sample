package com.sample.mybatis.controller;

import com.sample.mybatis.mbg.model.Articles;
import com.sample.mybatis.service.ArticlesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

@RestController
@RequestMapping("/articles")
public class ArticlesController {
    static Articles getArticles(long id, String author, String title, String articleType, String publishDate) {
        Articles articles = new Articles();
        articles.setId(id);
        articles.setAuthor(author);
        articles.setTitle(title);
        articles.setArticleType(articleType);
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        try {
            articles.setPublishDate(df.parse(publishDate));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return articles;
    }

    @Autowired
    private ArticlesService articlesService;

    @GetMapping("/listAll")
    List<Articles> listAll() {
        return articlesService.listAllArticles();
    }

    @GetMapping("/list")
    List<Articles> list(int pageSize, int pageNum) {
        return articlesService.listArticles(pageSize, pageNum);
    }

    @GetMapping("/{id}")
    Articles getArticle(@PathVariable long id) {
        return articlesService.getArticle(id);
    }

    @PostMapping("/add")
    long insertArticle(String author, String title, String articleType, String publishDate) {
        Articles articles = getArticles(-1, author, title, articleType, publishDate);
        articlesService.createArticle(articles);
        return articles.getId();
    }

    @PutMapping("/update")
    int updateArticle(long id, String author, String title, String articleType, String publishDate) {
        Articles articles = getArticles(id, author, title, articleType, publishDate);
        return articlesService.updateArticle(articles);
    }

    @DeleteMapping("/delete")
    int deleteArticle(long id) {
        return articlesService.deleteArticle(id);
    }
}
