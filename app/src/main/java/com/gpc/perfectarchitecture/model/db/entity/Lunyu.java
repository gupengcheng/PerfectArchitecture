package com.gpc.perfectarchitecture.model.db.entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.NotNull;
import org.greenrobot.greendao.annotation.Transient;

import java.util.List;

import org.greenrobot.greendao.annotation.Generated;

/*
 * @NAME: Lunyu
 * @Package: com.gpc.perfectarchitecture.model.db.entity
 * @PoemAuthor : pcg
 * @Create at : 2018/11/13 下午1:39
 * @Description:
 */
@Entity
public class Lunyu {
    @Id(autoincrement = true)
    private Long id;
    private String chapter;
    @NotNull
    private String content; // 对应paragraphs，用|分割
    //@Transient：表明这个字段不会被写入数据库，只是作为一个普通的java类字段，用来临时存储数据的，不会被持久化
    @Transient
    private List<String> paragraphs;

    @Generated(hash = 790655967)
    public Lunyu(Long id, String chapter, @NotNull String content) {
        this.id = id;
        this.chapter = chapter;
        this.content = content;
    }

    @Generated(hash = 2043316006)
    public Lunyu() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getChapter() {
        return this.chapter;
    }

    public void setChapter(String chapter) {
        this.chapter = chapter;
    }

    public String getContent() {
        return this.content;
    }

    public void setContent(String content) {
        this.content = content;
    }

}
