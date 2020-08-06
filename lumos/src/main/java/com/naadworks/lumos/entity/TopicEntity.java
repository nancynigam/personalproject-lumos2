package com.naadworks.lumos.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "topics")
public class TopicEntity {

    @Id
    @GeneratedValue()
    private Long id;

    @Column(name = "title", nullable = false, unique = true)
    private String title;

    @Column(name = "category")
    private String category;

    @Column(name = "description")
    private String description;

    @Column(name = "created_on")
    @Temporal(TemporalType.DATE)
    private Date createdOn;

    @Column(name = "last_modified_on")
    @Temporal(TemporalType.DATE)
    private Date lastModifiedOn;

    @Column(name = "is_active")
    private Boolean isActive;

    public TopicEntity() {
    }

    public TopicEntity(Long id) {
        this.id = id;
    }

    public TopicEntity(Long id, String title, String category, String description, Date createdOn, Date lastModifiedOn, Boolean isActive) {
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
        return "TopicEntity{" +
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
