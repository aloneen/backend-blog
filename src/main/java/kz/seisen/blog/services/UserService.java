package kz.seisen.blog.services;

import kz.seisen.blog.dto.UserCreateDto;
import kz.seisen.blog.dto.UserDto;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {

    List<UserDto> getAll();
    UserDto getById(Long id);
    UserDto getByEmail(String email);
    UserDto create(UserCreateDto userCreateDto);
    UserDto update(Long id, UserCreateDto userCreateDto);
    boolean delete(Long id);

}
