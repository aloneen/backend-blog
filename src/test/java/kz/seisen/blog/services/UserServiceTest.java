package kz.seisen.blog.services;


import kz.seisen.blog.dto.UserCreateDto;
import kz.seisen.blog.dto.UserDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Random;

@SpringBootTest
@Transactional
public class UserServiceTest {

    @Autowired
    private UserService service;


    @Test
    void getAllTest() {

        List<UserDto> dtos = service.getAll();


        Assertions.assertNotNull(dtos);
        Assertions.assertNotEquals(0, dtos.size());

        for (UserDto dto : dtos) {
            Assertions.assertNotNull(dto.getId());
            Assertions.assertNotNull(dto.getUsername());
            Assertions.assertNotNull(dto.getEmail());
        }

    }


    @Test
    void getByIdTest(){
        Random random = new Random();
        int randomIndex = random.nextInt(service.getAll().size());
        Long someIndex = service.getAll().get(randomIndex).getId();


        UserDto dto = service.getById(someIndex);

        Assertions.assertNotNull(dto);
        Assertions.assertNotNull(dto.getId());
        Assertions.assertNotNull(dto.getUsername());
        Assertions.assertNotNull(dto.getEmail());



    }


    @Test
    void addTest() {

        int before = service.getAll().size();



        String uniqueEmail = "testuser" + System.currentTimeMillis() + "@example.com";
        UserCreateDto userCreateDto = new UserCreateDto();
        userCreateDto.setUsername("TestUser" + System.currentTimeMillis());
        userCreateDto.setEmail(uniqueEmail);
        userCreateDto.setPassword("testpassword123");

        UserDto saved = service.create(userCreateDto);



        Assertions.assertNotNull(saved);
        Assertions.assertNotNull(saved.getId());
        Assertions.assertNotNull(saved.getUsername());
        Assertions.assertNotNull(saved.getEmail());



        UserDto savedTest = service.getById(saved.getId());

        Assertions.assertNotNull(savedTest);
        Assertions.assertNotNull(savedTest.getId());
        Assertions.assertNotNull(savedTest.getUsername());
        Assertions.assertNotNull(savedTest.getEmail());



        Assertions.assertEquals(saved.getId(), savedTest.getId());
        Assertions.assertEquals(saved.getUsername(), savedTest.getUsername());
        Assertions.assertEquals(saved.getEmail(), savedTest.getEmail());


        int after = service.getAll().size();
        Assertions.assertEquals(before+1, after);



    }

    @Test
    void updateTest() {

        Random random = new Random();
        int randomIndex = random.nextInt(service.getAll().size());
        Long someIndex = service.getAll().get(randomIndex).getId();



        UserCreateDto newUser = new UserCreateDto();
        newUser.setUsername("UpdatedTestUser");
        newUser.setEmail("updatedtest@example.com");
        newUser.setPassword("newpassword123");

        UserDto updated = service.update(someIndex, newUser);

        Assertions.assertNotNull(updated);
        Assertions.assertNotNull(updated.getId());
        Assertions.assertNotNull(updated.getUsername());
        Assertions.assertNotNull(updated.getEmail());



        UserDto updateTest = service.getById(someIndex);

        Assertions.assertNotNull(updateTest);
        Assertions.assertNotNull(updateTest.getId());
        Assertions.assertNotNull(updateTest.getUsername());
        Assertions.assertNotNull(updateTest.getEmail());



        Assertions.assertEquals(updated.getId(), updateTest.getId());
        Assertions.assertEquals(updated.getUsername(), updateTest.getUsername());
        Assertions.assertEquals(updated.getEmail(), updateTest.getEmail());


    }


    @Test
    void deleteTest() {

        String uniqueEmail = "deletetest" + System.currentTimeMillis() + "@example.com";
        UserCreateDto userCreateDto = new UserCreateDto();
        userCreateDto.setUsername("DeleteTestUser" + System.currentTimeMillis());
        userCreateDto.setEmail(uniqueEmail);
        userCreateDto.setPassword("testpassword123");

        UserDto newUser = service.create(userCreateDto);
        Assertions.assertNotNull(newUser);
        Long userIdToDelete = newUser.getId();

        int before = service.getAll().size();

        boolean deleted = service.delete(userIdToDelete);
        Assertions.assertTrue(deleted);

        UserDto deletedTest = service.getById(userIdToDelete);
        Assertions.assertNull(deletedTest);


        int after = service.getAll().size();
        Assertions.assertEquals(before-1,after);



    }
}
