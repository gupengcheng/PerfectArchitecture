package com.gpc.perfectarchitecture.model.db.entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.NotNull;
import org.greenrobot.greendao.annotation.Transient;

import java.util.List;

import org.greenrobot.greendao.annotation.Generated;

/*
 * @NAME: Poems
 * @Package: com.gpc.perfectarchitecture.model.db.entity
 * @PoemAuthor : pcg
 * @Create at : 2018/11/13 下午1:38
 * @Description:
 */
@Entity
public class Poems {
    @Id(autoincrement = true)
    private Long id;
    @NotNull
    private String title;
    @NotNull
    private String author;
    @NotNull
    private Long authorId; // 作者的id

    //@Transient：表明这个字段不会被写入数据库，只是作为一个普通的java类字段，用来临时存储数据的，不会被持久化
    @Transient
    private List<String> paragraphs; // ArrayList<String>用|把字符串拼接起来，用String存储
    @Transient
    private List<String> strains; // 诗句的平平仄仄

    @NotNull
    private String content; // 诗句内容，对应paragraphs。ArrayList<String>用|把字符串拼接起来，用String存储
    private String yunlv_rule; //韵律规则，对应strains。用|把字符串拼接起来，用String存储

    @NotNull
    private String dynasty; //诗词的朝代， T 唐诗 ; S 宋诗

    @Generated(hash = 971663703)
    public Poems(Long id, @NotNull String title, @NotNull String author,
                 @NotNull Long authorId, @NotNull String content, String yunlv_rule,
                 @NotNull String dynasty) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.authorId = authorId;
        this.content = content;
        this.yunlv_rule = yunlv_rule;
        this.dynasty = dynasty;
    }

    @Generated(hash = 1182850416)
    public Poems() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return this.author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Long getAuthorId() {
        return this.authorId;
    }

    public void setAuthorId(Long authorId) {
        this.authorId = authorId;
    }

    public String getContent() {
        return this.content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getYunlv_rule() {
        return this.yunlv_rule;
    }

    public void setYunlv_rule(String yunlv_rule) {
        this.yunlv_rule = yunlv_rule;
    }

    public String getDynasty() {
        return this.dynasty;
    }

    public void setDynasty(String dynasty) {
        this.dynasty = dynasty;
    }

}
