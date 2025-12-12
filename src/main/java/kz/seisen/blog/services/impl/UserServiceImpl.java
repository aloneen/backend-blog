package kz.seisen.blog.services.impl;


import kz.seisen.blog.dto.PostCreateDto;
import kz.seisen.blog.dto.PostDto;
import kz.seisen.blog.dto.UserCreateDto;
import kz.seisen.blog.dto.UserDto;
import kz.seisen.blog.mapper.PostMapper;
import kz.seisen.blog.mapper.UserMapper;
import kz.seisen.blog.models.User;
import kz.seisen.blog.repositories.PostRepository;
import kz.seisen.blog.repositories.UserRepository;
import kz.seisen.blog.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {


    private final UserRepository userRepository;

    private final UserMapper userMapper;

    @Override
    public List<UserDto> getAll() {
        return userMapper.toDtoList(userRepository.findAll());
    }

    @Override
    public UserDto getById(Long id) {
        return userMapper.toDto(userRepository.findById(id).orElse(null));
    }

    @Override
    public UserDto create(UserCreateDto userCreateDto) {
        if (Objects.isNull(userCreateDto)) return null;

        return userMapper.toDto(userRepository.save(userMapper.toEntity(userCreateDto)));
    }

    @Override
    public UserDto update(Long id, UserCreateDto userCreateDto) {
        User old = userRepository.findById(id).orElse(null);

        if (Objects.isNull(old) || Objects.isNull(userCreateDto)) {
            return null;
        }


        old.setUsername(userCreateDto.getUsername());
        old.setEmail(userCreateDto.getEmail());
        old.setPassword(userCreateDto.getPassword());

        return userMapper.toDto(userRepository.save(old));
    }

    @Override
    public boolean delete(Long id) {

        userRepository.deleteById(id);

        UserDto delete = getById(id);

        if (Objects.isNull(delete)) {
            return true;
        }else {
            return false;
        }

    }

}
