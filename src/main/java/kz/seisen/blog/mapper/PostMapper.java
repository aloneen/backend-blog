package kz.seisen.blog.mapper;

import kz.seisen.blog.dto.PostDto;
import kz.seisen.blog.models.Post;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", uses = {CommentMapper.class, LikeMapper.class, CategoryMapper.class})
public interface PostMapper {
    @Mapping(source = "user.id", target = "userId")
    @Mapping(source = "user.actualUsername", target = "username")
    PostDto toDto(Post post);

    List<PostDto> toDtoList(List<Post> entities);
}
