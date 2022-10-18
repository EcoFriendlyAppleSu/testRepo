package org.healthplus;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ValueAnnotationTest {

  @Value("${movie.catalog}")
  private String catalog;

  @Value("${movie.movieList}")
  private List<String> movieList;

  @Test
  public void getPropertiesValueTest() throws Exception {
    //given
    String name = "romance";
    assertThat(name).isEqualTo(catalog);
  }

  @Test
  public void getPropertiesListTest() throws Exception {
    System.out.println(movieList);
    assertThat(movieList.size()).isEqualTo(3);
  }

}
