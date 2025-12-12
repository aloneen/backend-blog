package kz.seisen.blog.mapper;


import kz.seisen.blog.dto.CommentDto;
import kz.seisen.blog.models.Comment;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper( componentModel = "spring", uses = { PostMapper.class, UserMapper.class })
public interface CommentMapper {

    @Mapping(source = "user", target = "userDto")
    @Mapping(source = "post", target = "postDto")
    CommentDto toDto(Comment comment);


    @Mapping(source = "userDto", target = "user")
    @Mapping(source = "postDto", target = "post")
    Comment toEntity(CommentDto commentDto);

    List<CommentDto> toDtoList(List<Comment> entities);
}
