package kz.seisen.blog.mapper;


import kz.seisen.blog.dto.UserCreateDto;
import kz.seisen.blog.dto.UserDto;
import kz.seisen.blog.models.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", uses = { PostMapper.class, CommentMapper.class, LikeMapper.class})
public interface UserMapper {

        @Mapping(source = "posts", target = "postsDto")
        @Mapping(source = "comments", target = "commentsDto")
        @Mapping(source = "likes", target = "likesDto")
        UserDto toDto(User user);


        User toEntity(UserCreateDto userCreateDto);


        @Mapping(source = "postsDto", target = "posts")
        @Mapping(source = "commentsDto", target = "comments")
        @Mapping(source = "likesDto", target = "likes")
        User toEntity(UserDto userDto);

        List<UserDto> toDtoList(List<User> entities);



}
