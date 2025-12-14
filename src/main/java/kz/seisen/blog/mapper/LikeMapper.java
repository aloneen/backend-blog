package kz.seisen.blog.mapper;

import kz.seisen.blog.dto.LikeDto;
import kz.seisen.blog.models.Like;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface LikeMapper {
    @Mapping(source = "user.id", target = "userId")
    @Mapping(source = "user.actualUsername", target = "username")
    @Mapping(source = "post.id", target = "postId")
    LikeDto toDto(Like entity);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "user", ignore = true)
    @Mapping(target = "post", ignore = true)
    Like toEntity(LikeDto dto);

    List<LikeDto> toDtoList(List<Like> entities);
}
