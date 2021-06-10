package net.usemyskills.grasp.persistence.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;

@Entity(primaryKeys = {"recordId", "tagId"})
public class RecordTagsReference {
    public RecordTagsReference(long recordId, long tagId) {
        this.recordId = recordId;
        this.tagId = tagId;
    }

    @ColumnInfo(index = true)
    public long recordId;

    @ColumnInfo(index = true)
    public long tagId;
}
