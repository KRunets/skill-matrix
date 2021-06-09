package by.runets.skillmatrix.api;

import by.runets.skillmatrix.exception.ResourceNotFoundException;
import by.runets.skillmatrix.model.Category;
import by.runets.skillmatrix.api.impl.ExcelRepositoryImpl;
import org.testng.annotations.Test;

public class ExcelRepositoryTest {
  
  private final static String FILE_NAME = "file/SkillMatrix.xlsx";
  private final static String SHEET_NAME = "Skill_Matrix";
  
  private final static String TEST_NAME = "file/test.xlsx";
  private final static String TEST_SHEET = "test";
  private ExcelRepository<Category> excelRepository = new ExcelRepositoryImpl();
  
  @Test
  public void writeTestMethod() throws ResourceNotFoundException {
    Category category = excelRepository.read(FILE_NAME, SHEET_NAME);
    excelRepository.write(category, TEST_NAME, TEST_SHEET);
  }
}
