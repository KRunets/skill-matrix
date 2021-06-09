package by.runets.skillmatrix.dto;

import javax.validation.constraints.Pattern;
import lombok.Data;

/**
 * Object of this class will be created when client makes PUT request with data of edited category.
 */
@Data
public class RequestEditCategoryDTO {
  @Pattern(regexp = "[^0-9.]+")
  private String oldName;
  @Pattern(regexp = "[^0-9.]+")
  private String newName;
}
