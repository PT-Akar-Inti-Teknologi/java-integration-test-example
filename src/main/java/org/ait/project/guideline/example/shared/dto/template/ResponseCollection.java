package org.ait.project.guideline.example.shared.dto.template;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseCollection<T>{

	@JsonProperty("pagination")
	private PaginationConfig paginationConfig;

	@JsonProperty("content")
	private List<T> content;
}