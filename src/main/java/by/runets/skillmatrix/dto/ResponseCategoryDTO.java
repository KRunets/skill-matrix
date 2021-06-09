package by.runets.skillmatrix.dto;

import java.util.List;
import javax.validation.constraints.Pattern;
import lombok.Data;

/**
 * Object of this class will be created when server needs to return tree of categories to the client side.
 */
@Data
public class ResponseCategoryDTO {
  @Pattern(regexp = "[^0-9.]+|.")
  private String text;
  private List<ResponseCategoryDTO> nodes;
}
