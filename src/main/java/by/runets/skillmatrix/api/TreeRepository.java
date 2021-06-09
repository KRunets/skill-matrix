package by.runets.skillmatrix.api;

import org.springframework.stereotype.Component;

/**
 * This is a interface which provides methods for delete, insert or edit tree.
 * @param <T> - any param for tree structure.
 */
@Component
public interface TreeRepository <T> {
  boolean delete(T node, T root);
  boolean insert(T node, T root);
  boolean edit(String oldName, String newName, T root);
}
