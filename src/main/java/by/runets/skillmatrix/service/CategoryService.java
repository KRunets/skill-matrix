package by.runets.skillmatrix.service;

import by.runets.skillmatrix.exception.ServiceException;
import by.runets.skillmatrix.model.Category;
import org.springframework.stereotype.Service;

/**
 * This is a service layer interface with CRUD methods.
 * @param <T> - generic param which should be extended from Category or be Category.
 */
@Service
public interface CategoryService<T extends Category> {
  T create(T entity) throws ServiceException;
  T edit(String oldName, String newName) throws ServiceException;
  T delete(T entity) throws ServiceException;
  T read() throws ServiceException;
}
