package com.sample.swagger.service.impl;

import com.github.pagehelper.PageHelper;
import com.sample.swagger.mbg.mapper.ArticleMapper;
import com.sample.swagger.mbg.model.Article;
import com.sample.swagger.mbg.model.ArticleExample;
import com.sample.swagger.service.ArticlesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticlesServiceImpl implements ArticlesService {
    @Autowired
    private ArticleMapper articlesMapper;


    @Override
    public List<Article> listAllArticles() {
        return articlesMapper.selectByExample(new ArticleExample());
    }

    @Override
    public int createArticle(Article articles) {
        return articlesMapper.insertSelective(articles);
    }

    @Override
    public int updateArticle(Article articles) {
        return articlesMapper.updateByPrimaryKeySelective(articles);
    }

    @Override
    public int deleteArticle(long id) {
        return articlesMapper.deleteByPrimaryKey(id);
    }

    @Override
    public List<Article> listArticles(int pageSize, int pageNum) {
        PageHelper.startPage(pageNum, pageSize);
        return articlesMapper.selectByExample(new ArticleExample());
    }

    @Override
    public Article getArticle(long id) {
        return articlesMapper.selectByPrimaryKey(id);
    }
}
