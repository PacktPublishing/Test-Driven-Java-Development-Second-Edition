package com.packtpublishing.tdd.funcprog.streams;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

public class StreamsTest {

  @Test
  public void filterByNameReturnsCollectionFiltered() {
    List<String> names = Arrays.asList("Alex", "Paul", "Viktor",
        "Kobe", "Tom", "Andrea");
    List<String> filteredNames = names.stream()
        .filter(name -> name.startsWith("A"))
        .collect(Collectors.toList());

    assertThat(filteredNames)
        .hasSize(2)
        .containsExactly("Alex", "Andrea");
  }

  @Test
  public void mapToUppercaseTransformsAllElementsToUppercase() {
    List<String> names = Arrays.asList("Alex", "Paul", "Viktor");
    List<String> namesUppercase = names.stream()
        .map(String::toUpperCase)
        .collect(Collectors.toList());

    assertThat(namesUppercase)
        .hasSize(3)
        .containsExactly("ALEX", "PAUL", "VIKTOR");
  }

  @Test
  public void gettingLettersUsedInNames() {
    List<String> names = Arrays.asList("Alex", "Paul", "Viktor");
    List<String> lettersUsed = names.stream()
        .map(String::toLowerCase)
        .flatMap(name -> Stream.of(name.split("")))
        .distinct()
        .collect(Collectors.toList());

    assertThat(lettersUsed)
        .hasSize(12)
        .containsExactly("a", "l", "e", "x", "p", "u", "v", "i", "k", "t", "o", "r");
  }

  @Test
  public void countingLettersUsedInNames() {
    List<String> names = Arrays.asList("Alex", "Paul", "Viktor");
    long count = names.stream()
        .map(String::toLowerCase)
        .flatMap(name -> Stream.of(name.split("")))
        .distinct()
        .mapToLong(l -> 1L)
        .reduce(0L, (v1, v2) -> v1 + v2);

    assertThat(count).isEqualTo(12);
  }
}
