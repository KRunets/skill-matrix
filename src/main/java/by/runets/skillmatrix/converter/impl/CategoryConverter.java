package by.runets.skillmatrix.converter.impl;

import by.runets.skillmatrix.converter.Converter;
import by.runets.skillmatrix.dto.RequestNewCategoryDTO;
import by.runets.skillmatrix.model.Category;
import org.springframework.stereotype.Component;

/**
 * This is a class which implements Converter interface with two generic param.
 * First param is Category.
 * Second param is RequestNewCategoryDTO
 */
@Component
public class CategoryConverter implements Converter<Category, RequestNewCategoryDTO> {
  
  /**
   * This is a method which converts RequestNewCategoryDTO object to Category object.
   * @param requestNewCategoryDTO contains String data of text and parent.
   * @return Category object with text and parent data.
   */
  @Override
  public Category convert(RequestNewCategoryDTO requestNewCategoryDTO) {
    Category category = new Category();
    Category parent = new Category();
  
    category.setText(requestNewCategoryDTO.getText());
    parent.setText(requestNewCategoryDTO.getParent());
    category.setParent(parent);
  
    return category;
  }
}
