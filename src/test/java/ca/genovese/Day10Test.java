package ca.genovese;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;


public class Day10Test {

    @Test
    void testSample1() {
        try (Stream<String> lines = Files.lines(Paths.get("src/test/resources/Day10Sample"))) {

            long count = Day10.question1(lines);

            assertThat(count).isEqualTo(36);


        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void testInput1() {
        try (Stream<String> lines = Files.lines(Paths.get("src/test/resources/Day10Input"))) {
            long count = Day10.question1(lines);

            assertThat(count).isEqualTo(550);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void testSample2() {
        try (Stream<String> lines = Files.lines(Paths.get("src/test/resources/Day10Sample"))) {
            long count = Day10.question2(lines);
            assertThat(count).isEqualTo(81);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void testInput2() {
        try (Stream<String> lines = Files.lines(Paths.get("src/test/resources/Day10Input"))) {
            long count = Day10.question2(lines);

            assertThat(count).isEqualTo(1255);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
