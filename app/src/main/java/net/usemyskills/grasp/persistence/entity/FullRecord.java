package net.usemyskills.grasp.persistence.entity;

import androidx.room.Embedded;
import androidx.room.Junction;
import androidx.room.Relation;

import java.util.List;

public class FullRecord {
    @Embedded
    public Record record;

    @Relation(
            parentColumn = "recordId",
            entityColumn = "tagId",
            associateBy = @Junction(RecordTags.class)
    )
    public List<Tag> tags;

    @Relation(
            parentColumn = "typeId",
            entityColumn = "tagId"
    )
    public Type type;
}
