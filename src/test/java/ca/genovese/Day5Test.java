package ca.genovese;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;


public class Day5Test {

    @Test
    void testInput1() {
        try (Stream<String> lines = Files.lines(Paths.get("src/test/resources/Day5Input"))) {
            long count = Day5.question1(lines);

            assertThat(count).isEqualTo(5509);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }



    @Test
    void testSample1() {
        try (Stream<String> lines = Files.lines(Paths.get("src/test/resources/Day5Sample"))) {

            long count = Day5.question1(lines);

            assertThat(count).isEqualTo(143);


        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void testInput2() {
        try (Stream<String> lines = Files.lines(Paths.get("src/test/resources/Day5Input"))) {
            long count = Day5.question2(lines);

            assertThat(count).isEqualTo(0);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void testSample2() {
        try (Stream<String> lines = Files.lines(Paths.get("src/test/resources/Day5Sample"))) {

            long count = Day5.question2(lines);

            assertThat(count).isEqualTo(123);


        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
