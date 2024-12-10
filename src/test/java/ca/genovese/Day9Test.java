package ca.genovese;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;


public class Day9Test {

    @Test
    void testSample1() {
        try (Stream<String> lines = Files.lines(Paths.get("src/test/resources/Day9Sample"))) {

            long count = Day9.question1(lines);

            assertThat(count).isEqualTo(1928);


        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void testInput1() {
        try (Stream<String> lines = Files.lines(Paths.get("src/test/resources/Day9Input"))) {
            long count = Day9.question1(lines);

            assertThat(count).isEqualTo(6283404590840L);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void testSample2() {
        try (Stream<String> lines = Files.lines(Paths.get("src/test/resources/Day9Sample"))) {
            long count = Day9.question2(lines);
            assertThat(count).isEqualTo(2858);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void testInput2() {
        try (Stream<String> lines = Files.lines(Paths.get("src/test/resources/Day9Input"))) {
            long count = Day9.question2(lines);

            assertThat(count).isEqualTo(9701157345462L);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
