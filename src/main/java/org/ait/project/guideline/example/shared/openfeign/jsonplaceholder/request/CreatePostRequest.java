package org.ait.project.guideline.example.shared.openfeign.jsonplaceholder.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class CreatePostRequest {
  @JsonProperty("title")
  private String title;

  @JsonProperty("body")
  private String body;

  @JsonProperty("userId")
  private int userId;
}
