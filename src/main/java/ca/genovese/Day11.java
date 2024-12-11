package ca.genovese;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Stream;

public class Day11 {
    record Pair(long first, long second) {
    }

    static AtomicLong count = new AtomicLong(0);
    private static Map<Pair, Long> resultCache = new HashMap<>();

    public static long question1(Stream<String> lineStream) {
        Stream<Long> inputValues = new ArrayList<>(Arrays.asList(lineStream.toList().getFirst().split(" ")))
                .stream().map(Long::parseLong);

        return inputValues.mapToLong(l -> applyRuleNTimesAndCache(l, 24)).sum();

    }

    private static Stream<Long> applyRule(Long i) {
        if (i == 0) {
            return Stream.of(1L);
        } else if (Long.toString(i).length() % 2 == 0) {
            String longString1 = Long.toString(i).substring(0, Long.toString(i).length() / 2);
            String longString2 = Long.toString(i).substring((Long.toString(i).length() / 2));

            return Stream.of(Long.parseLong(longString1), Long.parseLong(longString2));
        } else {
            return Stream.of(i * 2024L);
        }
    }

    private static Long applyRuleNTimesAndCache(Long input, int depth) {
        if (resultCache.containsKey(new Pair(input, depth))) {
            return resultCache.get(new Pair(input, depth));
        } else {
            List<Long> nextInputs = applyRule(input).toList();
            long result = 0;
            if (depth > 0) {
                for (Long nextInput : nextInputs) {
                    result += applyRuleNTimesAndCache(nextInput, depth - 1);
                }
            } else {
                result = nextInputs.size();
            }
            resultCache.put(new Pair(input, depth), result);
            return result;
        }
    }

    public static long question2(Stream<String> lineStream) {
        String[] inputs = lineStream.toList().getFirst().split(" ");
        Stream<Long> inputValues = new ArrayList<>(Arrays.asList(inputs))
                .stream().map(Long::parseLong);

        long sum = inputValues.mapToLong(l -> applyRuleNTimesAndCache(l, 74)).sum();
        System.out.println(resultCache.size());
        return sum;
    }


}