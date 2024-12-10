package ca.genovese;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;


public class Day8Test {

    @Test
    void testSample1() {
        try (Stream<String> lines = Files.lines(Paths.get("src/test/resources/Day8Sample"))) {

            long count = Day8.question1(lines);

            assertThat(count).isEqualTo(14);


        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void testInput1() {
        try (Stream<String> lines = Files.lines(Paths.get("src/test/resources/Day8Input"))) {
            long count = Day8.question1(lines);

            assertThat(count).isEqualTo(336L);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void testSample2() {
        try (Stream<String> lines = Files.lines(Paths.get("src/test/resources/Day8Sample"))) {
            long count = Day8.question2(lines);
            assertThat(count).isEqualTo(34);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void testInput2() {
        try (Stream<String> lines = Files.lines(Paths.get("src/test/resources/Day8Input"))) {
            long count = Day8.question2(lines);

            assertThat(count).isEqualTo(1131L);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
