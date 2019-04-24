package com.cuzz.elastic.bean;

import io.searchbox.annotations.JestId;
import lombok.Data;

/**
 * @Author: cuzz
 * @Date: 2018/9/27 17:59
 * @Description:
 */
@Data
public class Article {
    @JestId
    private Integer id;
    private String autor;
    private String title;
    private String content;

    public Article() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
