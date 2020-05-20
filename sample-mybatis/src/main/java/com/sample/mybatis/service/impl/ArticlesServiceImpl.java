package com.sample.mybatis.service.impl;

import com.github.pagehelper.PageHelper;
import com.sample.mybatis.mbg.mapper.ArticlesMapper;
import com.sample.mybatis.mbg.model.Articles;
import com.sample.mybatis.mbg.model.ArticlesExample;
import com.sample.mybatis.service.ArticlesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticlesServiceImpl implements ArticlesService {
    @Autowired
    private ArticlesMapper articlesMapper;


    @Override
    public List<Articles> listAllArticles() {
        return articlesMapper.selectByExample(new ArticlesExample());
    }

    @Override
    public int createArticle(Articles articles) {
        return articlesMapper.insertSelective(articles);
    }

    @Override
    public int updateArticle(Articles articles) {
        return articlesMapper.updateByPrimaryKey(articles);
    }

    @Override
    public int deleteArticle(long id) {
        return articlesMapper.deleteByPrimaryKey(id);
    }

    @Override
    public List<Articles> listArticles(int pageSize, int pageNum) {
        PageHelper.startPage(pageSize, pageNum);
        return articlesMapper.selectByExample(new ArticlesExample());
    }

    @Override
    public Articles getArticle(long id) {
        return articlesMapper.selectByPrimaryKey(id);
    }
}
