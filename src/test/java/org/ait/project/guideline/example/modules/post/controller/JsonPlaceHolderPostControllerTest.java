package org.ait.project.guideline.example.modules.post.controller;

import org.ait.project.guideline.example.modules.post.model.entity.JsonPlaceHolderPost;
import org.ait.project.guideline.example.modules.post.model.repository.JsonPlaceHolderPostRepository;
import org.ait.project.guideline.example.shared.openfeign.jsonplaceholder.JsonPlaceHolderClient;
import org.ait.project.guideline.example.shared.openfeign.jsonplaceholder.request.CreatePostRequest;
import org.ait.project.guideline.example.shared.openfeign.jsonplaceholder.response.PostResponse;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.ZonedDateTime;
import java.util.Collections;
import java.util.List;

/**
 * We use @ActiveProfiles("test"), because :
 * <ul>
 *     <li>We using h2 database</li>
 *     <li>Because we using different database, we should create and store another yaml/config at test modules</li>
 * </ul>
 *
 * @see resources/application-test.yml
 * @author @fadhylfa / @frhn9
 */
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class JsonPlaceHolderPostControllerTest {

  /**
   * Dont worry about database, we use H2 database as defined at application-test.yaml.
   * <br>
   * but, for OpenFeign Client, we mock it using Annotation @MockBean to mock it
   */
  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private JsonPlaceHolderClient jsonPlaceHolderClient;

  @Autowired
  JsonPlaceHolderPostRepository jsonPlaceHolderPostRepository;

  @BeforeEach
  void setup() {
    JsonPlaceHolderPost jsonPlaceHolderPost = new JsonPlaceHolderPost();
    jsonPlaceHolderPost.setId(1);
    jsonPlaceHolderPost.setTitle("hanya sebuah title");
    jsonPlaceHolderPost.setBody("sexeh");
    jsonPlaceHolderPost.setUserId(666);
    jsonPlaceHolderPost.setCreateAt(ZonedDateTime.now());

    jsonPlaceHolderPostRepository.save(jsonPlaceHolderPost);
  }

  /**
   * This method will test endpoint GET: {url}/post
   * <br>Expected result is the endpoint will returning field response_schema, response_code, and response_message
   *
   * @throws Exception
   * @see <a href="https://documenter.getpostman.com/view/25201895/2s8Z73xVro#ef29d6fd-c193-4935-88ee-61a2077f782a">Postman API link</a>
   * @author @fadhylfa / @frhn9
   */
  @Test
  @Order(1)
  void get_all_jsonplaceholder_post() throws Exception {
    jsonPlaceHolderPostRepository.deleteAll();

    PostResponse postResponse = new PostResponse();
    postResponse.setId(1);
    postResponse.setTitle("sebuah title");
    postResponse.setBody("sebuah body");
    postResponse.setUserId(1);
    List<PostResponse> postResponseList = Collections.singletonList(postResponse);

    Mockito.when(jsonPlaceHolderClient.getListPost()).thenReturn(postResponseList);

    mockMvc.perform(MockMvcRequestBuilders.get("/post")
            .contentType(MediaType.APPLICATION_JSON))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
        .andExpect(MockMvcResultMatchers.content().string(Matchers.containsString("response_schema")))
        .andExpect(MockMvcResultMatchers.content().string(Matchers.containsString("response_code")))
        .andExpect(MockMvcResultMatchers.content().string(Matchers.containsString("response_message")))
        .andExpect(MockMvcResultMatchers.jsonPath("$.response_output.list.content").isArray())
        .andExpect(MockMvcResultMatchers.jsonPath("$.response_output.list.content", Matchers.hasSize(1)));
  }

  @Test
  @Order(2)
  void getAllJsonPlaceHolderPostPage() {
  }

   /**
   * This method will test endpoint GET: {url}/{id}
   * <ul>
   * <li>This endpoint use annotation @Query for repository method</li>
   * <li>Expected result is success so it will return HTTP 200 OK</li>
   * </ul>
   *
   * @throws Exception
   * @see <a href="https://documenter.getpostman.com/view/25201895/2s8Z73xVro#4311eb97-078e-442f-a2c2-e625d480a2b2">Postman API link</a>
   * @author @fadhylfa / @frhn9
   */
  @Test
  @Order(3)
  void get_jsonplaceholderpost_by_id_when_found() throws Exception {
    mockMvc.perform(MockMvcRequestBuilders.get("/1")
            .contentType(MediaType.APPLICATION_JSON))
        .andExpect(MockMvcResultMatchers.status().isOk());
  }

  /**
   * This method will test endpoint GET: {url}/{id}
   * <ul>
   * <li>This endpoint use annotation @Query for repository method</li>
   * <li>Expected result is success so it will return HTTP 404 Not Found</li>
   * <li>Expected json body is to check whether content response is expected as we define at test method</li>
   * </ul>
   *
   * @throws Exception
   * @see <a href="https://documenter.getpostman.com/view/25201895/2s8Z73xVro#4311eb97-078e-442f-a2c2-e625d480a2b2">Postman API link</a>
   * @author @fadhylfa / @frhn9
   */
  @Test
  @Order(4)
  void get_jsonplaceholderpost_by_id_when_not_found() throws Exception {
    mockMvc.perform(MockMvcRequestBuilders.get("/666")
            .contentType(MediaType.APPLICATION_JSON))
        .andExpect(MockMvcResultMatchers.status().isNotFound())
        .andExpect(MockMvcResultMatchers.jsonPath("$.response_schema.response_code").value("PMRK-0001"));
  }

  /**
   * This method will test endpoint GET: {url}/{id}
   * <ul>
   * <li>Expected result is data not found so it will return HTTP 404 Not Found</li>
   * <li>Expected json body is to check whether content response is expected as we define at test method</li>
   * </ul>
   *
   * @throws Exception
   * @see <a href="https://documenter.getpostman.com/view/25201895/2s8Z73xVro#4311eb97-078e-442f-a2c2-e625d480a2b2">Postman API link</a>
   * @author @fadhylfa / @frhn9
   */
  @Test
  @Order(5)
  void add_post_when_success() throws Exception {
    CreatePostRequest postReq = new CreatePostRequest();
    postReq.setTitle("test test");
    postReq.setBody("testing@gmail.com");
    postReq.setUserId(777);

    PostResponse postResponse = new PostResponse();
    postResponse.setId(1);
    postResponse.setTitle("test test");
    postResponse.setBody("testing@gmail.com");
    postResponse.setUserId(777);

    Mockito.when(jsonPlaceHolderClient.createPost(postReq)).thenReturn(postResponse);

    mockMvc.perform(MockMvcRequestBuilders.post("/post")
            .contentType(MediaType.APPLICATION_JSON)
        // code below is for request body
            .content("{\n" +
                "    \"title\": \"test test\",\n" +
                "    \"body\": \"testing@gmail.com\",\n" +
                "    \"userId\": \"666\"\n" +
                "}"))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
        // code below expected to check default response
        .andExpect(MockMvcResultMatchers.jsonPath("$.response_schema").exists())
        .andExpect(MockMvcResultMatchers.jsonPath("$.response_schema.response_code").exists())
        .andExpect(MockMvcResultMatchers.jsonPath("$.response_schema.response_message").exists())
        .andExpect(MockMvcResultMatchers.jsonPath("$.response_schema").exists())
        // code below expected to check content response
        .andExpect(MockMvcResultMatchers.jsonPath("$.response_output.detail.title").exists())
        .andExpect(MockMvcResultMatchers.jsonPath("$.response_output.detail.title").value("test test"))
        .andExpect(MockMvcResultMatchers.jsonPath("$.response_output.detail.body").exists())
        .andExpect(MockMvcResultMatchers.jsonPath("$.response_output.detail.body").value("testing@gmail.com"))
        .andExpect(MockMvcResultMatchers.jsonPath("$.response_output.detail.userId").exists())
        .andExpect(MockMvcResultMatchers.jsonPath("$.response_output.detail.userId").value("666"));
  }

  /**
   * This method will test endpoint POST: {url}/post
   * <ul>
   * <li>For request body, you must convert your object to json string first</li>
   * <li>Expected result is failed so it will return HTTP 400 Bad Request</li>
   * <li>Expected json body is to check whether content response is expected as we define at test method</li>
   * </ul>
   *
   * @throws Exception
   * @see <a href="https://documenter.getpostman.com/view/25201895/2s8Z73xVro#4311eb97-078e-442f-a2c2-e625d480a2b2">Postman API link</a>
   * @author @fadhylfa / @frhn9
   */
  @Test
  @Order(6)
  void add_post_when_validation_error() throws Exception {
    mockMvc.perform(MockMvcRequestBuilders.post("/post")
            .contentType(MediaType.APPLICATION_JSON)
            // code below is for request body
            .content("{\n" +
                "    \"title\": \"test test\",\n" +
                "    \"body\": \"testing@gmail.com\",\n" +
                "    \"userId\": \"\"\n" +
                "}"))
        .andExpect(MockMvcResultMatchers.status().isBadRequest())
        .andExpect(MockMvcResultMatchers.content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
        // code below expected to check default response
        .andExpect(MockMvcResultMatchers.jsonPath("$.response_schema").exists())
        .andExpect(MockMvcResultMatchers.jsonPath("$.response_schema.response_code").exists())
        .andExpect(MockMvcResultMatchers.jsonPath("$.response_schema.response_code").value("PMRK-0009"))
        .andExpect(MockMvcResultMatchers.jsonPath("$.response_schema.response_message").exists())
        .andExpect(MockMvcResultMatchers.jsonPath("$.response_schema.response_message").value("invalid parameter"))
        .andExpect(MockMvcResultMatchers.jsonPath("$.response_schema").exists());
  }

}