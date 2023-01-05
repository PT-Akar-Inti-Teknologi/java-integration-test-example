package org.ait.project.guideline.example.modules.post.transform;

import java.util.List;
import org.ait.project.guideline.example.modules.post.dto.request.PostReq;
import org.ait.project.guideline.example.modules.post.dto.response.JsonPlaceHolderPostResponse;
import org.ait.project.guideline.example.modules.post.model.entity.JsonPlaceHolderPost;
import org.ait.project.guideline.example.shared.openfeign.jsonplaceholder.request.CreatePostRequest;
import org.ait.project.guideline.example.shared.openfeign.jsonplaceholder.response.PostResponse;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Named;

@Mapper(componentModel = "spring") // for Dependency Injection Spring
public interface JsonPlaceHolderPostTransform {

  @Named("createJPHPostResponse")
  JsonPlaceHolderPostResponse createJPHPostResponse(JsonPlaceHolderPost jsonPlaceHolderPost);

  @IterableMapping(qualifiedByName = "createJPHPostResponse")
  List<JsonPlaceHolderPostResponse> createJPHPostResponseList(
      List<JsonPlaceHolderPost> jsonPlaceHolderPostList);

  @Named("createJPHPost")
  JsonPlaceHolderPost createJPHPost(PostResponse postResponse);

  @IterableMapping(qualifiedByName = "createJPHPost")
  List<JsonPlaceHolderPost> createJPHPostList(List<PostResponse> postResponseList);

  CreatePostRequest createJPHPostRequest(PostReq postReq);

  JsonPlaceHolderPost createEntityPost(PostReq postReq);
}
