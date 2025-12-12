package kz.seisen.blog.services;


import kz.seisen.blog.dto.PostCreateDto;
import kz.seisen.blog.dto.PostDto;

import java.util.List;

public interface PostService {


    List<PostDto> getAll();
    PostDto getById(Long id);
    PostDto create(PostCreateDto postCreateDto);
    PostDto update(Long id, PostCreateDto postCreateDto);
    boolean delete(Long id);


}
