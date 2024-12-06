package ca.genovese;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;


public class Day2Test {

    @Test
    void testInput() {
        try (Stream<String> lines = Files.lines(Paths.get("src/test/resources/Day2Input"))) {
            long count = Day2.getSafeReports(lines);

            assertThat(count).isEqualTo(328);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }



    @Test
    void testSampleInput() {
        try (Stream<String> lines = Files.lines(Paths.get("src/test/resources/Day2Sample"))) {

            long count = Day2.getSafeReports(lines);

            assertThat(count).isEqualTo(4);


        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
