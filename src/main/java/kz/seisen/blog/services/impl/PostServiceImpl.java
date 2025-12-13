package kz.seisen.blog.services.impl;

import kz.seisen.blog.dto.PostCreateDto;
import kz.seisen.blog.dto.PostDto;
import kz.seisen.blog.mapper.PostMapper;
import kz.seisen.blog.models.Category;
import kz.seisen.blog.models.Post;
import kz.seisen.blog.models.User;
import kz.seisen.blog.repositories.CategoryRepository;
import kz.seisen.blog.repositories.PostRepository;
import kz.seisen.blog.repositories.UserRepository;
import kz.seisen.blog.services.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;
    private final PostMapper postMapper;
    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;

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

        Post post = new Post();
        post.setTitle(postCreateDto.getTitle());
        post.setText(postCreateDto.getText());
        
        if (postCreateDto.getUserId() != null) {
            User user = userRepository.findById(postCreateDto.getUserId()).orElse(null);
            post.setUser(user);
        }
        
        if (postCreateDto.getCategoryIds() != null && !postCreateDto.getCategoryIds().isEmpty()) {
            List<Category> categories = postCreateDto.getCategoryIds().stream()
                .map(categoryRepository::findById)
                .filter(java.util.Optional::isPresent)
                .map(java.util.Optional::get)
                .collect(Collectors.toList());
            post.setCategories(categories);
        }

        return postMapper.toDto(postRepository.save(post));
    }

    @Override
    public PostDto update(Long id, PostCreateDto postCreateDto) {
        Post existingPost = postRepository.findById(id).orElse(null);
        
        if (Objects.isNull(existingPost) || Objects.isNull(postCreateDto)) {
            return null;
        }
        
        existingPost.setTitle(postCreateDto.getTitle());
        existingPost.setText(postCreateDto.getText());
        
        if (postCreateDto.getCategoryIds() != null) {
            List<Category> categories = postCreateDto.getCategoryIds().stream()
                .map(categoryRepository::findById)
                .filter(java.util.Optional::isPresent)
                .map(java.util.Optional::get)
                .collect(Collectors.toList());
            existingPost.setCategories(categories);
        }
        
        return postMapper.toDto(postRepository.save(existingPost));
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
