package ca.genovese;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class Day8 {
    record Point(int x, int y) {
        public List<Point> antinodes(Point p) {
            int xDiff = x - p.x;
            int yDiff = y - p.y;

            return List.of(
                    new Point(x + xDiff, y + yDiff),
                    new Point(p.x - xDiff, p.y - yDiff)
            );
        }

        public Set<Point> resonantAntinodes(Point p, int maxX, int maxY) {
            int xDiff = x - p.x;
            int yDiff = y - p.y;

            Set<Point> antinodes = new HashSet<>();

            Point antinode = this;
            while (antinode.x >= 0 && antinode.x < maxX && antinode.y >= 0 && antinode.y < maxY) {
                antinodes.add(antinode);
                antinode = new Point(antinode.x + xDiff, antinode.y + yDiff);
            }

            antinode = p;
            while (antinode.x >= 0 && antinode.x < maxX && antinode.y >= 0 && antinode.y < maxY) {
                antinodes.add(antinode);
                antinode = new Point(antinode.x - xDiff, antinode.y - yDiff);
            }

            return antinodes;
        }
    }

    public static long question1(Stream<String> lineStream) {
        List<List<String>> mapGrid = new ArrayList<>();
        lineStream.forEach(l -> mapGrid.add(new ArrayList<>(l.chars().mapToObj(i -> String.valueOf((char) i)).toList())));
        Map<String, Set<Point>> antennas = new HashMap<>();
        for (int row = 0; row < mapGrid.size(); row++) {
            for (int col = 0; col < mapGrid.get(row).size(); col++) {
                if (!mapGrid.get(row).get(col).equals(".")) {
                    antennas.putIfAbsent(mapGrid.get(row).get(col), new HashSet<>());
                    antennas.get(mapGrid.get(row).get(col)).add(new Point(row, col));
                }
            }
        }

        Set<Point> antinodes = antennas.values().stream().map(
                        points -> points.stream().map(
                                        p -> points.stream()
                                                .filter(p2 -> !p.equals(p2))
                                                .map(p::antinodes)
                                                .flatMap(List::stream)
                                                .filter(antinode -> antinode.x >= 0 && antinode.x < mapGrid.size()
                                                        && antinode.y >= 0 && antinode.y < mapGrid.getFirst().size())
                                                .collect(Collectors.toSet())
                                ).flatMap(Set::stream)
                                .collect(Collectors.toSet()))
                .flatMap(Set::stream)
                .collect(Collectors.toSet());


        return antinodes.size();
    }

    public static long question2(Stream<String> lineStream) {
        List<List<String>> mapGrid = new ArrayList<>();
        lineStream.forEach(l -> mapGrid.add(new ArrayList<>(l.chars().mapToObj(i -> String.valueOf((char) i)).toList())));
        Map<String, Set<Point>> antennas = new HashMap<>();
        for (int row = 0; row < mapGrid.size(); row++) {
            for (int col = 0; col < mapGrid.get(row).size(); col++) {
                if (!mapGrid.get(row).get(col).equals(".")) {
                    antennas.putIfAbsent(mapGrid.get(row).get(col), new HashSet<>());
                    antennas.get(mapGrid.get(row).get(col)).add(new Point(row, col));
                }
            }
        }

        Set<Point> antinodes = antennas.values().stream().flatMap(
                        points -> points.stream().flatMap(
                                        p -> points.stream()
                                                .filter(p2 -> !p.equals(p2))
                                                .map(p2 -> p.resonantAntinodes(p2, mapGrid.size(), mapGrid.getFirst().size()))
                                                .flatMap(Set::stream)
                                )).collect(Collectors.toSet());


        return antinodes.size();
    }


}