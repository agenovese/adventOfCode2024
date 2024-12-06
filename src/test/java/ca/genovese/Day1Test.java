package ca.genovese;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class Day1Test {

    @Test
    void testListDistance() {
        List<Integer> list1 = List.of(3, 4, 2, 1, 3, 3);
        List<Integer> list2 = List.of(4, 3, 5, 3, 9, 3);

        int distance = Day1.listDistance(list1, list2);
        assertThat(distance).isEqualTo(11);
    }

    @Test
    void testListSimilarity() {
        List<Integer> list1 = List.of(3, 4, 2, 1, 3, 3);
        List<Integer> list2 = List.of(4, 3, 5, 3, 9, 3);

        int distance = Day1.listSimilarity(list1, list2);
        assertThat(distance).isEqualTo(31);
    }

    @Test
    void testListDistanceForInput() {
        try (Stream<String> lines = Files.lines(Paths.get("src/test/resources/Day1Input.txt"))) {
            List<Integer> list1 = new ArrayList<>();
            List<Integer> list2 = new ArrayList<>();

            lines.forEach(line -> {
                String[] split = line.split("\s+");
                list1.add(Integer.parseInt(split[0]));
                list2.add(Integer.parseInt(split[1]));
            });

            int distance = Day1.listDistance(list1, list2);
            assertThat(distance).isEqualTo(1506483);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void testListSimilarityForInput() {
        try (Stream<String> lines = Files.lines(Paths.get("src/test/resources/Day1Input.txt"))) {
            List<Integer> list1 = new ArrayList<>();
            List<Integer> list2 = new ArrayList<>();

            lines.forEach(line -> {
                String[] split = line.split("\s+");
                list1.add(Integer.parseInt(split[0]));
                list2.add(Integer.parseInt(split[1]));
            });

            int distance = Day1.listSimilarity(list1, list2);
            assertThat(distance).isEqualTo(23126924);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}