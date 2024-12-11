package ca.genovese;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;


public class Day11Test {

    @Test
    void testSample1() {
        try (Stream<String> lines = Files.lines(Paths.get("src/test/resources/Day11Sample"))) {

            long count = Day11.question1(lines);

            assertThat(count).isEqualTo(55312L);


        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void testInput1() {
        try (Stream<String> lines = Files.lines(Paths.get("src/test/resources/Day11Input"))) {
            long count = Day11.question1(lines);

            assertThat(count).isEqualTo(233875L);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void testSample2() {
        try (Stream<String> lines = Files.lines(Paths.get("src/test/resources/Day11Sample"))) {
            long count = Day11.question2(lines);
            assertThat(count).isEqualTo(65601038650482L);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void testInput2() {
        try (Stream<String> lines = Files.lines(Paths.get("src/test/resources/Day11Input"))) {
            long count = Day11.question2(lines);

            assertThat(count).isEqualTo(277444936413293L);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
