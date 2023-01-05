package org.ait.project.guideline.example.modules.post.service.internal;

import org.ait.project.guideline.example.modules.post.dto.request.PostReq;
import org.ait.project.guideline.example.modules.post.dto.response.JsonPlaceHolderPostResponse;
import org.ait.project.guideline.example.shared.dto.template.ResponseDetail;
import org.ait.project.guideline.example.shared.dto.template.ResponseList;
import org.ait.project.guideline.example.shared.dto.template.ResponseTemplate;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface JsonPlaceHolderPostService {

  /**
   * Function used to get All Json Place Holder Post
   * @return ResponseEntity which contains a list of JsonPlaceHolderPost Responses
   */
  ResponseEntity<ResponseTemplate<ResponseList<JsonPlaceHolderPostResponse>>>getAllJsonPlaceHolderPost();

  /**
   * Function used to add Post
   * @param postReq is the body of the request that represents the data to be added
   * @return ResponseEntity which contains a response
   */
  ResponseEntity<ResponseTemplate<ResponseDetail<JsonPlaceHolderPostResponse>>> addPost(PostReq postReq);

  ResponseEntity<ResponseTemplate<ResponseList<JsonPlaceHolderPostResponse>>> getAllJsonPlaceHolderPostPage(
      Pageable pageable);

  ResponseEntity<ResponseTemplate<ResponseDetail<JsonPlaceHolderPostResponse>>> getJsonPlaceHolderPostById(
      Integer id);


}
