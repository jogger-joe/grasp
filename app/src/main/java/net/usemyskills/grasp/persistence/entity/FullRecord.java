package net.usemyskills.grasp.persistence.entity;

import androidx.room.Embedded;
import androidx.room.Junction;
import androidx.room.Relation;

import java.util.List;

public class FullRecord {
    @Embedded
    private Record record;

    @Relation(
            parentColumn = "recordId",
            entityColumn = "tagId",
            associateBy = @Junction(RecordTags.class)
    )
    public List<Tag> tags;

    @Relation(
            parentColumn = "typeId",
            entityColumn = "id"
    )
    public Type type;

    @Relation(
            parentColumn = "groupId",
            entityColumn = "id"
    )
    public RecordGroup recordGroup;

    public Record getRecord() {
        return record;
    }

    public void setRecord(Record record) {
        this.record = record;
    }

    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public RecordGroup getGroup() {
        return recordGroup;
    }

    public void setGroup(RecordGroup recordGroup) {
        this.recordGroup = recordGroup;
    }
}
