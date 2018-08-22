package com.example.springboot.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "mindmap")
public class MindMap {

    @Id
    @GeneratedValue
    private Integer id;

    @Column(name = "mindmap_content",columnDefinition = "text")
    private String mindmapContent;

    @Column(name = "create_time",columnDefinition = "timestamp not null default now()",updatable = false)
    private Date createTime;

    @Column(name = "last_update_time",columnDefinition = "timestamp")
    private Date lastUpdateTime;

    @Column(name = "first_class")
    private String firstClass;

    @Column(name = "second_class")
    private String secondClass;

    @Column(name = "title")
    private String title;

    public MindMap(){

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMindmapContent() {
        return mindmapContent;
    }

    public void setMindmapContent(String mindmapContent) {
        this.mindmapContent = mindmapContent;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getLastUpdateTime() {
        return lastUpdateTime;
    }

    public void setLastUpdateTime(Date lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }

    public String getFirstClass() {
        return firstClass;
    }

    public void setFirstClass(String firstClass) {
        this.firstClass = firstClass;
    }

    public String getSecondClass() {
        return secondClass;
    }

    public void setSecondClass(String secondClass) {
        this.secondClass = secondClass;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
