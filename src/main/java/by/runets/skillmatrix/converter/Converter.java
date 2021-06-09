package by.runets.skillmatrix.converter;

import by.runets.skillmatrix.dto.RequestNewCategoryDTO;
import by.runets.skillmatrix.model.Category;
import org.springframework.stereotype.Component;

/**
 * This is interface provides method which will convert object to another object by both fields.
 * @param <T> - generic param which should be extended from Category or be Category.
 * @param <K> - generic param which will converts to T param.
 */
@Component
public interface Converter <T extends Category, K>{
  T convert(K entity);
}
