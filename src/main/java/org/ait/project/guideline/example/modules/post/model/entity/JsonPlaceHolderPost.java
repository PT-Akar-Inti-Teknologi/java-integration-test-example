package org.ait.project.guideline.example.modules.post.model.entity;

import java.time.ZonedDateTime;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.CreatedDate;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class JsonPlaceHolderPost {

  @Id
  @GeneratedValue
  private Integer id;

  private String title;

  private String body;

  private int userId;

  @CreationTimestamp
  private ZonedDateTime createAt;
}
