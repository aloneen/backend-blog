package kz.seisen.blog.mapper;


import kz.seisen.blog.dto.UserCreateDto;
import kz.seisen.blog.dto.UserDto;
import kz.seisen.blog.models.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", uses = {PostMapper.class, LikeMapper.class})
public interface UserMapper {

    @Mapping(source = "posts", target = "postsDto")
    @Mapping(source = "comments", target = "commentsDto", qualifiedByName = "userMapperCommentToDto")
    @Mapping(source = "likes", target = "likesDto")
    @Mapping(target = "postsDto[].commentsDto", ignore = true)
    @Mapping(target = "postsDto[].likesDto", ignore = true)
    @Mapping(target = "postsDto[].userDto", ignore = true)
    UserDto toDto(User user);
    
    @org.mapstruct.Named("userMapperCommentToDto")
    default kz.seisen.blog.dto.CommentDto userMapperCommentToDto(kz.seisen.blog.models.Comment comment) {
        if (comment == null) return null;
        kz.seisen.blog.dto.CommentDto dto = new kz.seisen.blog.dto.CommentDto();
        dto.setId(comment.getId());
        dto.setText(comment.getText());
        return dto;
    }

    User toEntity(UserCreateDto userCreateDto);

    @Mapping(source = "postsDto", target = "posts")
    @Mapping(source = "commentsDto", target = "comments", qualifiedByName = "userMapperCommentToEntity")
    @Mapping(source = "likesDto", target = "likes")
    User toEntity(UserDto userDto);
    
    @org.mapstruct.Named("userMapperCommentToEntity")
    default kz.seisen.blog.models.Comment userMapperCommentToEntity(kz.seisen.blog.dto.CommentDto dto) {
        if (dto == null) return null;
        kz.seisen.blog.models.Comment comment = new kz.seisen.blog.models.Comment();
        comment.setId(dto.getId());
        comment.setText(dto.getText());
        return comment;
    }

    List<UserDto> toDtoList(List<User> entities);
}
