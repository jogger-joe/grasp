package net.usemyskills.grasp.repository;

import net.usemyskills.grasp.model.RecordGroupDto;
import net.usemyskills.grasp.persistence.entity.RecordGroup;

import java.util.ArrayList;
import java.util.List;

public class RecordGroupMapper {
    public static RecordGroup toEntity(RecordGroupDto recordGroupDto) {
        RecordGroup recordGroup = new RecordGroup();
        recordGroup.tagId = recordGroupDto.id;
        recordGroup.name = recordGroupDto.name;
        recordGroup.description = recordGroupDto.description;
        recordGroup.iconId = recordGroupDto.iconId;
        return recordGroup;
    }

    public static RecordGroupDto toDto(RecordGroup recordGroup) {
        RecordGroupDto recordGroupDto = new RecordGroupDto();
        recordGroupDto.id = recordGroup.tagId;
        recordGroupDto.name = recordGroup.name;
        recordGroupDto.description = recordGroup.description;
        recordGroupDto.iconId = recordGroup.iconId;
        return recordGroupDto;
    }

    public static List<RecordGroup> toEntity(List<RecordGroupDto> recordGroupDtos) {
        List<RecordGroup> recordGroupList = new ArrayList<>();
        for (RecordGroupDto recordGroupDto : recordGroupDtos) {
            recordGroupList.add(RecordGroupMapper.toEntity(recordGroupDto));
        }
        return recordGroupList;
    }

    public static List<RecordGroupDto> toDto(List<RecordGroup> recordGroups) {
        List<RecordGroupDto> recordGroupDtoList = new ArrayList<>();
        for (RecordGroup recordGroup : recordGroups) {
            recordGroupDtoList.add(RecordGroupMapper.toDto(recordGroup));
        }
        return recordGroupDtoList;
    }
}
