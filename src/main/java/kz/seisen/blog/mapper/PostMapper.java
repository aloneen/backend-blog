package kz.seisen.blog.mapper;


import kz.seisen.blog.dto.PostCreateDto;
import kz.seisen.blog.dto.PostDto;
import kz.seisen.blog.models.Post;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", uses = {CommentMapper.class, LikeMapper.class, CategoryMapper.class, UserMapper.class})
public interface PostMapper {

    @Mapping(source = "user", target = "userDto")
    @Mapping(source = "comments", target = "commentsDto")
    @Mapping(source = "likes", target = "likesDto")
    @Mapping(source = "categories", target = "categoriesDto")
    PostDto toDto(Post post);


    @Mapping(target = "id")
    @Mapping(source = "userDto", target = "user")
    @Mapping(source = "categoriesDto", target = "categories")
    Post toEntity(PostCreateDto postCreateDto);


    @Mapping(source = "userDto", target = "user")
    @Mapping(source = "commentsDto", target = "comments")
    @Mapping(source = "likesDto", target = "likes")
    @Mapping(source = "categoriesDto", target = "categories")
    Post toEntity(PostDto postDto);

    List<PostDto> toDtoList(List<Post> entities);

}
