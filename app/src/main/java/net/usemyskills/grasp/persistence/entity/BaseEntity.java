package net.usemyskills.grasp.persistence.entity;

import androidx.room.Entity;

import java.util.Date;

@Entity
public class BaseEntity  {
    public Date createdAt;
    public Date updatedAt;

    public BaseEntity() {
        this.createdAt = new Date();
        this.updatedAt = new Date();
    }
}
