package com.sample.dto;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ArticleTypeStatistics {
    private String articleType;
    private long articleNum;


}
