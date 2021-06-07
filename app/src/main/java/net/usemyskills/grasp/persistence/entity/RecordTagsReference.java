package net.usemyskills.grasp.persistence.entity;

import androidx.room.Entity;

@Entity(primaryKeys = {"recordId", "tagId"})
public class RecordTagsReference {
    public RecordTagsReference(long recordId, long tagId) {
        this.recordId = recordId;
        this.tagId = tagId;
    }

    public long recordId;
    public long tagId;
}
