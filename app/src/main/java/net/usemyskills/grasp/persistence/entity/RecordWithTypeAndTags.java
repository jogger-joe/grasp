package net.usemyskills.grasp.persistence.entity;

import androidx.room.Embedded;
import androidx.room.Junction;
import androidx.room.Relation;

import java.util.ArrayList;
import java.util.List;

public class RecordWithTypeAndTags {
    @Embedded
    public Record record;

    @Relation(
            parentColumn = "recordId",
            entityColumn = "tagId",
            associateBy = @Junction(RecordTagsReference.class)
    )
    public List<Tag> tags;

    @Relation(
            parentColumn = "typeId",
            entityColumn = "tagId"
    )
    public Type type;

    public RecordWithTypeAndTags() {
        this.record = new Record();
        this.tags = new ArrayList<>();
        this.type = new Type();
    }
}
