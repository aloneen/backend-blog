package kz.seisen.blog.services;

import kz.seisen.blog.dto.CommentDto;


import java.util.List;

public interface CommentService {


    List<CommentDto> getAll();
    CommentDto getById(Long id);
    CommentDto create(CommentDto commentDto);
    CommentDto update(Long id, CommentDto commentDto);
    boolean delete(Long id);

}
