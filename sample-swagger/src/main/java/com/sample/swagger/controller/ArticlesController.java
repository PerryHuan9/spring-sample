package com.sample.swagger.controller;

import com.sample.swagger.controller.exception.InvalidParameter;
import com.sample.swagger.mbg.model.Article;
import com.sample.swagger.service.ArticlesService;
import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.List;
import java.util.UUID;


@Api(tags = "Articles", description = "文章管理接口")
@RestController
@RequestMapping("/articles")
public class ArticlesController {
    private static Logger logger = LoggerFactory.getLogger(ArticlesController.class);

    @Autowired
    private ArticlesService articlesService;

    @ApiOperation("所有文章列表")
    @GetMapping("/listAll")
    List<Article> listAll() {
        return articlesService.listAllArticles();
    }

    @ApiOperation("分页查找文章列表")
    @GetMapping("/list")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageSize", value = "单页数据数量", paramType = "query"),
            @ApiImplicitParam(name = "pageNum", value = "页数", paramType = "query"),
    })
    List<Article> list(Integer pageSize, Integer pageNum) {
        if (pageSize == null || pageNum == null) {
            throw new InvalidParameter("pageSize或pageNum参数错误");
        }
        return articlesService.listArticles(pageSize, pageNum);
    }

    @ApiOperation("获取文章")
    @GetMapping("/{id}")
    Article getArticle(@ApiParam("文章ID") @PathVariable long id) {
        return articlesService.getArticle(id);
    }

    @ApiOperation(value = "增加文章", notes = "id, publish_date, articleId不需要填写，会自动生成")
    @PostMapping("/add")
    Article insertArticle(@RequestBody Article article) {
        article.setId(null);
        article.setPublishDate(Instant.now().getEpochSecond());
        article.setArticleId(UUID.randomUUID().toString());
        articlesService.createArticle(article);
        return article;
    }

    @ApiOperation("更新文章内容")
    @PutMapping("/update")
    int updateArticle(Article article) {
        // 拦截，让以下两位属性不能被修改
        article.setArticleId(null);
        article.setPublishDate(null);
        return articlesService.updateArticle(article);
    }

    @ApiOperation("删除文章")
    @DeleteMapping("/delete")
    int deleteArticle(@ApiParam("文章id") long id) {
        return articlesService.deleteArticle(id);
    }
}
