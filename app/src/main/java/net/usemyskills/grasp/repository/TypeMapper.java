package net.usemyskills.grasp.repository;

import net.usemyskills.grasp.model.TypeDto;
import net.usemyskills.grasp.persistence.entity.Type;

import java.util.ArrayList;
import java.util.List;

public class TypeMapper {
    public static Type toEntity(TypeDto tagDto) {
        Type type = new Type();
        type.tagId = tagDto.id;
        type.groupId = tagDto.groupId;
        type.name = tagDto.name;
        type.description = tagDto.description;
        type.suffix = tagDto.suffix;
        type.displayAsInteger = tagDto.displayAsInteger;
        return type;
    }

    public static TypeDto toDto(Type type) {
        TypeDto typeDto = new TypeDto();
        typeDto.id = type.tagId;
        typeDto.groupId = type.groupId;
        typeDto.name = type.name;
        typeDto.description = type.description;
        typeDto.suffix = type.suffix;
        typeDto.displayAsInteger = type.displayAsInteger;
        return typeDto;
    }

    public static List<Type> toEntity(List<TypeDto> typeDtos) {
        List<Type> typeList = new ArrayList<>();
        for (TypeDto typeDto : typeDtos) {
            typeList.add(TypeMapper.toEntity(typeDto));
        }
        return typeList;
    }

    public static List<TypeDto> toDto(List<Type> types) {
        List<TypeDto> typeDtoList = new ArrayList<>();
        for (Type type : types) {
            typeDtoList.add(TypeMapper.toDto(type));
        }
        return typeDtoList;
    }
}
