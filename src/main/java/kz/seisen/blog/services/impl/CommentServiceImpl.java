package kz.seisen.blog.services.impl;


import kz.seisen.blog.dto.CommentDto;
import kz.seisen.blog.mapper.CommentMapper;
import kz.seisen.blog.repositories.CommentRepository;
import kz.seisen.blog.services.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {


    private final CommentRepository commentRepository;

    private final CommentMapper commentMapper;

    @Override
    public List<CommentDto> getAll() {
        return commentMapper.toDtoList(commentRepository.findAll());
    }

    @Override
    public CommentDto getById(Long id) {
        return commentMapper.toDto(commentRepository.findById(id).orElse(null));
    }

    @Override
    public CommentDto create(CommentDto commentDto) {
        if (Objects.isNull(commentDto)) return null;

        return commentMapper.toDto(commentRepository.save(commentMapper.toEntity(commentDto)));
    }

    @Override
    public CommentDto update(Long id, CommentDto commentDto) {
        CommentDto old = getById(id);

        if (Objects.isNull(old) || Objects.isNull(commentDto)) {
            return null;
        }

        old.setText(commentDto.getText());
//        old.setUserDto(commentDto.getUserDto());
//        old.setPostDto(commentDto.getPostDto());


        return commentMapper.toDto(commentRepository.save(commentMapper.toEntity(old)));
    }

    @Override
    public boolean delete(Long id) {

        commentRepository.deleteById(id);

        CommentDto delete = getById(id);

        if (Objects.isNull(delete)) {
            return true;
        }else {
            return false;
        }

    }

}
