package kz.seisen.blog.services.impl;

import kz.seisen.blog.dto.LikeDto;
import kz.seisen.blog.mapper.LikeMapper;
import kz.seisen.blog.models.Like;
import kz.seisen.blog.models.Post;
import kz.seisen.blog.models.User;
import kz.seisen.blog.repositories.LikeRepository;
import kz.seisen.blog.repositories.PostRepository;
import kz.seisen.blog.repositories.UserRepository;
import kz.seisen.blog.services.LikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class LikeServiceImpl implements LikeService {
    private final LikeRepository likeRepository;
    private final LikeMapper likeMapper;
    private final UserRepository userRepository;
    private final PostRepository postRepository;

    @Override
    public List<LikeDto> getAll() {
        return likeMapper.toDtoList(likeRepository.findAll());
    }

    @Override
    public LikeDto getById(Long id) {
        return likeMapper.toDto(likeRepository.findById(id).orElse(null));
    }

    @Override
    public LikeDto create(LikeDto likeDto) {
        if (Objects.isNull(likeDto)) return null;

        Like like = new Like();
        
        if (likeDto.getUserId() != null) {
            User user = userRepository.findById(likeDto.getUserId()).orElse(null);
            like.setUser(user);
        }
        
        if (likeDto.getPostId() != null) {
            Post post = postRepository.findById(likeDto.getPostId()).orElse(null);
            like.setPost(post);
        }

        return likeMapper.toDto(likeRepository.save(like));
    }



    @Override
    public boolean delete(Long id) {

        likeRepository.deleteById(id);

        LikeDto delete = getById(id);

        if (Objects.isNull(delete)) {
            return true;
        }else {
            return false;
        }

    }

}
