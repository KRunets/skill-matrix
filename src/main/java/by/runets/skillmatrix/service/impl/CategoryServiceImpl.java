package by.runets.skillmatrix.service.impl;

import by.runets.skillmatrix.exception.ResourceNotFoundException;
import by.runets.skillmatrix.exception.ServiceException;
import by.runets.skillmatrix.model.Category;
import by.runets.skillmatrix.service.CategoryService;
import by.runets.skillmatrix.api.ExcelRepository;
import by.runets.skillmatrix.api.TreeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Log4j2
public class CategoryServiceImpl implements CategoryService {
  
  private final static String FILE_NAME = "file/SkillMatrix.xlsx";
  private final static String SHEET_NAME = "Skill_Matrix";
  
  private final ExcelRepository<Category> excelRepository;
  private final TreeRepository<Category> treeRepository;
  
  @Override
  public Category create(Category category) throws ServiceException {
    try {
      Category root = excelRepository.read(FILE_NAME, SHEET_NAME);
      if (treeRepository.insert(category, root)) {
        excelRepository.write(root, FILE_NAME, SHEET_NAME);
      }
      
      return root;
    } catch (ResourceNotFoundException e) {
      throw new ServiceException("Read category service exception", e);
    }
  }
  
  @Override
  public Category edit(String oldName, String newName) throws ServiceException {
    try {
      Category root = excelRepository.read(FILE_NAME, SHEET_NAME);
      if (treeRepository.edit(oldName, newName, root)) {
        excelRepository.write(root, FILE_NAME, SHEET_NAME);
      }
      
      return root;
    } catch (ResourceNotFoundException e) {
      throw new ServiceException("Read category service exception", e);
    }
  }
  
  @Override
  public Category delete(Category category) throws ServiceException {
    try {
      Category root = excelRepository.read(FILE_NAME, SHEET_NAME);
      if (treeRepository.delete(category, root)) {
        excelRepository.write(root, FILE_NAME, SHEET_NAME);
      }
      
      return root;
    } catch (ResourceNotFoundException e) {
      throw new ServiceException("Read category service exception", e);
    }
  }
  
  @Override
  public Category read() throws ServiceException {
    try {
      return excelRepository.read(FILE_NAME, SHEET_NAME);
    } catch (ResourceNotFoundException e) {
      throw new ServiceException("Read category service exception", e);
    }
  }
}
