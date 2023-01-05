package org.ait.project.guideline.example.modules.post.service.delegate.impl;

import java.time.ZonedDateTime;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.ait.project.guideline.example.modules.post.exception.JPHNotFoundException;
import org.ait.project.guideline.example.modules.post.model.entity.JsonPlaceHolderPost;
import org.ait.project.guideline.example.modules.post.model.repository.JsonPlaceHolderPostRepository;
import org.ait.project.guideline.example.modules.post.service.delegate.JsonPlaceHolderPostDelegate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class JsonPlaceHolderPostDelegateImpl implements JsonPlaceHolderPostDelegate {

  private final JsonPlaceHolderPostRepository repository;

  @Override
  public List<JsonPlaceHolderPost> getAllPost() {
    return repository.findAll();
  }

  @Override
  public Page<JsonPlaceHolderPost> getAllPostPage(Pageable pageable) {
    return repository.findAll(pageable);
  }

  @Override
  public JsonPlaceHolderPost getPostById(Integer id) {
    return repository.findById(id).orElseThrow(JPHNotFoundException::new);
  }

  @Override
  public List<JsonPlaceHolderPost> saveAll(List<JsonPlaceHolderPost> jsonPlaceHolderPostList) {
    return repository.saveAll(jsonPlaceHolderPostList);
  }

  @Override
  public JsonPlaceHolderPost save(JsonPlaceHolderPost jsonPlaceHolderPost) {
    return repository.save(jsonPlaceHolderPost);
  }
}
