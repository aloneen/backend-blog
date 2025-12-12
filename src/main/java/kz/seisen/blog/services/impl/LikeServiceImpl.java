package kz.seisen.blog.services.impl;


import kz.seisen.blog.dto.CategoryDto;
import kz.seisen.blog.dto.LikeDto;
import kz.seisen.blog.mapper.CategoryMapper;
import kz.seisen.blog.mapper.LikeMapper;
import kz.seisen.blog.repositories.CategoryRepository;
import kz.seisen.blog.repositories.LikeRepository;
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

        return likeMapper.toDto(likeRepository.save(likeMapper.toEntity(likeDto)));
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
