package kz.seisen.blog.mappers;


import kz.seisen.blog.dto.PostCreateDto;
import kz.seisen.blog.dto.PostDto;
import kz.seisen.blog.mapper.PostMapper;
import kz.seisen.blog.models.Post;
import kz.seisen.blog.models.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class PostMapperTest {

    @Autowired
    private PostMapper mapper;

    @Test
    void convertEntityToDtoTest() {
        User user = new User(1L, "testuser", "test@example.com", "password", null, null, null, null);
        Post entity = new Post(1L, "Test Title", "Test Text", user, null, null, null);

        PostDto dto = mapper.toDto(entity);

        Assertions.assertNotNull(dto);
        Assertions.assertNotNull(dto.getId());
        Assertions.assertNotNull(dto.getTitle());
        Assertions.assertNotNull(dto.getText());
        Assertions.assertNotNull(dto.getUserId());
        Assertions.assertNotNull(dto.getUsername());

        Assertions.assertEquals(entity.getId(), dto.getId());
        Assertions.assertEquals(entity.getTitle(), dto.getTitle());
        Assertions.assertEquals(entity.getText(), dto.getText());
        Assertions.assertEquals(entity.getUser().getId(), dto.getUserId());
        Assertions.assertEquals(entity.getUser().getActualUsername(), dto.getUsername());
    }

    @Test
    void convertDtoToEntityTest() {
        PostCreateDto dto = new PostCreateDto("Test Title", "Test Text", 1L, null);

        Post entity = mapper.toEntity(dto);

        Assertions.assertNotNull(entity);
        Assertions.assertNotNull(entity.getTitle());
        Assertions.assertNotNull(entity.getText());

        Assertions.assertEquals(dto.getTitle(), entity.getTitle());
        Assertions.assertEquals(dto.getText(), entity.getText());
    }

    @Test
    void convertEntityListToDtoList() {
        User user = new User(1L, "testuser", "test@example.com", "password", null, null, null, null);
        
        List<Post> entities = new ArrayList<>();
        entities.add(new Post(1L, "Title1", "Text1", user, null, null, null));
        entities.add(new Post(2L, "Title2", "Text2", user, null, null, null));
        entities.add(new Post(3L, "Title3", "Text3", user, null, null, null));

        List<PostDto> dtos = mapper.toDtoList(entities);

        Assertions.assertNotNull(dtos);
        Assertions.assertNotEquals(dtos.size(), 0);
        Assertions.assertEquals(dtos.size(), entities.size());

        for (int i = 0; i < dtos.size(); i++) {
            Assertions.assertNotNull(dtos.get(i));
            Assertions.assertNotNull(dtos.get(i).getId());
            Assertions.assertNotNull(dtos.get(i).getTitle());
            Assertions.assertNotNull(dtos.get(i).getText());
            Assertions.assertNotNull(dtos.get(i).getUserId());
            Assertions.assertNotNull(dtos.get(i).getUsername());

            Assertions.assertEquals(dtos.get(i).getId(), entities.get(i).getId());
            Assertions.assertEquals(dtos.get(i).getTitle(), entities.get(i).getTitle());
            Assertions.assertEquals(dtos.get(i).getText(), entities.get(i).getText());
            Assertions.assertEquals(dtos.get(i).getUserId(), entities.get(i).getUser().getId());
            Assertions.assertEquals(dtos.get(i).getUsername(), entities.get(i).getUser().getActualUsername());
        }
    }

}
