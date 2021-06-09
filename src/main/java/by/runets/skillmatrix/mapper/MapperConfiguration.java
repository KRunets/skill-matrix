package by.runets.skillmatrix.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * This is class will registered in spring container as a configuration class.
 */
@Configuration
public class MapperConfiguration {
  @Bean
  public ModelMapper modelMapper() {
    return new ModelMapper();
  }
}
