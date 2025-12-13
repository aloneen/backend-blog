package kz.seisen.blog.services.impl;

import kz.seisen.blog.dto.CommentDto;
import kz.seisen.blog.mapper.CommentMapper;
import kz.seisen.blog.models.Comment;
import kz.seisen.blog.models.Post;
import kz.seisen.blog.models.User;
import kz.seisen.blog.repositories.CommentRepository;
import kz.seisen.blog.repositories.PostRepository;
import kz.seisen.blog.repositories.UserRepository;
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
    private final UserRepository userRepository;
    private final PostRepository postRepository;

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

        Comment comment = new Comment();
        comment.setText(commentDto.getText());
        
        if (commentDto.getUserId() != null) {
            User user = userRepository.findById(commentDto.getUserId()).orElse(null);
            comment.setUser(user);
        }
        
        if (commentDto.getPostId() != null) {
            Post post = postRepository.findById(commentDto.getPostId()).orElse(null);
            comment.setPost(post);
        }

        return commentMapper.toDto(commentRepository.save(comment));
    }

    @Override
    public CommentDto update(Long id, CommentDto commentDto) {
        Comment existingComment = commentRepository.findById(id).orElse(null);

        if (Objects.isNull(existingComment) || Objects.isNull(commentDto)) {
            return null;
        }

        existingComment.setText(commentDto.getText());

        return commentMapper.toDto(commentRepository.save(existingComment));
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
