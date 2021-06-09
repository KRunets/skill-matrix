package by.runets.skillmatrix.api.impl;

import by.runets.skillmatrix.exception.ResourceNotFoundException;
import by.runets.skillmatrix.model.Category;
import by.runets.skillmatrix.api.ExcelRepository;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Component;

@Component
public class ExcelRepositoryImpl implements ExcelRepository<Category> {
  
  private final static int INDENT = 0;
  private final static int ROW_NUM = 0;
  
  /**
   * This is a method for writing tree structure in file.
   * @param category - object of category tree structure
   * @param fileName - String param.
   * @param sheetName - String param.
   * @throws ResourceNotFoundException - thrown if access to data file is prohibited
   */
  @Override
  public void write(Category category, String fileName, String sheetName) throws ResourceNotFoundException {
    try (FileOutputStream outputStream = new FileOutputStream(fileName)) {
      XSSFWorkbook workbook = new XSSFWorkbook();
      XSSFSheet sheet = workbook.createSheet(sheetName);
      
      writeTreeStructure(INDENT, ROW_NUM, category, sheet);
      
      workbook.write(outputStream);
    } catch (IOException e) {
      throw new ResourceNotFoundException("Write data to excel file exception", e);
    }
  }
  
  /**
   * This is a method for reading tree structure from file.
   * @param fileName - String param.
   * @param sheetName - String param.
   * @return - Category tree structure.
   * @throws ResourceNotFoundException - thrown if access to data file is prohibited
   */
  @Override
  public Category read(String fileName, String sheetName) throws ResourceNotFoundException {
    try (InputStream inputStream = new FileInputStream(fileName)) {
      XSSFWorkbook excelBook = new XSSFWorkbook(inputStream);
      XSSFSheet excelSheet = excelBook.getSheet(sheetName);
      
      List<Category> categories = new ArrayList<>();
      Category root = null;
      
      for (Row row : excelSheet) {
        for (Cell cell : row) {
          if (cell.getCellType() == CellType.STRING) {
            int current = cell.getColumnIndex();
            if (current == 0 && root == null) {
              root = new Category(current, cell.getStringCellValue(), new ArrayList<>(), new Category());
            } else if (root != null) {
              if (current > root.getColumn()) {
                Category node = new Category(current, cell.getStringCellValue(), new ArrayList<>(), root);
                root.addNode(node);
                
                root = node;
              } else if (current == root.getColumn()) {
                Category node = new Category(current, cell.getStringCellValue(), new ArrayList<>(), root.getParent());
                
                root.getParent().addNode(node);
                root = node;
              } else {
                while (current < root.getColumn()) {
                  root = root.getParent();
                }
                
                Category node = new Category(current, cell.getStringCellValue(), new ArrayList<>(), root.getParent());
                root.getParent().addNode(node);
                
                root = node;
              }
            }
          }
        }
        categories.add(root);
      }
  
      return categories.get(0);
    } catch (IOException e) {
      throw new ResourceNotFoundException("Read data from excel file exception", e);
    }
  }
  
  private void writeTreeStructure(int indent, int rowNum, Category tree, XSSFSheet sheet) {
    if (sheet.getRow(rowNum) != null) {
      writeTreeStructure(indent, rowNum + 1, tree, sheet);
    } else {
      Row row = sheet.createRow(rowNum);
      Cell cell = row.createCell(indent);
      cell.setCellValue(tree.getText());
      if (tree.getNodes() != null) {
        for (Category child : tree.getNodes()) {
          writeTreeStructure(indent + 1, rowNum + 1, child, sheet);
        }
      }
    }
  }
}
    
