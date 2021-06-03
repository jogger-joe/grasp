package net.usemyskills.grasp.repository;

import net.usemyskills.grasp.model.TagDto;
import net.usemyskills.grasp.persistence.entity.Tag;

import java.util.ArrayList;
import java.util.List;

public class TagMapper {
    public static Tag toEntity(TagDto tagDto) {
        Tag tag = new Tag();
        tag.tagId = tagDto.id;
        tag.groupId = tagDto.groupId;
        tag.name = tagDto.name;
        tag.description = tagDto.description;
        return tag;
    }

    public static TagDto toDto(Tag tag) {
        TagDto tagDto = new TagDto();
        tagDto.id = tag.tagId;
        tagDto.groupId = tag.groupId;
        tagDto.name = tag.name;
        tagDto.description = tag.description;
        return tagDto;
    }

    public static List<Tag> toEntity(List<TagDto> tagDtos) {
        List<Tag> tagList = new ArrayList<>();
        for (TagDto tagDto : tagDtos) {
            tagList.add(TagMapper.toEntity(tagDto));
        }
        return tagList;
    }

    public static List<TagDto> toDto(List<Tag> tags) {
        List<TagDto> tagDtoList = new ArrayList<>();
        for (Tag tag : tags) {
            tagDtoList.add(TagMapper.toDto(tag));
        }
        return tagDtoList;
    }
}
