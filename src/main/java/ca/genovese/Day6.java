package ca.genovese;

import java.util.*;
import java.util.stream.Stream;


public class Day6 {
    record Point(int x, int y) {}
    record Position(int x, int y, int deltaX, int deltaY) {
        public Point getPoint() {
            return new Point(x, y);
        }
        public Position move() {
            return new Position(x + deltaX, y + deltaY, deltaX, deltaY);
        }

        public Position turnRight() {
            if(deltaX == 0) {
                return new Position(x, y,  -deltaY, 0);
            } else {
                return new Position(x, y,  0, deltaX);
            }
        }
    }

    public static long question1(Stream<String> lineStream) {
        HashMap<Point, Set<Position>> visited = findVisited(lineStream);
        return visited.size();
    }

    private static HashMap<Point, Set<Position>> findVisited(Stream<String> lineStream) {
        List<List<String>> mapGrid = new ArrayList<>();
        lineStream.forEach(l -> mapGrid.add(l.chars().mapToObj(i -> String.valueOf((char) i)).toList()));
        Position start = getStartPosition(mapGrid);

        HashMap<Point, Set<Position>> visited = getPointSetHashMap(start, mapGrid);
        return visited;
    }

    private static HashMap<Point, Set<Position>> getPointSetHashMap(Position start, List<List<String>> mapGrid) {
        HashMap<Point, Set<Position>> visited = new HashMap<>();
        Position current = start;
        Position next = current;

        while(next.y >= 0 && next.y < mapGrid.size() && next.x >= 0 && next.x < mapGrid.get(0).size()) {
            Set<Position> newValue = visited.getOrDefault(next.getPoint(), new HashSet<>());
            if(newValue.contains(current)) {
                return null;
            }
            newValue.add(current);
            visited.put(next.getPoint(), newValue);

            current = next;
            next = current.move();
            while(next.y >= 0 && next.y < mapGrid.size() && next.x >= 0 && next.x < mapGrid.get(0).size() &&
                    mapGrid.get(next.y).get(next.x).equals("#")) {
                current = current.turnRight();
                next = current.move();
            }
        }
        return visited;
    }

    public static long question2(Stream<String> lineStream) {
        List<List<String>> mapGrid = new ArrayList<>();
        lineStream.forEach(l -> mapGrid.add(new ArrayList<>(l.chars().mapToObj(i -> String.valueOf((char) i)).toList())));
        Position start = getStartPosition(mapGrid);

        HashMap<Point, Set<Position>> visited = getPointSetHashMap(start, mapGrid);

        List<Point> loopPoints = visited.keySet().stream()
                .filter(pos -> {
                    mapGrid.get(pos.y).set(pos.x, "#");
                    HashMap<Point, Set<Position>> result = getPointSetHashMap(start, mapGrid);
                    mapGrid.get(pos.y).set(pos.x, ".");
                    return result == null;
                }).toList();

        return loopPoints.size();
    }

    private static Position getStartPosition(List<List<String>> mapGrid) {
        Position start = null;
        for(int i = 0; i < mapGrid.size(); i++) {
            for(int j = 0; j < mapGrid.get(i).size(); j++) {
                if(mapGrid.get(i).get(j).equals("^")) {
                    start = new Position(j, i, 0, -1);
                    break;
                }
            }
        }
        return start;
    }

}
