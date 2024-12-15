package ca.genovese;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;


public class Day13Test {

    @Test
    void testSample1() {
        try (Stream<String> lines = Files.lines(Paths.get("src/test/resources/Day13Sample"))) {
            BigInteger count = Day13.question1(lines);
            assertThat(count).isEqualTo(480);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void testInput1() {
        try (Stream<String> lines = Files.lines(Paths.get("src/test/resources/Day13Input"))) {
            BigInteger count = Day13.question1(lines);

            assertThat(count).isEqualTo(38839);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void testSample2() {
        try (Stream<String> lines = Files.lines(Paths.get("src/test/resources/Day13Sample"))) {
            BigInteger count = Day13.question2(lines);
            assertThat(count).isEqualTo(875318608908L);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void testInput2() {
        try (Stream<String> lines = Files.lines(Paths.get("src/test/resources/Day13Input"))) {
            BigInteger count = Day13.question2(lines);

            assertThat(count).isEqualTo(75200131617108L);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
