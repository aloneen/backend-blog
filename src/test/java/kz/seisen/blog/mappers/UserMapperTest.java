package kz.seisen.blog.mappers;


import kz.seisen.blog.dto.UserCreateDto;
import kz.seisen.blog.dto.UserDto;
import kz.seisen.blog.mapper.UserMapper;
import kz.seisen.blog.models.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class UserMapperTest {

    @Autowired
    private UserMapper mapper;

    @Test
    void convertEntityToDtoTest() {
        User entity = new User(1L, "testuser", "test@example.com", "password", null, null, null, null);

        UserDto dto = mapper.toDto(entity);

        Assertions.assertNotNull(dto);
        Assertions.assertNotNull(dto.getId());
        Assertions.assertNotNull(dto.getUsername());
        Assertions.assertNotNull(dto.getEmail());

        Assertions.assertEquals(entity.getId(), dto.getId());
        Assertions.assertEquals(entity.getActualUsername(), dto.getUsername());
        Assertions.assertEquals(entity.getEmail(), dto.getEmail());
    }

    @Test
    void convertDtoToEntityTest() {
        UserCreateDto dto = new UserCreateDto("testuser", "test@example.com", "password");

        User entity = mapper.toEntity(dto);

        Assertions.assertNotNull(entity);
        Assertions.assertNotNull(entity.getActualUsername());
        Assertions.assertNotNull(entity.getEmail());
        Assertions.assertNotNull(entity.getPassword());

        Assertions.assertEquals(dto.getUsername(), entity.getActualUsername());
        Assertions.assertEquals(dto.getEmail(), entity.getEmail());
        Assertions.assertEquals(dto.getPassword(), entity.getPassword());
    }

    @Test
    void convertEntityListToDtoList() {
        List<User> entities = new ArrayList<>();
        entities.add(new User(1L, "user1", "user1@example.com", "password1", null, null, null, null));
        entities.add(new User(2L, "user2", "user2@example.com", "password2", null, null, null, null));
        entities.add(new User(3L, "user3", "user3@example.com", "password3", null, null, null, null));

        List<UserDto> dtos = mapper.toDtoList(entities);

        Assertions.assertNotNull(dtos);
        Assertions.assertNotEquals(dtos.size(), 0);
        Assertions.assertEquals(dtos.size(), entities.size());

        for (int i = 0; i < dtos.size(); i++) {
            Assertions.assertNotNull(dtos.get(i));
            Assertions.assertNotNull(dtos.get(i).getId());
            Assertions.assertNotNull(dtos.get(i).getUsername());
            Assertions.assertNotNull(dtos.get(i).getEmail());

            Assertions.assertEquals(dtos.get(i).getId(), entities.get(i).getId());
            Assertions.assertEquals(dtos.get(i).getUsername(), entities.get(i).getActualUsername());
            Assertions.assertEquals(dtos.get(i).getEmail(), entities.get(i).getEmail());
        }
    }

}
