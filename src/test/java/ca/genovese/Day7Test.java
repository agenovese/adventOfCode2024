package ca.genovese;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;


public class Day7Test {

    @Test
    void testSample1() {
        try (Stream<String> lines = Files.lines(Paths.get("src/test/resources/Day7Sample"))) {

            long count = Day7.question1(lines);

            assertThat(count).isEqualTo(3749);


        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void testInput1() {
        try (Stream<String> lines = Files.lines(Paths.get("src/test/resources/Day7Input"))) {
            long count = Day7.question1(lines);

            assertThat(count).isEqualTo(663613490587L);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void testSample2() {
        try (Stream<String> lines = Files.lines(Paths.get("src/test/resources/Day7Sample"))) {
            long count = Day7.question1(lines);
            assertThat(count).isEqualTo(11387);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void testInput2() {
        try (Stream<String> lines = Files.lines(Paths.get("src/test/resources/Day7Input"))) {
            long count = Day7.question1(lines);

            assertThat(count).isEqualTo(110365987435001L);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
