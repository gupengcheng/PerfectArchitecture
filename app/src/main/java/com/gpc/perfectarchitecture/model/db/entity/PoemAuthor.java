package com.gpc.perfectarchitecture.model.db.entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.NotNull;
import org.greenrobot.greendao.annotation.Generated;

/*
 * @NAME: PoemAuthor
 * @Package: com.gpc.perfectarchitecture.model.db.entity
 * @PoemAuthor : pcg
 * @Create at : 2018/11/13 下午1:40
 * @Description:
 */
@Entity
public class PoemAuthor {
    @Id(autoincrement = true)
    private Long id; // 对应Poems中的authorId
    @NotNull
    private String name; // 诗人姓名
    private String desc; // 诗人简介
    @NotNull
    private String dynasty; //诗词的朝代， T 唐诗 ; S 宋诗 ; C 宋词
    @Generated(hash = 132283090)
    public PoemAuthor(Long id, @NotNull String name, String desc,
            @NotNull String dynasty) {
        this.id = id;
        this.name = name;
        this.desc = desc;
        this.dynasty = dynasty;
    }
    @Generated(hash = 3034444)
    public PoemAuthor() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getDesc() {
        return this.desc;
    }
    public void setDesc(String desc) {
        this.desc = desc;
    }
    public String getDynasty() {
        return this.dynasty;
    }
    public void setDynasty(String dynasty) {
        this.dynasty = dynasty;
    }
}
