package org.ait.project.guideline.example.modules.post.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.Data;

@Data
public class PostReq {

  @NotBlank(message = "{title.required}")
  @JsonProperty("title")
  private String title;

  @NotBlank(message = "{body.required}")
  @JsonProperty("body")
  private String body;

  @NotNull(message = "{userId.required}")
  @JsonProperty("userId")
  private Integer userId;

}
