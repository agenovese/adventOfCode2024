package ca.genovese;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;


public class Day6Test {

    @Test
    void testSample1() {
        try (Stream<String> lines = Files.lines(Paths.get("src/test/resources/Day6Sample"))) {

            long count = Day6.question1(lines);

            assertThat(count).isEqualTo(0);


        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void testInput1() {
        try (Stream<String> lines = Files.lines(Paths.get("src/test/resources/Day6Input"))) {
            long count = Day6.question1(lines);

            assertThat(count).isEqualTo(0);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void testSample2() {
        try (Stream<String> lines = Files.lines(Paths.get("src/test/resources/Day6Sample"))) {

            long count = Day6.question2(lines);

            assertThat(count).isEqualTo(0);


        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void testInput2() {
        try (Stream<String> lines = Files.lines(Paths.get("src/test/resources/Day6Input"))) {
            long count = Day6.question2(lines);

            assertThat(count).isEqualTo(0);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
