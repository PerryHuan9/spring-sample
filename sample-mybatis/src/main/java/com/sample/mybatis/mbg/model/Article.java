package com.sample.mybatis.mbg.model;

import java.io.Serializable;

public class Article implements Serializable {
    private Long id;

    /**
     * 标题
     *
     * @mbg.generated
     */
    private String title;

    /**
     * 作者
     *
     * @mbg.generated
     */
    private String author;

    private Long publishDate;

    /**
     * 文章类型
     *
     * @mbg.generated
     */
    private String articleType;

    /**
     * 文章id
     *
     * @mbg.generated
     */
    private String articleId;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Long getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(Long publishDate) {
        this.publishDate = publishDate;
    }

    public String getArticleType() {
        return articleType;
    }

    public void setArticleType(String articleType) {
        this.articleType = articleType;
    }

    public String getArticleId() {
        return articleId;
    }

    public void setArticleId(String articleId) {
        this.articleId = articleId;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", title=").append(title);
        sb.append(", author=").append(author);
        sb.append(", publishDate=").append(publishDate);
        sb.append(", articleType=").append(articleType);
        sb.append(", articleId=").append(articleId);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}