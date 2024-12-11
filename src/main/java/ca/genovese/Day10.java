package ca.genovese;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Day10 {

    record StartEnd(Point start, Point end) {}
    record Point(int x, int y, int value) {}
    record Path(List<Point> points) {
        public Path addStep(Point point) {
            List<Point> newPoints = new ArrayList<>(points);
            newPoints.add(point);
            return new Path(newPoints);
        }
    }

    public static long question1(Stream<String> lineStream) {
        Set<Path> finalPaths = getPaths(lineStream);

        Set<StartEnd> pathMaps = finalPaths.stream().map(p -> new StartEnd(p.points.getFirst(), p.points.getLast())).collect(Collectors.toSet());


        return pathMaps.size();
    }

    private static Set<Path> getPaths(Stream<String> lineStream) {
        List<List<Integer>> inputGrid = new ArrayList<>();
        lineStream.forEach(l -> inputGrid.add(l.chars().mapToObj(i -> Integer.parseInt(String.valueOf((char) i))).toList()));
        List<List<Point>> mapGrid = new ArrayList<>();
        for(int row = 0; row < inputGrid.size(); row++) {
            mapGrid.add(new ArrayList<>());
            for(int col = 0; col < inputGrid.get(row).size(); col++) {
                mapGrid.get(row).add(new Point(col, row, inputGrid.get(row).get(col)));
            }
        }

        mapGrid.forEach(System.out::println);

        List<Point> trailHeads = mapGrid.stream().flatMap(List::stream).filter(p -> p.value == 0).toList();
        List<Path> paths = trailHeads.stream().map(p -> new Path(List.of(p))).toList();
        Set<Path> finalPaths = paths.stream()
                .flatMap(p -> findNextPoints(p.points.getLast(), mapGrid).stream().map(p::addStep))
                .flatMap(p -> findNextPoints(p.points.getLast(), mapGrid).stream().map(p::addStep))
                .flatMap(p -> findNextPoints(p.points.getLast(), mapGrid).stream().map(p::addStep))
                .flatMap(p -> findNextPoints(p.points.getLast(), mapGrid).stream().map(p::addStep))
                .flatMap(p -> findNextPoints(p.points.getLast(), mapGrid).stream().map(p::addStep))
                .flatMap(p -> findNextPoints(p.points.getLast(), mapGrid).stream().map(p::addStep))
                .flatMap(p -> findNextPoints(p.points.getLast(), mapGrid).stream().map(p::addStep))
                .flatMap(p -> findNextPoints(p.points.getLast(), mapGrid).stream().map(p::addStep))
                .flatMap(p -> findNextPoints(p.points.getLast(), mapGrid).stream().map(p::addStep))
                .collect(Collectors.toSet());
        return finalPaths;
    }

    private static List<Point> findNextPoints(Point point, List<List<Point>> mapGrid) {
        List<Point> result = new ArrayList<>();
        if(point.y > 0 && mapGrid.get(point.y - 1).get(point.x).value == point.value + 1) {
            result.add(mapGrid.get(point.y - 1).get(point.x));
        }
        if (point.y < mapGrid.size() - 1 && mapGrid.get(point.y + 1).get(point.x).value == point.value + 1) {
            result.add(mapGrid.get(point.y + 1).get(point.x));
        }
        if(point.x > 0 && mapGrid.get(point.y).get(point.x - 1).value == point.value + 1) {
            result.add(mapGrid.get(point.y).get(point.x - 1));
        }
        if(point.x < mapGrid.getFirst().size() - 1 && mapGrid.get(point.y).get(point.x + 1).value == point.value + 1) {
            result.add(mapGrid.get(point.y).get(point.x + 1));
        }
        return result;
    }

    public static long question2(Stream<String> lineStream) {
        Set<Path> finalPaths = getPaths(lineStream);
        return finalPaths.size();
    }


}