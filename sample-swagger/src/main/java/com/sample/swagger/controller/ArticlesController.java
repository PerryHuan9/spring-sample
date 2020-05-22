package com.sample.swagger.controller;

import com.sample.swagger.mbg.model.Articles;
import com.sample.swagger.service.ArticlesService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

@Api(tags = "Articles", description = "文章管理接口")
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

    @ApiOperation("所有文章列表")
    @GetMapping("/listAll")
    List<Articles> listAll() {
        return articlesService.listAllArticles();
    }

    @ApiOperation("分页查找文章列表")
    @GetMapping("/list")
    List<Articles> list(@ApiParam("单页数据数量") int pageSize, @ApiParam("页数") int pageNum) {
        return articlesService.listArticles(pageSize, pageNum);
    }

    @ApiOperation("获取文章")
    @GetMapping("/{id}")
    Articles getArticle(@ApiParam("文章ID") @PathVariable long id) {
        return articlesService.getArticle(id);
    }

    @ApiOperation("增加文章")
    @PostMapping("/add")
    long insertArticle(
            @ApiParam("文章作者") String author,
            @ApiParam("文章标题") String title,
            @ApiParam("文章类型") String articleType,
            @ApiParam("文章发布时间") String publishDate) {
        Articles articles = getArticles(-1, author, title, articleType, publishDate);
        articlesService.createArticle(articles);
        return articles.getId();
    }

    @ApiOperation("更新文章内容")
    @PutMapping("/update")
    int updateArticle(
            @ApiParam("文章ID") long id,
            @ApiParam("文章作者") String author,
            @ApiParam("文章标题") String title,
            @ApiParam("文章类型") String articleType,
            @ApiParam("文章发布时间") String publishDate) {
        Articles articles = getArticles(id, author, title, articleType, publishDate);
        return articlesService.updateArticle(articles);
    }

    @ApiOperation("删除文章")
    @DeleteMapping("/delete")
    int deleteArticle(@ApiParam("文章id") long id) {
        return articlesService.deleteArticle(id);
    }
}
