package kz.seisen.blog.mapper;


import kz.seisen.blog.dto.LikeDto;
import kz.seisen.blog.models.Like;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface LikeMapper {

    @Mapping(target = "user.id", source = "userDto.id")
    @Mapping(target = "user.username", source = "userDto.username")
    @Mapping(target = "user.email", source = "userDto.email")
    @Mapping(target = "user.password", ignore = true)
    @Mapping(target = "user.posts", ignore = true)
    @Mapping(target = "user.comments", ignore = true)
    @Mapping(target = "user.likes", ignore = true)
    @Mapping(target = "post.id", source = "postDto.id")
    @Mapping(target = "post.title", ignore = true)
    @Mapping(target = "post.text", ignore = true)
    @Mapping(target = "post.user", ignore = true)
    @Mapping(target = "post.comments", ignore = true)
    @Mapping(target = "post.likes", ignore = true)
    @Mapping(target = "post.categories", ignore = true)
    Like toEntity(LikeDto dto);

    @Mapping(target = "userDto.id", source = "user.id")
    @Mapping(target = "userDto.username", source = "user.username")
    @Mapping(target = "userDto.email", source = "user.email")
    @Mapping(target = "userDto.postsDto", ignore = true)
    @Mapping(target = "userDto.commentsDto", ignore = true)
    @Mapping(target = "userDto.likesDto", ignore = true)
    @Mapping(target = "postDto.id", source = "post.id")
    @Mapping(target = "postDto.title", source = "post.title")
    @Mapping(target = "postDto.text", source = "post.text")
    @Mapping(target = "postDto.commentsDto", ignore = true)
    @Mapping(target = "postDto.likesDto", ignore = true)
    @Mapping(target = "postDto.userDto", ignore = true)
    @Mapping(target = "postDto.categoriesDto", ignore = true)
    LikeDto toDto(Like entity);

    List<LikeDto> toDtoList(List<Like> entities);
}
