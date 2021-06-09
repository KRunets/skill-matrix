package by.runets.skillmatrix.controller;

import by.runets.skillmatrix.converter.Converter;
import by.runets.skillmatrix.dto.RequestEditCategoryDTO;
import by.runets.skillmatrix.dto.RequestNewCategoryDTO;
import by.runets.skillmatrix.dto.ResponseCategoryDTO;
import by.runets.skillmatrix.exception.ServiceException;
import by.runets.skillmatrix.model.Category;
import by.runets.skillmatrix.service.CategoryService;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * This is a rest controller which provides communication between client and server.
 */
@RestController
@RequiredArgsConstructor
@Log4j2
public class Controller {
  private final CategoryService<Category> categoryService;
  private final ModelMapper modelMapper;
  private final Converter<Category, RequestNewCategoryDTO> converter;
  
  /**
   * This is a method which will calls when client makes GET request by uri /api/category.
   * @return tree of categories from excel file or HTTP NOT FOUND value.
   */
  @GetMapping("/api/category")
  public ResponseEntity<ResponseCategoryDTO> loadCategory() {
    try {
      Category category = categoryService.read();
      ResponseCategoryDTO responseCategoryDTO = modelMapper.map(category, ResponseCategoryDTO.class);
      return ResponseEntity.ok(responseCategoryDTO);
    } catch (ServiceException e) {
      log.error(e);
      return ResponseEntity.notFound().build();
    }
  }
  
  /**
   * This is a method which will calls when client makes DELETE request by uri /api/category/delete and wants to delete category from tree of categories in excel file.
   * @param requestNewCategoryDTO - object contains data of selected category in client side.
   * @return tree of categories from excel file or HTTP NOT FOUND value.
   */
  @DeleteMapping("/api/category/delete")
  public ResponseEntity<?> deleteCategory(@Valid @RequestBody RequestNewCategoryDTO requestNewCategoryDTO) {
    try {
      Category category = modelMapper.map(requestNewCategoryDTO, Category.class);
      ResponseCategoryDTO responseCategoryDTO = modelMapper.map(categoryService.delete(category), ResponseCategoryDTO.class);
      return ResponseEntity.ok(responseCategoryDTO);
    } catch (ServiceException e) {
      log.error(e);
      return ResponseEntity.notFound().build();
    }
  }
  
  /**
   * This is a method which will calls when client makes POST request by uri /api/category/add" and wants to add new category in tree of categories.
   * @param requestNewCategoryDTO - object contains data of new category from client side.
   * @return tree of categories from excel file or HTTP NOT FOUND value.
   */
  @PostMapping("/api/category/add")
  public ResponseEntity<?> addCategory(@Valid @RequestBody RequestNewCategoryDTO requestNewCategoryDTO) {
    try {
      Category category = converter.convert(requestNewCategoryDTO);
      ResponseCategoryDTO responseCategoryDTO = modelMapper.map(categoryService.create(category), ResponseCategoryDTO.class);
      return ResponseEntity.ok(responseCategoryDTO);
    } catch (ServiceException e) {
      log.error(e);
      return ResponseEntity.notFound().build();
    }
  }
  
  /**
   * This is a method which will calls when client makes PUT request by uri /api/category/edit and wants to edit selected category.
   * @param requestNewCategoryDTO - object contains data of edited category.
   * @return tree of categories from excel file or HTTP NOT FOUND value.
   */
  @PutMapping("/api/category/edit")
  public ResponseEntity<ResponseCategoryDTO> putCategory(@Valid @RequestBody RequestEditCategoryDTO requestNewCategoryDTO) {
    try {
      Category category = categoryService.edit(requestNewCategoryDTO.getOldName(), requestNewCategoryDTO.getNewName());
      return ResponseEntity.ok(modelMapper.map(category, ResponseCategoryDTO.class));
    } catch (ServiceException e) {
      log.error(e);
      return ResponseEntity.notFound().build();    }
  }
}
