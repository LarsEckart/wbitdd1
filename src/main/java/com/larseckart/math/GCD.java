package com.larseckart.math;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

class GCD {

  public static int from(int first, int second) {
    return max(commonDivisors(divisors(first), divisors(second)));
  }

  static int max(Set<Integer> commonDivisors) {
    return commonDivisors.stream().max(Integer::compareTo).orElseThrow();
  }

  static Set<Integer> commonDivisors(Collection<Integer> divisors, Collection<Integer> divisors1) {
    return divisors.stream().distinct().filter(divisors1::contains).collect(Collectors.toSet());
  }

  static Set<Integer> divisors(int number) {
    Set<Integer> result = new HashSet<>();
    result.add(number);
    result.add(1);
    for (int i = number - 1; i > 0; i--) {
      if (number % i == 0) {
        result.add(i);
      }
    }
    return result;
  }
}
