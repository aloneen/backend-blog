package kz.seisen.blog.services;


import kz.seisen.blog.dto.CommentDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Random;

@SpringBootTest
@Transactional
public class CommentServiceTest {

    @Autowired
    private CommentService service;


    @Test
    void getAllTest() {

        List<CommentDto> dtos = service.getAll();


        Assertions.assertNotNull(dtos);
        Assertions.assertNotEquals(0, dtos.size());

        for (CommentDto dto : dtos) {
            Assertions.assertNotNull(dto.getId());
            Assertions.assertNotNull(dto.getText());
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


        CommentDto dto = service.getById(someIndex);

        Assertions.assertNotNull(dto);
        Assertions.assertNotNull(dto.getId());
        Assertions.assertNotNull(dto.getText());
        Assertions.assertNotNull(dto.getUserId());
        Assertions.assertNotNull(dto.getUsername());
        Assertions.assertNotNull(dto.getPostId());



    }


    @Test
    void addTest() {

        int before = service.getAll().size();


        CommentDto commentDto = new CommentDto();
        commentDto.setText("Test Comment Text");
        commentDto.setUserId(1L);
        commentDto.setPostId(1L);

        CommentDto saved = service.create(commentDto);



        Assertions.assertNotNull(saved);
        Assertions.assertNotNull(saved.getId());
        Assertions.assertNotNull(saved.getText());
        Assertions.assertNotNull(saved.getUserId());
        Assertions.assertNotNull(saved.getPostId());



        CommentDto savedTest = service.getById(saved.getId());

        Assertions.assertNotNull(savedTest);
        Assertions.assertNotNull(savedTest.getId());
        Assertions.assertNotNull(savedTest.getText());
        Assertions.assertNotNull(savedTest.getUserId());
        Assertions.assertNotNull(savedTest.getPostId());



        Assertions.assertEquals(saved.getId(), savedTest.getId());
        Assertions.assertEquals(saved.getText(), savedTest.getText());
        Assertions.assertEquals(saved.getUserId(), savedTest.getUserId());
        Assertions.assertEquals(saved.getPostId(), savedTest.getPostId());


        int after = service.getAll().size();
        Assertions.assertEquals(before+1, after);



    }

    @Test
    void updateTest() {

        Random random = new Random();
        int randomIndex = random.nextInt(service.getAll().size());
        Long someIndex = service.getAll().get(randomIndex).getId();



        CommentDto newComment = new CommentDto(someIndex, "Updated Test Comment", 1L, "testuser", 1L);

        CommentDto updated = service.update(someIndex, newComment);

        Assertions.assertNotNull(updated);
        Assertions.assertNotNull(updated.getId());
        Assertions.assertNotNull(updated.getText());



        CommentDto updateTest = service.getById(someIndex);

        Assertions.assertNotNull(updateTest);
        Assertions.assertNotNull(updateTest.getId());
        Assertions.assertNotNull(updateTest.getText());



        Assertions.assertEquals(updated.getId(), updateTest.getId());
        Assertions.assertEquals(updated.getText(), updateTest.getText());


    }


    @Test
    void deleteTest() {
        int before = service.getAll().size();

        Random random = new Random();
        int randomIndex = random.nextInt(service.getAll().size());
        Long someIndex = service.getAll().get(randomIndex).getId();

        boolean deleted = service.delete(someIndex);
        Assertions.assertTrue(deleted);

        CommentDto deletedTest = service.getById(someIndex);
        Assertions.assertNull(deletedTest);


        int after = service.getAll().size();
        Assertions.assertEquals(before-1,after);



    }

}
