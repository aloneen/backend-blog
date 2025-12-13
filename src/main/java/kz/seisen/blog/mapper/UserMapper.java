package kz.seisen.blog.mapper;

import kz.seisen.blog.dto.UserCreateDto;
import kz.seisen.blog.dto.UserDto;
import kz.seisen.blog.models.User;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", uses = {PostMapper.class, CommentMapper.class, LikeMapper.class})
public interface UserMapper {
    UserDto toDto(User user);
    User toEntity(UserCreateDto userCreateDto);
    List<UserDto> toDtoList(List<User> entities);
}
