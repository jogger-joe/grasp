package net.usemyskills.grasp.model;


public class TypeDto extends TagDto {
    public boolean displayAsInteger;
    public String suffix;

    public TypeDto(TypeDto typeDto) {
        super(typeDto);
        this.displayAsInteger = typeDto.displayAsInteger;
        this.suffix = typeDto.suffix;
    }

    public TypeDto(String name, int groupId) {
        super(name, groupId);
    }

    public TypeDto() {
        super("", 0);
    }
}
