package by.runets.skillmatrix.api;

import by.runets.skillmatrix.exception.ResourceNotFoundException;
import by.runets.skillmatrix.model.Category;
import by.runets.skillmatrix.api.impl.ExcelRepositoryImpl;
import by.runets.skillmatrix.api.impl.TreeRepositoryImpl;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TreeRepositoryTest {
  private final static String FILE_NAME = "file/SkillMatrix.xlsx";
  private final static String SHEET_NAME = "Skill_Matrix";
  
  private final static String TEST_NAME = "file/test.xlsx";
  private final static String TEST_SHEET = "test";
  
  private TreeRepository<Category> treeRepository = new TreeRepositoryImpl();
  private ExcelRepository<Category> excelRepository = new ExcelRepositoryImpl();
  
  @Test
  public void testInsert() throws ResourceNotFoundException {
    Category category = new Category();
    Category parent = new Category();
    parent.setText("Hard skills");
    category.setParent(parent);
    category.setText("new Node");
  
    Category root = excelRepository.read(FILE_NAME, SHEET_NAME);
    
    Assert.assertTrue(treeRepository.insert(category, root));
  }
}
