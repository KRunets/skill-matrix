package by.runets.skillmatrix.model;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Category {
  @NonNull
  private int column;
  @NonNull
  private String text;
  @NonNull
  private List<Category> nodes;
  @NonNull
  private Category parent;
  
  public boolean addNode(Category category) {
    return nodes.add(category);
  }
  
  @Override
  public String toString() {
    String parent = this.parent == null ? "" : this.parent.getText();
    return "Category \n{"  + "\n" + "\tlevel: " + column + "\n" + "\ttext: " + text + "\n" + "\tsubcategories: ["
        + nodes + "]" + "\n" + "\tparent: " + parent + "\n}";
  }
}
