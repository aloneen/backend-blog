package kz.seisen.blog.mapper;


import kz.seisen.blog.dto.PostCreateDto;
import kz.seisen.blog.dto.PostDto;
import kz.seisen.blog.models.Post;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", uses = {LikeMapper.class, CategoryMapper.class})
public interface PostMapper {

    @Mapping(source = "user.id", target = "userDto.id")
    @Mapping(source = "user.username", target = "userDto.username")
    @Mapping(source = "user.email", target = "userDto.email")
    @Mapping(target = "userDto.postsDto", ignore = true)
    @Mapping(target = "userDto.commentsDto", ignore = true)
    @Mapping(target = "userDto.likesDto", ignore = true)
    @Mapping(source = "comments", target = "commentsDto", qualifiedByName = "postMapperCommentToDto")
    @Mapping(source = "likes", target = "likesDto")
    @Mapping(source = "categories", target = "categoriesDto")
    PostDto toDto(Post post);
    
    @org.mapstruct.Named("postMapperCommentToDto")
    default kz.seisen.blog.dto.CommentDto postMapperCommentToDto(kz.seisen.blog.models.Comment comment) {
        if (comment == null) return null;
        kz.seisen.blog.dto.CommentDto dto = new kz.seisen.blog.dto.CommentDto();
        dto.setId(comment.getId());
        dto.setText(comment.getText());
        return dto;
    }

    @Mapping(source = "userDto.id", target = "user.id")
    @Mapping(target = "user.username", ignore = true)
    @Mapping(target = "user.email", ignore = true)
    @Mapping(target = "user.password", ignore = true)
    @Mapping(target = "user.posts", ignore = true)
    @Mapping(target = "user.comments", ignore = true)
    @Mapping(target = "user.likes", ignore = true)
    @Mapping(source = "categoriesDto", target = "categories")
    Post toEntity(PostCreateDto postCreateDto);

    @Mapping(source = "userDto.id", target = "user.id")
    @Mapping(target = "user.username", ignore = true)
    @Mapping(target = "user.email", ignore = true)
    @Mapping(target = "user.password", ignore = true)
    @Mapping(target = "user.posts", ignore = true)
    @Mapping(target = "user.comments", ignore = true)
    @Mapping(target = "user.likes", ignore = true)
    @Mapping(source = "commentsDto", target = "comments", qualifiedByName = "postMapperCommentToEntity")
    @Mapping(source = "likesDto", target = "likes")
    @Mapping(source = "categoriesDto", target = "categories")
    Post toEntity(PostDto postDto);
    
    @org.mapstruct.Named("postMapperCommentToEntity")
    default kz.seisen.blog.models.Comment postMapperCommentToEntity(kz.seisen.blog.dto.CommentDto dto) {
        if (dto == null) return null;
        kz.seisen.blog.models.Comment comment = new kz.seisen.blog.models.Comment();
        comment.setId(dto.getId());
        comment.setText(dto.getText());
        return comment;
    }

    List<PostDto> toDtoList(List<Post> entities);
}
