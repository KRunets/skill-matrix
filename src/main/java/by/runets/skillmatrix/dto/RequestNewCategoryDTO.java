package by.runets.skillmatrix.dto;

import javax.validation.constraints.Pattern;
import lombok.Data;

/**
 * Object of this class will be created when client makes POST or DELETE request with data of new or delete category.
 */
@Data
public class RequestNewCategoryDTO {
  @Pattern(regexp = "[^0-9.]+")
  private String text;
  private String parent;
}
