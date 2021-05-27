package net.usemyskills.grasp.model;

import net.usemyskills.grasp.persistence.entity.Record;
import net.usemyskills.grasp.persistence.entity.RecordWithTypeAndTags;
import net.usemyskills.grasp.persistence.entity.Tag;

import java.text.SimpleDateFormat;
import java.util.Locale;

public class RecordDto {
    public String date;
    public String type;
    public String tags;
    public String value;

    public RecordDto(RecordWithTypeAndTags recordWithTypeAndTags) {
        Record record = recordWithTypeAndTags.record;
        this.date = new SimpleDateFormat("dd.MM.yyyy", Locale.GERMAN).format(record.date);
        this.type = recordWithTypeAndTags.type.name;
        StringBuilder tagsStringBuilder = new StringBuilder();
        for (Tag tag: recordWithTypeAndTags.tags) {
            tagsStringBuilder.append(tag.name).append(" ");
        }
        this.tags = tagsStringBuilder.toString();
        StringBuilder valueStringBuilder = new StringBuilder();
        valueStringBuilder.append(record.value).append(" ").append(recordWithTypeAndTags.type.suffix);
        this.value = valueStringBuilder.toString();
    }
}
