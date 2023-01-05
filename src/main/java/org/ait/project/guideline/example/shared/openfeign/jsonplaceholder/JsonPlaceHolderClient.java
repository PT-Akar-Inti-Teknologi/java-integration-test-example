package org.ait.project.guideline.example.shared.openfeign.jsonplaceholder;

import java.util.List;
import org.ait.project.guideline.example.shared.openfeign.jsonplaceholder.request.CreatePostRequest;
import org.ait.project.guideline.example.shared.openfeign.jsonplaceholder.response.PostResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(
    value = "jsonplaceholder-client",
    url = "${restclient.url.jsonplaceholder-client}",
    fallback = JsonPlaceHolderClientFallback.class
)
public interface JsonPlaceHolderClient {

  @GetMapping("/posts")
  List<PostResponse> getListPost();

  @GetMapping("/posts/{id}")
  PostResponse getPostById(@PathVariable String id);

  @PostMapping("/posts")
  PostResponse createPost(@RequestBody CreatePostRequest createPostRequest);
}
