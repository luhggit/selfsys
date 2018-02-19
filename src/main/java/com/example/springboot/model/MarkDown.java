package com.example.springboot.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "markdown")
public class MarkDown {

    @Id
    @GeneratedValue
    private Integer id;

    @Column(name = "md_content",columnDefinition = "text")
    private String mdContent;

    @Column(name = "html_content",columnDefinition = "text")
    private String htmlContent;

    @Column(name = "create_time",columnDefinition = "timestamp not null default now()")
    private Date createTime;

    @Column(name = "last_update_time",columnDefinition = "timestamp")
    private Date lastUpdateTime;

    @Column(name = "status")
    private String status;

    @Column(name = "first_class")
    private String firstClass;

    @Column(name = "second_class")
    private String secondClass;

    @Column(name = "title")
    private String title;

    public MarkDown(){
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMdContent() {
        return mdContent;
    }

    public void setMdContent(String mdContent) {
        this.mdContent = mdContent;
    }

    public String getHtmlContent() {
        return htmlContent;
    }

    public void setHtmlContent(String htmlContent) {
        this.htmlContent = htmlContent;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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
