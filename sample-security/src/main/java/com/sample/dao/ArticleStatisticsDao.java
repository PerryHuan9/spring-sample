package com.sample.dao;

import com.sample.dto.ArticleTypeStatistics;

import java.util.List;

public interface ArticleStatisticsDao {
    List<ArticleTypeStatistics> statisticsArticleType();
}
