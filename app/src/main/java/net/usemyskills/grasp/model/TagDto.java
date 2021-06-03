package net.usemyskills.grasp.model;

public class TagDto implements HasPlaceholder {
    public long id;
    public String name;
    public String description;
    public long groupId = 0;

    public TagDto(TagDto tagDto) {
        this.id = tagDto.id;
        this.name = tagDto.name;
        this.description = tagDto.description;
        this.groupId = tagDto.groupId;
    }

    public TagDto(String name, int groupId) {
        this.id = 0;
        this.name = name;
        this.groupId = groupId;
    }

    public static TagDto create() {
        return new TagDto();
    }

    public TagDto() {
        this("", 0);
    }

    @Override
    public boolean isPlaceholder() {
        return this.id == 0;
    }
}
