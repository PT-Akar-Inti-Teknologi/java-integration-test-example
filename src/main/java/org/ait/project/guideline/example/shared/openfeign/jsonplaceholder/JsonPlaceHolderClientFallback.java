package org.ait.project.guideline.example.shared.openfeign.jsonplaceholder;

import java.util.List;
import org.ait.project.guideline.example.shared.openfeign.jsonplaceholder.request.CreatePostRequest;
import org.ait.project.guideline.example.shared.openfeign.jsonplaceholder.response.PostResponse;
import org.springframework.stereotype.Component;

@Component
public class JsonPlaceHolderClientFallback implements JsonPlaceHolderClient{

  /**
   * When Openfeign Call failed, then do this
   * */

  @Override
  public List<PostResponse> getListPost() {
    return null;
  }

  @Override
  public PostResponse getPostById(String id) {
    return null;
  }

  @Override
  public PostResponse createPost(CreatePostRequest createPostRequest) {
    return null;
  }
}
