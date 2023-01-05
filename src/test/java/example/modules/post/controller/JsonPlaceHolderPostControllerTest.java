package example.modules.post.controller;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

/**
 * We use @ActiveProfiles("test"), because :
 * <ul>
 *     <li>We using h2 database</li>
 *     <li>Because we using different database, we should create and store another yaml/config at test modules</li>
 * </ul>
 *
 * @see resources/application-test.yml
 */
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class JsonPlaceHolderPostControllerTest {

  @Test
  void getAllJsonPlaceHolderPost() throws Exception {

  }

  @Test
  void getAllJsonPlaceHolderPostPage() {
  }

  @Test
  void getJsonPlaceHolderPostById() {
  }

  @Test
  void addPost() throws Exception {

  }

}