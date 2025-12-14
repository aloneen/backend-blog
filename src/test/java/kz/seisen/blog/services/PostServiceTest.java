package kz.seisen.blog.services;


import kz.seisen.blog.dto.PostCreateDto;
import kz.seisen.blog.dto.PostDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@SpringBootTest
@Transactional
public class PostServiceTest {

    @Autowired
    private PostService service;


    @Test
    void getAllTest() {

        List<PostDto> dtos = service.getAll();


        Assertions.assertNotNull(dtos);
        Assertions.assertNotEquals(0, dtos.size());

        for (PostDto dto : dtos) {
            Assertions.assertNotNull(dto.getId());
            Assertions.assertNotNull(dto.getTitle());
            Assertions.assertNotNull(dto.getText());
            Assertions.assertNotNull(dto.getUserId());
            Assertions.assertNotNull(dto.getUsername());
        }

    }


    @Test
    void getByIdTest(){
        Random random = new Random();
        int randomIndex = random.nextInt(service.getAll().size());
        Long someIndex = service.getAll().get(randomIndex).getId();


        PostDto dto = service.getById(someIndex);

        Assertions.assertNotNull(dto);
        Assertions.assertNotNull(dto.getId());
        Assertions.assertNotNull(dto.getTitle());
        Assertions.assertNotNull(dto.getText());
        Assertions.assertNotNull(dto.getUserId());
        Assertions.assertNotNull(dto.getUsername());



    }


    @Test
    void addTest() {

        int before = service.getAll().size();


        List<PostDto> existingPosts = service.getAll();
        Long validUserId = existingPosts.get(0).getUserId();
        Assertions.assertNotNull(validUserId);

        PostCreateDto postCreateDto = new PostCreateDto();
        postCreateDto.setTitle("Test Post Title");
        postCreateDto.setText("Test Post Text");
        postCreateDto.setUserId(validUserId);
        postCreateDto.setCategoryIds(new ArrayList<>());

        PostDto saved = service.create(postCreateDto);



        Assertions.assertNotNull(saved);
        Assertions.assertNotNull(saved.getId());
        Assertions.assertNotNull(saved.getTitle());
        Assertions.assertNotNull(saved.getText());



        PostDto savedTest = service.getById(saved.getId());

        Assertions.assertNotNull(savedTest);
        Assertions.assertNotNull(savedTest.getId());
        Assertions.assertNotNull(savedTest.getTitle());
        Assertions.assertNotNull(savedTest.getText());



        Assertions.assertEquals(saved.getId(), savedTest.getId());
        Assertions.assertEquals(saved.getTitle(), savedTest.getTitle());
        Assertions.assertEquals(saved.getText(), savedTest.getText());


        int after = service.getAll().size();
        Assertions.assertEquals(before+1, after);



    }

    @Test
    void updateTest() {

        Random random = new Random();
        int randomIndex = random.nextInt(service.getAll().size());
        Long someIndex = service.getAll().get(randomIndex).getId();


        List<PostDto> existingPosts = service.getAll();
        Long validUserId = existingPosts.get(0).getUserId();
        Assertions.assertNotNull(validUserId);

        PostCreateDto newPost = new PostCreateDto();
        newPost.setTitle("Updated Test Title");
        newPost.setText("Updated Test Text");
        newPost.setUserId(validUserId);
        newPost.setCategoryIds(new ArrayList<>());

        PostDto updated = service.update(someIndex, newPost);

        Assertions.assertNotNull(updated);
        Assertions.assertNotNull(updated.getId());
        Assertions.assertNotNull(updated.getTitle());
        Assertions.assertNotNull(updated.getText());



        PostDto updateTest = service.getById(someIndex);

        Assertions.assertNotNull(updateTest);
        Assertions.assertNotNull(updateTest.getId());
        Assertions.assertNotNull(updateTest.getTitle());
        Assertions.assertNotNull(updateTest.getText());



        Assertions.assertEquals(updated.getId(), updateTest.getId());
        Assertions.assertEquals(updated.getTitle(), updateTest.getTitle());
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

        PostDto deletedTest = service.getById(someIndex);
        Assertions.assertNull(deletedTest);


        int after = service.getAll().size();
        Assertions.assertEquals(before-1,after);



    }
}
