package com.sample.component;


import com.sample.dto.ArticleTypeStatistics;
import com.sample.service.ArticlesService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Slf4j
public class ArticleStatistics {
    @Autowired
    private ArticlesService articlesService;


    /**
     * 定时任务
     * 每5分钟执行一次
     */
    @Scheduled(cron = "0 0/1 * * * ?")
    private void computedArticles() {
        List<ArticleTypeStatistics> result = articlesService.statisticsArticleType();
        log.info(result.toString());
    }
}
