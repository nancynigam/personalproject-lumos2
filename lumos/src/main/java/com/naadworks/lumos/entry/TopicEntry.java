package com.naadworks.lumos.entry;

import java.io.Serializable;
import java.util.Date;

/*
Id
Title
Category
Description
createdOn
lastModified
Status flag-> isActive
MetaData{}
 */
public class TopicEntry implements Serializable {

    private Long id;

    private String title;

    private String category;

    private String description;

    private Date createdOn;

    private Date lastModifiedOn;

    private Boolean isActive;
    //MetaData JSON


    public TopicEntry() {
    }

    public TopicEntry(Long id) {
        this.id = id;
    }

    public TopicEntry(Long id, String title, String category, String description, Date createdOn, Date lastModifiedOn, Boolean isActive) {
        this.id = id;
        this.title = title;
        this.category = category;
        this.description = description;
        this.createdOn = createdOn;
        this.lastModifiedOn = lastModifiedOn;
        this.isActive = isActive;
    }

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

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }

    public Date getLastModifiedOn() {
        return lastModifiedOn;
    }

    public void setLastModifiedOn(Date lastModifiedOn) {
        this.lastModifiedOn = lastModifiedOn;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }

    @Override
    public String toString() {
        return "TopicEntry{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", category='" + category + '\'' +
                ", description='" + description + '\'' +
                ", createdOn=" + createdOn +
                ", lastModifiedOn=" + lastModifiedOn +
                ", isActive=" + isActive +
                '}';
    }
}
