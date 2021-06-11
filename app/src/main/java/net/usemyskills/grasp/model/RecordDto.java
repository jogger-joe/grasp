package net.usemyskills.grasp.model;

import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class RecordDto {
    public long id;
    public long groupId;
    public Date date;
    public TypeDto type;
    public List<TagDto> tags;
    public double value;

    public RecordDto(RecordDto recordDto) {
        this.id = recordDto.id;
        this.groupId = recordDto.groupId;
        this.date = recordDto.date;
        this.type = recordDto.type;
        this.tags = recordDto.tags;
        this.value = recordDto.value;
    }

    public RecordDto() {
        this.id = 0;
        this.groupId = 0;
        this.date = new Date();
        this.type = new TypeDto();
        this.tags = new ArrayList<>();
        this.value = 0.0;
    }

    public String getValueLabel() {
        boolean displayAsInteger = this.type != null && this.type.displayAsInteger;
        String suffix = this.type != null ? this.type.suffix : "";
        String valueString = String.valueOf(this.value);
        if (displayAsInteger) {
            valueString = String.valueOf((int)this.value);
        }
        return valueString + " " + suffix;
    }
    public String getTypeLabel() { return this.type.name; }

    public String getTagsLabel() {
        if (this.tags != null) {
            StringBuilder tagsStringBuilder = new StringBuilder();
            for (TagDto tag : this.tags) {
                if (tag != null) {
                    tagsStringBuilder.append(tag.name).append(" ");
                }
            }
            return tagsStringBuilder.toString();
        }
        return "";
    }
    public String getDateLabel() {
        return this.date == null ? "invalid" : new SimpleDateFormat("dd.MM.yyyy", Locale.GERMAN).format(this.date);
    }

}
