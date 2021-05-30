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
    public String valueSuffix;

    public RecordDto(RecordWithTypeAndTags recordWithTypeAndTags) {
        Record record = recordWithTypeAndTags.record;
        if (record != null) {
            this.date = new SimpleDateFormat("dd.MM.yyyy", Locale.GERMAN).format(record.date);
            this.value = String.valueOf(record.value);
        }
        this.type = recordWithTypeAndTags.type != null ? recordWithTypeAndTags.type.name : "missing tag";
        if (recordWithTypeAndTags.tags != null) {
            StringBuilder tagsStringBuilder = new StringBuilder();
            for (Tag tag : recordWithTypeAndTags.tags) {
                if (tag != null) {
                    tagsStringBuilder.append(tag.name).append(" ");
                }
            }
            this.tags = tagsStringBuilder.toString();
        }
        if (recordWithTypeAndTags.type != null){
            this.valueSuffix = recordWithTypeAndTags.type.suffix;
        }
    }

    public String getValueLabel() {
        return this.value + " " + this.valueSuffix;
    }
}
