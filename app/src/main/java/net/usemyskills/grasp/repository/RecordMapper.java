package net.usemyskills.grasp.repository;

import net.usemyskills.grasp.model.RecordDto;
import net.usemyskills.grasp.model.TypeDto;
import net.usemyskills.grasp.persistence.entity.Record;
import net.usemyskills.grasp.persistence.entity.RecordWithTypeAndTags;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class RecordMapper {
    public static RecordWithTypeAndTags toEntity(RecordDto recordDto) {
        RecordWithTypeAndTags recordWithTypeAndTags = new RecordWithTypeAndTags();
        recordWithTypeAndTags.record.recordId = recordDto.id;
        recordWithTypeAndTags.record.groupId = recordDto.groupId;
        recordWithTypeAndTags.record.date = recordDto.date;
        recordWithTypeAndTags.record.value = recordDto.value;
        recordWithTypeAndTags.record.typeId = recordDto.type != null ? recordDto.type.id : 0;
        recordWithTypeAndTags.tags = TagMapper.toEntity(recordDto.tags);
        recordWithTypeAndTags.type = TypeMapper.toEntity(recordDto.type);
        return recordWithTypeAndTags;
    }

    public static RecordDto toDto(RecordWithTypeAndTags recordWithTypeAndTags) {
        RecordDto recordDto = new RecordDto();
        Record record = recordWithTypeAndTags.record;
        recordDto.id = record != null ? record.recordId : 0;
        recordDto.groupId = record != null ? record.groupId : 0;
        recordDto.date = record != null ? record.date : new Date();
        recordDto.value = record != null ? record.value : 0.0;
        recordDto.tags = recordWithTypeAndTags.tags != null ? TagMapper.toDto(recordWithTypeAndTags.tags) : new ArrayList<>();
        recordDto.type = recordWithTypeAndTags.type != null ? TypeMapper.toDto(recordWithTypeAndTags.type) : new TypeDto();
        return recordDto;
    }

    public static List<RecordWithTypeAndTags> toEntity(List<RecordDto> recordDtos) {
        List<RecordWithTypeAndTags> withTypeAndTagsArrayList = new ArrayList<>();
        for (RecordDto recordDto : recordDtos) {
            withTypeAndTagsArrayList.add(RecordMapper.toEntity(recordDto));
        }
        return withTypeAndTagsArrayList;
    }

    public static List<RecordDto> toDto(List<RecordWithTypeAndTags> recordsWithTypeAndTags) {
        List<RecordDto> recordDtoList = new ArrayList<>();
        for (RecordWithTypeAndTags recordWithTypeAndTags : recordsWithTypeAndTags) {
            recordDtoList.add(RecordMapper.toDto(recordWithTypeAndTags));
        }
        return recordDtoList;
    }
}
