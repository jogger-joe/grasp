package net.usemyskills.grasp.persistence.entity;

import androidx.room.Entity;

@Entity(primaryKeys = {"recordId", "tagId"})
public class RecordTags {
    public long recordId;
    public long tagId;
}
