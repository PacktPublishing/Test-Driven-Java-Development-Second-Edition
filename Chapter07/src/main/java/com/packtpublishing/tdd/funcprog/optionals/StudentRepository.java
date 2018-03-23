package com.packtpublishing.tdd.funcprog.optionals;

import java.util.*;

public class StudentRepository {
  private final Set<Student> studentSet;

  StudentRepository(Collection<Student> students) {
    studentSet = new HashSet<>(students);
  }

  public Optional<Student> findByName(String name) {
    for (Student student : this.studentSet) {
      if (student.name.equals(name))
        return Optional.of(student);
    }
    return Optional.empty();
  }
}
