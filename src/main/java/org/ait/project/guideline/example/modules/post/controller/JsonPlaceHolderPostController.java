package org.ait.project.guideline.example.modules.post.controller;

import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.ait.project.guideline.example.modules.post.dto.request.PostReq;
import org.ait.project.guideline.example.modules.post.dto.response.JsonPlaceHolderPostResponse;
import org.ait.project.guideline.example.modules.post.service.internal.JsonPlaceHolderPostService;
import org.ait.project.guideline.example.shared.dto.template.ResponseDetail;
import org.ait.project.guideline.example.shared.dto.template.ResponseList;
import org.ait.project.guideline.example.shared.dto.template.ResponseTemplate;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class JsonPlaceHolderPostController implements JsonPlaceHolderPostService {

  private final JsonPlaceHolderPostService postService;

  @Override
  @GetMapping("/post")
  public ResponseEntity<ResponseTemplate<ResponseList<JsonPlaceHolderPostResponse>>> getAllJsonPlaceHolderPost() {
    return postService.getAllJsonPlaceHolderPost();
  }

  @Override
  @GetMapping("/post-page")
  public ResponseEntity<ResponseTemplate<ResponseList<JsonPlaceHolderPostResponse>>> getAllJsonPlaceHolderPostPage(
      Pageable pageable) {
    return postService.getAllJsonPlaceHolderPostPage(pageable);
  }

  @Override
  @GetMapping("/{id}")
  public ResponseEntity<ResponseTemplate<ResponseDetail<JsonPlaceHolderPostResponse>>> getJsonPlaceHolderPostById(
      @PathVariable Integer id) {
    return postService.getJsonPlaceHolderPostById(id);
  }

  @Override
  @PostMapping("/post")
  public ResponseEntity<ResponseTemplate<ResponseDetail<JsonPlaceHolderPostResponse>>> addPost(
      @Valid
      @RequestBody
          PostReq postReq) {
    return postService.addPost(postReq);
  }
}
