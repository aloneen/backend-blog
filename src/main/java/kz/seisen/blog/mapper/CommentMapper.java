package kz.seisen.blog.mapper;

import kz.seisen.blog.dto.CommentDto;
import kz.seisen.blog.models.Comment;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CommentMapper {
    @Mapping(source = "user.id", target = "userId")
    @Mapping(source = "user.username", target = "username")
    @Mapping(source = "post.id", target = "postId")
    CommentDto toDto(Comment comment);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "user", ignore = true)
    @Mapping(target = "post", ignore = true)
    Comment toEntity(CommentDto commentDto);

    List<CommentDto> toDtoList(List<Comment> entities);
}
