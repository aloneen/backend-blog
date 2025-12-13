package kz.seisen.blog.mapper;


import kz.seisen.blog.dto.CommentDto;
import kz.seisen.blog.models.Comment;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", uses = {UserMapper.class})
public interface CommentMapper {

    @Mapping(source = "user", target = "userDto")
    @Mapping(source = "post.id", target = "postDto.id")
    @Mapping(source = "post.title", target = "postDto.title")
    @Mapping(source = "post.text", target = "postDto.text")
    @Mapping(target = "postDto.commentsDto", ignore = true)
    @Mapping(target = "postDto.likesDto", ignore = true)
    @Mapping(target = "postDto.userDto", ignore = true)
    @Mapping(target = "postDto.categoriesDto", ignore = true)
    CommentDto toDto(Comment comment);

    @Mapping(source = "userDto", target = "user")
    @Mapping(source = "postDto.id", target = "post.id")
    @Mapping(target = "post.title", ignore = true)
    @Mapping(target = "post.text", ignore = true)
    @Mapping(target = "post.user", ignore = true)
    @Mapping(target = "post.comments", ignore = true)
    @Mapping(target = "post.likes", ignore = true)
    @Mapping(target = "post.categories", ignore = true)
    Comment toEntity(CommentDto commentDto);

    List<CommentDto> toDtoList(List<Comment> entities);
}
