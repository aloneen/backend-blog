package kz.seisen.blog.services;


import kz.seisen.blog.dto.LikeDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Random;

@SpringBootTest
@Transactional
public class LikeServiceTest {

    @Autowired
    private LikeService service;


    @Test
    void getAllTest() {

        List<LikeDto> dtos = service.getAll();


        Assertions.assertNotNull(dtos);
        Assertions.assertNotEquals(0, dtos.size());

        for (LikeDto dto : dtos) {
            Assertions.assertNotNull(dto.getId());
            Assertions.assertNotNull(dto.getUserId());
            Assertions.assertNotNull(dto.getUsername());
            Assertions.assertNotNull(dto.getPostId());
        }

    }


    @Test
    void getByIdTest(){
        Random random = new Random();
        int randomIndex = random.nextInt(service.getAll().size());
        Long someIndex = service.getAll().get(randomIndex).getId();


        LikeDto dto = service.getById(someIndex);

        Assertions.assertNotNull(dto);
        Assertions.assertNotNull(dto.getId());
        Assertions.assertNotNull(dto.getUserId());
        Assertions.assertNotNull(dto.getUsername());
        Assertions.assertNotNull(dto.getPostId());



    }


    @Test
    void addTest() {

        int before = service.getAll().size();


        LikeDto likeDto = new LikeDto();
        likeDto.setUserId(1L);
        likeDto.setPostId(1L);

        LikeDto saved = service.create(likeDto);



        Assertions.assertNotNull(saved);
        Assertions.assertNotNull(saved.getId());
        Assertions.assertNotNull(saved.getUserId());
        Assertions.assertNotNull(saved.getPostId());



        LikeDto savedTest = service.getById(saved.getId());

        Assertions.assertNotNull(savedTest);
        Assertions.assertNotNull(savedTest.getId());
        Assertions.assertNotNull(savedTest.getUserId());
        Assertions.assertNotNull(savedTest.getPostId());



        Assertions.assertEquals(saved.getId(), savedTest.getId());
        Assertions.assertEquals(saved.getUserId(), savedTest.getUserId());
        Assertions.assertEquals(saved.getPostId(), savedTest.getPostId());


        int after = service.getAll().size();
        Assertions.assertEquals(before+1, after);



    }

    @Test
    void deleteTest() {
        int before = service.getAll().size();

        Random random = new Random();
        int randomIndex = random.nextInt(service.getAll().size());
        Long someIndex = service.getAll().get(randomIndex).getId();

        boolean deleted = service.delete(someIndex);
        Assertions.assertTrue(deleted);

        LikeDto deletedTest = service.getById(someIndex);
        Assertions.assertNull(deletedTest);


        int after = service.getAll().size();
        Assertions.assertEquals(before-1,after);



    }
}
