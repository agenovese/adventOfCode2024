package ca.genovese;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;



public class Day5 {
    record PageOrderRule(String x, String y) {

    }



    public static long question1(Stream<String> lineStream) {

        List<String> inputLines = lineStream.toList();
        List<PageOrderRule> rules = inputLines.stream()
                .filter(s -> s.contains("|"))
                .map(s -> s.split("\\|"))
                .map(s -> new PageOrderRule(s[0], s[1]))
                .toList();
        List<List<String>> list = inputLines.stream()
                .filter(s -> s.contains(","))
                .map(s -> s.split(","))
                .map(s -> (List<String>) new ArrayList<String>(List.of(s)))
                .toList();

        return list.stream().filter(pj ->
                rules.stream()
                            .allMatch(r -> !pj.contains(r.x) || !pj.contains(r.y) || pj.indexOf(r.x) < pj.indexOf(r.y)))
                .map(pj -> pj.get((pj.size() - 1) / 2))
                .mapToLong(Long::parseLong)
                .sum();
    }

    public static long question2(Stream<String> lineStream) {
        List<String> inputLines = lineStream.toList();
        List<PageOrderRule> rules = inputLines.stream()
                .filter(s -> s.contains("|"))
                .map(s -> s.split("\\|"))
                .map(s -> new PageOrderRule(s[0], s[1]))
                .toList();
        List<List<String>> list = inputLines.stream()
                .filter(s -> s.contains(","))
                .map(s -> s.split(","))
                .map(s -> (List<String>) new ArrayList<String>(List.of(s)))
                .toList();

        return list.stream().filter(pj ->
                        rules.stream()
                                .anyMatch(r -> pj.contains(r.x) && pj.contains(r.y) && pj.indexOf(r.x) > pj.indexOf(r.y)))
                .map(pj -> sortList(rules, pj) )
                .map(pj -> pj.get((pj.size() - 1) / 2))
                .mapToLong(Long::parseLong)
                .sum();
    }

    private static List<String> sortList(List<PageOrderRule> rules, List<String> input) {
        if(input.isEmpty()) {
            return List.of();
        } else {
            List<String> intermediate = new ArrayList<>();
            input.stream().filter(s -> rules.stream().noneMatch(r -> input.contains(r.x) && r.y.equals(s))).forEach(intermediate::add);
            List<PageOrderRule> newRules = rules.stream().filter(r -> !intermediate.contains(r.x)).toList();
            intermediate.addAll(sortList(newRules, input.stream().filter(s -> !intermediate.contains(s)).toList()));
            return intermediate;
        }
    }
}
