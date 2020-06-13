package com.sample.service.impl;

import com.github.pagehelper.PageHelper;
import com.sample.dao.ArticleStatisticsDao;
import com.sample.dto.ArticleTypeStatistics;
import com.sample.mbg.mapper.ArticleMapper;
import com.sample.mbg.model.Article;
import com.sample.mbg.model.ArticleExample;
import com.sample.service.ArticlesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticlesServiceImpl implements ArticlesService {
    @Autowired
    private ArticleMapper articlesMapper;

    @Autowired
    private ArticleStatisticsDao articleStatisticsDao;


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

    @Override
    public List<ArticleTypeStatistics> statisticsArticleType() {
        return articleStatisticsDao.statisticsArticleType();
    }
}
