package com.sample.controller;

import com.sample.controller.exception.InvalidParameter;
import com.sample.dto.ArticleTypeStatistics;
import com.sample.mbg.model.Article;
import com.sample.service.ArticlesService;
import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
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

    /**
     * @PreAuthorize使用SpEL控制接口权限 更多方法请查看
     * https://docs.spring.io/spring-security/site/docs/5.3.2.RELEASE/reference/html5/#el-common-built-in
     */
    @PreAuthorize("hasRole('ADMIN')") // 拥有ROLE_ADMIN权限的用户才允许访问该接口
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
//    @PreAuthorize("hasAnyRole('ADMIN','CTRL')")
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


    @ApiOperation("设备类型统计")
    @GetMapping("/articleTypeStatistics")
    List<ArticleTypeStatistics> statisticsArticleType() {
        return articlesService.statisticsArticleType();
    }
}
