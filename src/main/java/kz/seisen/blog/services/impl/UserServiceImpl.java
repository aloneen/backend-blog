package kz.seisen.blog.services.impl;

import kz.seisen.blog.dto.UserCreateDto;
import kz.seisen.blog.dto.UserDto;
import kz.seisen.blog.mapper.UserMapper;
import kz.seisen.blog.models.Permission;
import kz.seisen.blog.models.User;
import kz.seisen.blog.repositories.PermissionRepository;
import kz.seisen.blog.repositories.UserRepository;
import kz.seisen.blog.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class UserServiceImpl implements UserService {


    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private PermissionRepository permissionRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public List<UserDto> getAll() {
        return userMapper.toDtoList(userRepository.findAll());
    }

    @Override
    public UserDto getById(Long id) {
        return userMapper.toDto(userRepository.findById(id).orElse(null));
    }

    @Override
    public UserDto getByEmail(String email) {
        return userMapper.toDto(userRepository.findByEmail(email));
    }

    @Override
    public UserDto create(UserCreateDto userCreateDto) {
        if (Objects.isNull(userCreateDto)) return null;
        User responseUser = new User();

        User check = userRepository.findByEmail(userCreateDto.getEmail());
        if (check == null){
            User newUser = new User();
            newUser.setUsername(userCreateDto.getUsername());
            newUser.setEmail(userCreateDto.getEmail());
            newUser.setPassword(passwordEncoder.encode(userCreateDto.getPassword()));
            List<Permission> permissions = List.of(permissionRepository.findByName("ROLE_USER"));

            newUser.setPermissions(permissions);
            responseUser = userRepository.save(newUser);
        }


        return userMapper.toDto(responseUser);
    }

    @Override
    public UserDto update(Long id, UserCreateDto userCreateDto) {
        User old = userRepository.findById(id).orElse(null);

        if (Objects.isNull(old) || Objects.isNull(userCreateDto)) {
            return null;
        }


        old.setUsername(userCreateDto.getUsername());
        old.setEmail(userCreateDto.getEmail());

        if (userCreateDto.getPassword() != null && !userCreateDto.getPassword().isEmpty()) {
            old.setPassword(passwordEncoder.encode(userCreateDto.getPassword()));
        }

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

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(username);

        if (Objects.nonNull(user)) {
            return user;
        }

        throw new UsernameNotFoundException("User not found");
    }
}
