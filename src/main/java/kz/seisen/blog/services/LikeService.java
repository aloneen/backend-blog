package kz.seisen.blog.services;

import kz.seisen.blog.dto.LikeDto;
import kz.seisen.blog.dto.PostCreateDto;
import kz.seisen.blog.dto.PostDto;
import kz.seisen.blog.models.Like;

import java.util.List;

public interface LikeService {

    List<LikeDto> getAll();
    LikeDto getById(Long id);
    LikeDto create(LikeDto likeDto);
    LikeDto update(Long id, LikeDto likeDto);
    boolean delete(Long id);

}
