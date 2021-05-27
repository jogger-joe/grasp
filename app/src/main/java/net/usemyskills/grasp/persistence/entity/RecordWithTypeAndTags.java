package net.usemyskills.grasp.persistence.entity;

import androidx.room.Embedded;
import androidx.room.Junction;
import androidx.room.Relation;

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
}
