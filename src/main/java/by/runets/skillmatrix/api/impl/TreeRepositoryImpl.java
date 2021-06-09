package by.runets.skillmatrix.api.impl;

import by.runets.skillmatrix.model.Category;
import by.runets.skillmatrix.api.TreeRepository;
import org.springframework.stereotype.Component;

@Component
public class TreeRepositoryImpl implements TreeRepository<Category> {
  @Override
  public boolean delete(Category node, Category root) {
    if (root.getText().equalsIgnoreCase(node.getText())) {
      return root.getParent().getNodes().removeIf(n -> n.getText().equalsIgnoreCase(node.getText()));
    } else {
      for (Category child : root.getNodes()) {
        if (delete(node, child)) {
          return true;
        }
      }
    }
    return false;
  }
  
  @Override
  public boolean insert(Category node, Category root) {
    if (root.getText().equalsIgnoreCase(node.getParent().getText())) {
      return root.addNode(node);
    } else {
      for (Category child : root.getNodes()) {
        if (insert(node, child)) {
          return true;
        }
      }
    }
    return false;
  }
  
  @Override
  public boolean edit(String oldName, String newName, Category root) {
    if (root.getText().equalsIgnoreCase(oldName)) {
      root.setText(newName);
      return true;
    } else {
      for (Category child : root.getNodes()) {
        if (edit(oldName, newName, child)) {
          return true;
        }
      }
    }
    return false;
  }
}
