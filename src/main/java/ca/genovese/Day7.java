package ca.genovese;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class Day7 {
    record Calculation(long result, List<Long> values) {
        boolean validate() {
            Set<Long> possibleTotals = possibleTotals(values.reversed());
            return possibleTotals.contains(result);
        }

        private Set<Long> possibleTotals(List<Long> values) {
            List<Long> localValues = new ArrayList<>(values);
            long head = localValues.removeFirst();
            if (localValues.isEmpty()) {
                return Set.of(head);
            } else {
                Set<Long> subTotals = possibleTotals(localValues);
                return subTotals.stream()
                        .flatMap(i -> Stream.of(i + head, i * head, Long.parseLong(i.toString() + head)))
                        .collect(Collectors.toSet());
            }
        }
    }

    public static long question1(Stream<String> lineStream) {
        return lineStream.map(s -> {
                    long result = Long.parseLong(s.split(":")[0]);
                    List<Long> values = Arrays
                            .stream(s.split(":")[1].split(" "))
                            .filter(s1 -> !s1.isBlank())
                            .map(Long::parseLong)
                            .toList();
                    return new Calculation(result, values);
                })
                .filter(Calculation::validate)
                .mapToLong(c -> c.result)
                .sum();
    }

}