package ca.genovese;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;


public class Day3Test {

    @Test
    void testInput() {
        try (Stream<String> lines = Files.lines(Paths.get("src/test/resources/Day3Input"))) {
            long count = Day3.parseInstructions(lines);

            assertThat(count).isEqualTo(174336360L);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }



    @Test
    void testSampleInput() {
        try (Stream<String> lines = Files.lines(Paths.get("src/test/resources/Day3Sample"))) {

            long count = Day3.parseInstructions(lines);

            assertThat(count).isEqualTo(48L);


        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
