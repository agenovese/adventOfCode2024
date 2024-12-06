package ca.genovese;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;


public class Day4Test {

    @Test
    void testInput() {
        try (Stream<String> lines = Files.lines(Paths.get("src/test/resources/Day4Input"))) {
            long count = Day4.wordsearch(lines);

            assertThat(count).isEqualTo(1941);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }



    @Test
    void testSampleInput() {
        try (Stream<String> lines = Files.lines(Paths.get("src/test/resources/Day4Sample"))) {

            long count = Day4.wordsearch(lines);

            assertThat(count).isEqualTo(9);


        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
