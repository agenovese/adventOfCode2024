package ca.genovese;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;


public class Day12Test {

    @Test
    void testSample1() {
        try (Stream<String> lines = Files.lines(Paths.get("src/test/resources/Day12Sample"))) {

            long count = Day12.question1(lines);

            assertThat(count).isEqualTo(1930);


        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void testInput1() {
        try (Stream<String> lines = Files.lines(Paths.get("src/test/resources/Day12Input"))) {
            long count = Day12.question1(lines);

            assertThat(count).isEqualTo(1456082);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void testSample2() {
        try (Stream<String> lines = Files.lines(Paths.get("src/test/resources/Day12Sample"))) {
            long count = Day12.question2(lines);
            assertThat(count).isEqualTo(1206);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void testInput2() {
        try (Stream<String> lines = Files.lines(Paths.get("src/test/resources/Day12Input"))) {
            long count = Day12.question2(lines);

            assertThat(count).isEqualTo(0);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
