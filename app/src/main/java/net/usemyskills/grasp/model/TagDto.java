package net.usemyskills.grasp.model;

public class TagDto {
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

    public TagDto() {
        this("", 0);
    }

}
