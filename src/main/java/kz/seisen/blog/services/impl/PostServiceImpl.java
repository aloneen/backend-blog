package kz.seisen.blog.services.impl;


import kz.seisen.blog.dto.PostCreateDto;
import kz.seisen.blog.dto.PostDto;
import kz.seisen.blog.mapper.PostMapper;
import kz.seisen.blog.models.Post;
import kz.seisen.blog.repositories.PostRepository;
import kz.seisen.blog.services.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;

    private final PostMapper postMapper;

    @Override
    public List<PostDto> getAll() {
        return postMapper.toDtoList(postRepository.findAll());
    }

    @Override
    public PostDto getById(Long id) {
        return postMapper.toDto(postRepository.findById(id).orElse(null));
    }

    @Override
    public PostDto create(PostCreateDto postCreateDto) {
        if (Objects.isNull(postCreateDto)) return null;

        return postMapper.toDto(postRepository.save(postMapper.toEntity(postCreateDto)));
    }

    @Override
    public PostDto update(Long id, PostCreateDto postCreateDto) {
       PostDto oldPost = getById(id);

       if (Objects.isNull(oldPost) || Objects.isNull(postCreateDto)) {
           return null;
       }


       oldPost.setTitle(postCreateDto.getTitle());
       oldPost.setText(postCreateDto.getText());
       oldPost.setCategoriesDto(postCreateDto.getCategoriesDto());

       return postMapper.toDto(postRepository.save(postMapper.toEntity(oldPost)));
    }

    @Override
    public boolean delete(Long id) {

        postRepository.deleteById(id);

        PostDto delete = getById(id);

        if (Objects.isNull(delete)) {
            return true;
        }else {
            return false;
        }

    }
}
