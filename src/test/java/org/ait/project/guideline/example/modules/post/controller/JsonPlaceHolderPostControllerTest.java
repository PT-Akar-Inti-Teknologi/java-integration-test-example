package org.ait.project.guideline.example.modules.post.controller;

import org.ait.project.guideline.example.modules.post.model.entity.JsonPlaceHolderPost;
import org.ait.project.guideline.example.modules.post.model.repository.JsonPlaceHolderPostRepository;
import org.ait.project.guideline.example.modules.post.service.delegate.JsonPlaceHolderPostDelegate;
import org.ait.project.guideline.example.modules.post.service.delegate.impl.JsonPlaceHolderPostDelegateImpl;
import org.ait.project.guideline.example.shared.openfeign.jsonplaceholder.JsonPlaceHolderClient;
import org.ait.project.guideline.example.shared.openfeign.jsonplaceholder.request.CreatePostRequest;
import org.ait.project.guideline.example.shared.openfeign.jsonplaceholder.response.PostResponse;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
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

import java.util.Collections;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;

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

  /**
   * This method will test endpoint GET: {url}/post
   * <br>Expected result is the endpoint will returning field response_schema, response_code, and response_message
   *
   * @throws Exception
   * @see <a href="https://documenter.getpostman.com/view/25201895/2s8Z73xVro#ef29d6fd-c193-4935-88ee-61a2077f782a">Postman API link</a>
   * @author @fadhylfa / @frhn9
   */
  @Test
  void getAllJsonPlaceHolderPost() throws Exception {
    PostResponse postResponse = new PostResponse();
    postResponse.setId(1);
    postResponse.setTitle("title");
    postResponse.setBody("body");
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
  void getAllJsonPlaceHolderPostPage() {
  }

  @Test
  void getJsonPlaceHolderPostById() {
  }

  /**
   * This method will test endpoint POST: {url}/post
   * <ul>
   * <li>For request body, you must convert your object to json string first</li>
   * <li>Expected result is to check whether default response content response is expected as we define at test method</li>
   * </ul>
   *
   * @throws Exception
   * @see <a href="https://documenter.getpostman.com/view/25201895/2s8Z73xVro#4311eb97-078e-442f-a2c2-e625d480a2b2">Postman API link</a>
   * @author @fadhylfa / @frhn9
   */
  @Test
  void addPost() throws Exception {
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
        .andExpect(
            MockMvcResultMatchers.content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
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

}