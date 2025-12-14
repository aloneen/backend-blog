package kz.seisen.blog.mappers;


import kz.seisen.blog.dto.CommentDto;
import kz.seisen.blog.mapper.CommentMapper;
import kz.seisen.blog.models.Comment;
import kz.seisen.blog.models.Post;
import kz.seisen.blog.models.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class CommentMapperTest {

    @Autowired
    private CommentMapper mapper;

    @Test
    void convertEntityToDtoTest() {
        User user = new User(1L, "testuser", "test@example.com", "password", null, null, null, null);
        Post post = new Post(1L, "Post Title", "Post Text", user, null, null, null);
        Comment entity = new Comment(1L, "Comment Text", user, post);

        CommentDto dto = mapper.toDto(entity);

        Assertions.assertNotNull(dto);
        Assertions.assertNotNull(dto.getId());
        Assertions.assertNotNull(dto.getText());
        Assertions.assertNotNull(dto.getUserId());
        Assertions.assertNotNull(dto.getUsername());
        Assertions.assertNotNull(dto.getPostId());

        Assertions.assertEquals(entity.getId(), dto.getId());
        Assertions.assertEquals(entity.getText(), dto.getText());
        Assertions.assertEquals(entity.getUser().getId(), dto.getUserId());
        Assertions.assertEquals(entity.getUser().getActualUsername(), dto.getUsername());
        Assertions.assertEquals(entity.getPost().getId(), dto.getPostId());
    }

    @Test
    void convertDtoToEntityTest() {
        CommentDto dto = new CommentDto(1L, "Comment Text", 1L, "testuser", 1L);

        Comment entity = mapper.toEntity(dto);

        Assertions.assertNotNull(entity);
        Assertions.assertNotNull(entity.getText());

        Assertions.assertEquals(dto.getText(), entity.getText());

    }

    @Test
    void convertEntityListToDtoList() {
        User user = new User(1L, "testuser", "test@example.com", "password", null, null, null, null);
        Post post = new Post(1L, "Post Title", "Post Text", user, null, null, null);

        List<Comment> entities = new ArrayList<>();
        entities.add(new Comment(1L, "Comment1", user, post));
        entities.add(new Comment(2L, "Comment2", user, post));
        entities.add(new Comment(3L, "Comment3", user, post));

        List<CommentDto> dtos = mapper.toDtoList(entities);

        Assertions.assertNotNull(dtos);
        Assertions.assertNotEquals(dtos.size(), 0);
        Assertions.assertEquals(dtos.size(), entities.size());

        for (int i = 0; i < dtos.size(); i++) {
            Assertions.assertNotNull(dtos.get(i));
            Assertions.assertNotNull(dtos.get(i).getId());
            Assertions.assertNotNull(dtos.get(i).getText());
            Assertions.assertNotNull(dtos.get(i).getUserId());
            Assertions.assertNotNull(dtos.get(i).getUsername());
            Assertions.assertNotNull(dtos.get(i).getPostId());

            Assertions.assertEquals(dtos.get(i).getId(), entities.get(i).getId());
            Assertions.assertEquals(dtos.get(i).getText(), entities.get(i).getText());
            Assertions.assertEquals(dtos.get(i).getUserId(), entities.get(i).getUser().getId());
            Assertions.assertEquals(dtos.get(i).getUsername(), entities.get(i).getUser().getActualUsername());
            Assertions.assertEquals(dtos.get(i).getPostId(), entities.get(i).getPost().getId());
        }
    }
}
