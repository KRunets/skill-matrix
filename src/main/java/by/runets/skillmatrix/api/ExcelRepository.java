package by.runets.skillmatrix.api;

import by.runets.skillmatrix.exception.ResourceNotFoundException;
import org.springframework.stereotype.Component;

/**
 * This is a interface for read or write data in tree structure in excel file.
 * @param <T> - any param for tree structure.
 */
@Component
public interface ExcelRepository<T> {
  void write(T data, String fileName, String sheetName) throws ResourceNotFoundException;
  T read(String fileName, String sheetName) throws ResourceNotFoundException;
}
