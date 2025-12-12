package kz.seisen.blog.mapper;


import kz.seisen.blog.dto.LikeDto;
import kz.seisen.blog.models.Like;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", uses = {UserMapper.class, PostMapper.class})
public interface LikeMapper {


    @Mapping(target = "user", source = "userDto")
    @Mapping(target = "post", source = "postDto")
    Like toEntity(LikeDto dto);

    @Mapping(target = "userDto", source = "user")
    @Mapping(target = "postDto", source = "post")
    LikeDto toDto(Like entity);

    List<LikeDto> toDtoList(List<Like> entities);

}
