package com.packtpublishing.tdd.funcprog.optionals;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class StudentRepositoryTest {

  private List<Student> studentList = Arrays.asList(
      new Student("Jane", 23),
      new Student("John", 21),
      new Student("Tom", 25)
  );

  private StudentRepository studentRepository =
      new StudentRepository(studentList);

  @Test
  public void whenStudentIsNotFoundThenReturnEmpty() {
    assertThat(studentRepository.findByName("Samantha"))
        .isNotPresent();
  }

  @Test
  public void whenStudentIsFoundThenReturnStudent() {
    assertThat(studentRepository.findByName("John"))
        .isPresent() // redundant
        .hasValueSatisfying(s -> {
          assertThat(s.name).isEqualTo("John");
          assertThat(s.age).isEqualTo(21);
        });
  }
}
