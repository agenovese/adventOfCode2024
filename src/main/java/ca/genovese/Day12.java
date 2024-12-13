package ca.genovese;

import java.security.Key;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Day12 {

    public record Point(int x, int y, String val ) {
    }

    public record Edge(int start, int end, int location, boolean isHorizontal) {
        public static Set<Edge> getEdges(Point point, List<List<Point>> mapGrid) {
            Set<Edge> edges = new HashSet<>();
            //Left
            if (point.x == 0 || !mapGrid.get(point.y).get(point.x - 1).val.equals(point.val)) {
                int start = point.y;
                while (start > 0 // not at the top
                      && mapGrid.get(start - 1).get(point.x).val.equals(point.val) // next row up is the same group
                      && (point.x == 0 || !mapGrid.get(start - 1).get(point.x - 1).val.equals(point.val))) { // next row up is still the left boundary
                    start--;
                }
                int end = point.y;
                while (end < mapGrid.size() - 1 // not at the bottom
                       && mapGrid.get(end + 1).get(point.x).val.equals(point.val) // next row down is the same group
                       && (point.x == 0 || !mapGrid.get(end + 1).get(point.x - 1).val.equals(point.val))) { // next row down is still the left boundary
                    end++;
                }
                edges.add(new Edge(start, end, point.x, false));
            }
            //Right
            if (point.x == mapGrid.getFirst().size() - 1 || !mapGrid.get(point.y).get(point.x + 1).val.equals(point.val)) {
                int start = point.y;
                while (start > 0 // not at the top
                       && mapGrid.get(start - 1).get(point.x).val.equals(point.val) // next row up is the same group
                       && (point.x == mapGrid.getFirst().size() - 1 || !mapGrid.get(start - 1).get(point.x + 1).val.equals(point.val))) { // next row up is still the right boundary
                    start--;
                }
                int end = point.y;
                while (end < mapGrid.size() - 1 // not at the bottom
                       && mapGrid.get(end + 1).get(point.x).val.equals(point.val) // next row down is the same group
                       && (point.x == mapGrid.size() - 1 || !mapGrid.get(end + 1).get(point.x + 1).val.equals(point.val))) { // next row down is still the left boundary
                    end++;
                }
                edges.add(new Edge(start, end, point.x + 1, false));
            }
            //Top
            if (point.y == 0 || !mapGrid.get(point.y - 1).get(point.x).val.equals(point.val)) {
                int start = point.x;
                while (start > 0 // not at the left
                       && mapGrid.get(point.y).get(start - 1).val.equals(point.val) // next column left is the same group
                       && (point.y == 0 || !mapGrid.get(point.y - 1).get(start - 1).val.equals(point.val))) { // next column left is still the top boundary
                    start--;
                }
                int end = point.x;
                while (end < mapGrid.get(point.y).size() - 1 // not at the right
                       && mapGrid.get(point.y).get(end + 1).val.equals(point.val) // next column right is the same group
                       && (point.y == 0 || !mapGrid.get(point.y - 1).get(end + 1).val.equals(point.val))) { // next column right is still the top boundary
                    end++;
                }
                edges.add(new Edge(start, end, point.y, true));
            }
            //Bottom
            if (point.y == mapGrid.size() - 1 || !mapGrid.get(point.y + 1).get(point.x).val.equals(point.val)) {
                int start = point.x;
                while (start > 0 // not at the left
                       && mapGrid.get(point.y).get(start - 1).val.equals(point.val) // next column left is the same group
                       && (point.y == mapGrid.size() - 1 || !mapGrid.get(point.y + 1).get(start - 1).val.equals(point.val))) { // next column left is still the bottom boundary
                    start--;
                }
                int end = point.x;
                while (end < mapGrid.get(point.y).size() - 1 // not at the right
                       && mapGrid.get(point.y).get(end + 1).val.equals(point.val) // next column right is the same group
                       && (point.y == mapGrid.size() - 1 || !mapGrid.get(point.y + 1).get(end + 1).val.equals(point.val))) { // next column right is still the bottom boundary
                    end++;
                }
                edges.add(new Edge(start, end, point.y + 1, true));
            }
            return edges;
        }

    }

    public record Region(Set<Point> points) {

//        public Region addPoint(Point point, List<List<Point>> mapGrid) {
//            Map<Point, Integer> newPoints = new HashMap<>();
//            newPoints.putAll(points);
//            newPoints.put(point, 4 - getNeighbors(point, mapGrid).size());
//            return new Region(newPoints);
//        }

    }

    public static long question1(Stream<String> lineStream) {
        List<List<String>> inputGrid = new ArrayList<>();
        lineStream.forEach(l -> inputGrid.add(l.chars().mapToObj(i -> String.valueOf((char) i)).toList()));
        List<List<Point>> mapGrid = new ArrayList<>();
        for (int row = 0; row < inputGrid.size(); row++) {
            mapGrid.add(new ArrayList<>());
            for (int col = 0; col < inputGrid.get(row).size(); col++) {
                mapGrid.get(row).add(new Point(col, row, inputGrid.get(row).get(col)));
            }
        }

        Set<Map<Point, Integer>> regions = new HashSet<>();
        Optional<Point> first = Optional.of(mapGrid.getFirst().getFirst());
        while (first.isPresent()) {
            final Map<Point, Integer> neighbors = findNeighbors(first.get(), mapGrid);
            regions.add(neighbors);
            first = mapGrid.stream()
                .flatMap(List::stream)
                .filter(p -> regions.stream().noneMatch(r -> r.containsKey(p)))
                .findFirst();
        }

        return regions.stream().mapToInt(r ->
            r.values().stream().mapToInt(p -> p).sum() * r.size()
        ).sum();
    }

    private static Map<Point, Integer> findNeighbors(Point initialPoint, List<List<Point>> mapGrid) {
        Map<Point, Integer> visited = new HashMap<>();
        Queue<Point> toVisit = new LinkedList<>();
        toVisit.add(initialPoint);
        while (!toVisit.isEmpty()) {
            Point currentPoint = toVisit.poll();

            List<Point> neighbors = getNeighbors(currentPoint, mapGrid);
            toVisit.addAll(neighbors.stream().filter(p -> !visited.containsKey(p) && !toVisit.contains(p)).toList());
            visited.put(currentPoint, 4 - neighbors.size());
        }
        return visited;
    }

    private static List<Point> getNeighbors(Point currentPoint, List<List<Point>> mapGrid) {
        ArrayList<Point> neighbors = new ArrayList<>();
        if (currentPoint.x > 0) {
            final Point nextPoint = mapGrid.get(currentPoint.y).get(currentPoint.x - 1);
            if (nextPoint.val.equals(currentPoint.val)) {
                neighbors.add(nextPoint);
            }
        }
        if (currentPoint.x < mapGrid.getFirst().size() - 1) {
            final Point nextPoint = mapGrid.get(currentPoint.y).get(currentPoint.x + 1);
            if (nextPoint.val.equals(currentPoint.val)) {
                neighbors.add(nextPoint);
            }
        }
        if (currentPoint.y > 0) {
            final Point nextPoint = mapGrid.get(currentPoint.y - 1).get(currentPoint.x);
            if (nextPoint.val.equals(currentPoint.val)) {
                neighbors.add(nextPoint);
            }
        }
        if (currentPoint.y < mapGrid.size() - 1) {
            final Point nextPoint = mapGrid.get(currentPoint.y + 1).get(currentPoint.x);
            if (nextPoint.val.equals(currentPoint.val)) {
                neighbors.add(nextPoint);
            }
        }
        return neighbors;
    }

    public static long question2(Stream<String> lineStream) {
        List<List<String>> inputGrid = new ArrayList<>();
        lineStream.forEach(l -> inputGrid.add(l.chars().mapToObj(i -> String.valueOf((char) i)).toList()));
        List<List<Point>> mapGrid = new ArrayList<>();
        for (int row = 0; row < inputGrid.size(); row++) {
            mapGrid.add(new ArrayList<>());
            for (int col = 0; col < inputGrid.get(row).size(); col++) {
                mapGrid.get(row).add(new Point(col, row, inputGrid.get(row).get(col)));
            }
        }

        Set<Map<Point, Integer>> regions = new HashSet<>();
        Optional<Point> first = Optional.of(mapGrid.getFirst().getFirst());
        while (first.isPresent()) {
            final Map<Point, Integer> neighbors = findNeighbors(first.get(), mapGrid);
            regions.add(neighbors);
            first = mapGrid.stream()
                .flatMap(List::stream)
                .filter(p -> regions.stream().noneMatch(r -> r.containsKey(p)))
                .findFirst();
        }

        final Map<Map<Point, Integer>, Set<Edge>> regionEdgeMap = regions.stream()
            .collect(Collectors.toMap(
                r -> r,
                r ->
                    r.keySet().stream().map(p -> Edge.getEdges(p, mapGrid))
                        .flatMap(Set::stream).collect(Collectors.toSet())
            ));

        return regionEdgeMap.entrySet().stream()
            .mapToInt(region -> region.getKey().size() * region.getValue().size()).sum();
    }


}